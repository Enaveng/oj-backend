package com.dlwlrma.oj.judge.startegy;

import com.dlwlrma.oj.judge.codesandbox.model.JudgeInfo;


//将判题策略从JudgeServiceImpl当中抽离出来 不同的判题策略重写的内容不同 使用策略模式 不同语言的判题策略不同
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
