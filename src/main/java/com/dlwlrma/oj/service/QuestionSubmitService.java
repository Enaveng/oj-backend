package com.dlwlrma.oj.service;

import com.dlwlrma.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dlwlrma.oj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dlwlrma.oj.model.entity.User;

/**
* @author 86158
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-11-24 10:08:07
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {


    /**
     * 题目提交保存数据库方法
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
