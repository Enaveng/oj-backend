package com.dlwlrma.oj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dlwlrma.oj.common.BaseResponse;
import com.dlwlrma.oj.common.ErrorCode;
import com.dlwlrma.oj.common.ResultUtils;
import com.dlwlrma.oj.exception.BusinessException;
import com.dlwlrma.oj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.dlwlrma.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dlwlrma.oj.model.entity.QuestionSubmit;
import com.dlwlrma.oj.model.entity.User;
import com.dlwlrma.oj.model.vo.QuestionSubmitVO;
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

    //方便项目改造微服务 将题目提交接口移动到题目接口
//    @Resource
//    private QuestionSubmitService questionSubmitService;
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 提交
//     *
//     * @param questionSubmitAddRequest
//     * @param request
//     * @return resultNum 本次提交变化数
//     */
//    @PostMapping("/")
//    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//                                         HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 登录才能提交
//        final User loginUser = userService.getLoginUser(request);
//        Long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(result);
//    }
//
//    /**
//     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）
//     *
//     * @param questionSubmitQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
//                                                                         HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        // 从数据库中查询原始的题目提交分页信息
//        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        final User loginUser = userService.getLoginUser(request);
//        // 返回脱敏信息
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
//    }


}
