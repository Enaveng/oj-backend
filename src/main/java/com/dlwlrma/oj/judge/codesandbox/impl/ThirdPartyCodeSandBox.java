package com.dlwlrma.oj.judge.codesandbox.impl;
import com.dlwlrma.oj.judge.codesandbox.CodeSandBox;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeResponse;

//第三方代码沙箱
public class ThirdPartyCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
