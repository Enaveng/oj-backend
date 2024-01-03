package com.dlwlrma.oj.judge.codesandbox.impl;
import com.dlwlrma.oj.judge.codesandbox.CodeSandBox;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeResponse;

//后续实际使用的代码沙箱
public class RemoteCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("示例使用的代码沙箱");
        return null;
    }
}
