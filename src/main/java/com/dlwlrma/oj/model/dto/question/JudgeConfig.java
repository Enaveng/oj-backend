package com.dlwlrma.oj.model.dto.question;


/**
 * 判题配置
 * 比如写题时存在时间限制、内存限制等
 */
public class JudgeConfig {

    /**
     * 时间限制
     */
    private Long timeLimit;

    /**
     * 内存限制
     */
    private long memoryLimit;

    /**
     * 堆栈限制
     */
    private long stackLimit;

}
