package com.dlwlrma.oj.judge.codesandbox.impl;

import com.dlwlrma.oj.judge.codesandbox.CodeSandBox;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.dlwlrma.oj.model.dto.questionsubmit.JudgeInfo;
import com.dlwlrma.oj.model.enums.JudgeInfoMessageEnum;
import com.dlwlrma.oj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

//示例代码沙箱 暂时只是为了跑通业余流程
public class ExampleCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        //跑通业务封装需要的输出
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("这是一个测试");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setTime(1000);
        judgeInfo.setMemory(1000);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
