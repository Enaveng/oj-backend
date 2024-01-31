package com.dlwlrma.oj.judge.codesandbox;


import com.dlwlrma.oj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.dlwlrma.oj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.dlwlrma.oj.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例） 简单工厂模式
 */
public class CodeSandBoxFactory {
    public static CodeSandBox createCodeSandBoxByType(String type) {
        switch (type) {
            case "Example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }

}
