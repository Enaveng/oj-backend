package com.dlwlrma.oj.judge.codesandbox;

import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

//代理模式 在每次使用代码沙箱时都将输入输出通过日志打印出来
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox {

    private final CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息: " + executeCodeRequest);
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息: " + executeCodeResponse);
        return executeCodeResponse;
    }
}
