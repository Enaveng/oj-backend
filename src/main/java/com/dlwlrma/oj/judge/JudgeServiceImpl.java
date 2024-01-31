package com.dlwlrma.oj.judge;

import cn.hutool.json.JSONUtil;
import com.dlwlrma.oj.common.ErrorCode;
import com.dlwlrma.oj.exception.BusinessException;
import com.dlwlrma.oj.judge.codesandbox.CodeSandBox;
import com.dlwlrma.oj.judge.codesandbox.CodeSandBoxFactory;
import com.dlwlrma.oj.judge.codesandbox.CodeSandBoxProxy;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.dlwlrma.oj.judge.startegy.JudgeContext;
import com.dlwlrma.oj.model.dto.question.JudgeCase;
import com.dlwlrma.oj.judge.codesandbox.model.JudgeInfo;
import com.dlwlrma.oj.model.entity.Question;
import com.dlwlrma.oj.model.entity.QuestionSubmit;
import com.dlwlrma.oj.model.enums.QuestionSubmitStatusEnum;
import com.dlwlrma.oj.service.QuestionService;
import com.dlwlrma.oj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type}")
    private String type;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private QuestionService questionService;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //根据传入题目的提交id 获取到对应的题目、题目提交信息(包括代码、编程语言等)
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交的题目不存在");
        }
        //通过提交的题目得到对应的题目id
        Long questionId = questionSubmit.getQuestionId();
        //根据id进行题目的查询
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目信息不存在");
        }
        //只有提交的题目的status字段为'等待中'才可以去执行代码沙箱
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中或已经判题完成");
        }
        //更新判题状态(题目提交表)为"判题中"，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }
        //调用代码沙箱得到执行结果
        CodeSandBox codeSandBox = CodeSandBoxFactory.createCodeSandBoxByType(type); //工厂模式创建对应的代码沙箱
        CodeSandBoxProxy codeSandBoxProxy = new CodeSandBoxProxy(codeSandBox);      //代理模式增强功能(打印日志)
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        //数据库保存的是json格式 需要将其转换为List
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest().builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBoxProxy.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = new JudgeManager().doJudge(judgeContext);
        //更新状态
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        //将JudgeInfo对象转换为json对象
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);
        return questionSubmitResult;
    }
}
