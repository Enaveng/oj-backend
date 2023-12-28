package com.dlwlrma.oj.controller;

import com.dlwlrma.oj.common.BaseResponse;
import com.dlwlrma.oj.common.ErrorCode;
import com.dlwlrma.oj.common.ResultUtils;
import com.dlwlrma.oj.exception.BusinessException;
//import com.dlwlrma.oj.model.dto.questionthumb.QuestionSubmitAddRequest;
import com.dlwlrma.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dlwlrma.oj.model.entity.User;
import com.dlwlrma.oj.service.QuestionSubmitService;
import com.dlwlrma.oj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 本次提交变化数
     */
    @PostMapping("/")
    public BaseResponse<Long> doThumb(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                         HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);
        Long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }

}
