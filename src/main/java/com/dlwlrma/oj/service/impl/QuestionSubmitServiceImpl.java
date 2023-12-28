package com.dlwlrma.oj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dlwlrma.oj.common.ErrorCode;
import com.dlwlrma.oj.exception.BusinessException;
import com.dlwlrma.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dlwlrma.oj.model.entity.QuestionSubmit;
import com.dlwlrma.oj.model.entity.User;
import com.dlwlrma.oj.model.enums.QuestionSubmitLanguageEnum;
import com.dlwlrma.oj.model.enums.QuestionSubmitStatusEnum;
import com.dlwlrma.oj.service.QuestionSubmitService;
import com.dlwlrma.oj.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 86158
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2023-11-24 10:08:07
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {


    //返回题目的提交id
    @Override
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum questionSubmitLanguage = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (questionSubmitLanguage == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "提交的编程语言类型错误");
        }
        String code = questionSubmitAddRequest.getCode();
        Long questionId = questionSubmitAddRequest.getQuestionId();
        Long id = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setLanguage(language);
        questionSubmit.setCode(code);
        questionSubmit.setJudgeInfo("{}");
        //设置初始的判题状态 (等待中)
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setUserId(id);
        //保存数据
        boolean b = this.save(questionSubmit);
        if (!b) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据保存失败");
        }
        return questionSubmit.getId();
    }
}




