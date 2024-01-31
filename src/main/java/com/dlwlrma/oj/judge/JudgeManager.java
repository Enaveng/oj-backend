package com.dlwlrma.oj.judge;


import com.dlwlrma.oj.judge.startegy.DefaultJudgeStrategy;
import com.dlwlrma.oj.judge.startegy.JavaJudgeStrategy;
import com.dlwlrma.oj.judge.startegy.JudgeContext;
import com.dlwlrma.oj.judge.startegy.JudgeStrategy;
import com.dlwlrma.oj.judge.codesandbox.model.JudgeInfo;
import com.dlwlrma.oj.model.entity.QuestionSubmit;
import com.dlwlrma.oj.model.enums.QuestionSubmitLanguageEnum;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy(); //默认创建的是DefaultJudgeStrategy
        //判断提交题目的语言是什么
        String language = questionSubmit.getLanguage();
        if (language.equals(QuestionSubmitLanguageEnum.JAVA.getValue())) {
            judgeStrategy = new JavaJudgeStrategy();
        }
        log.info("得到的judgeInfo为" + judgeStrategy.doJudge(judgeContext));
        return judgeStrategy.doJudge(judgeContext);
    }
}
