package com.dlwlrma.oj.judge.codesandbox;

import com.dlwlrma.oj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dlwlrma.oj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class CodeSandBoxTest {

    @Value("${codesandbox.type}")
    private String type;

    @Test
    void executeCodeTest() {
        //用该方式创建代码沙箱很不方便 因此引入工厂模式 通过传入一个类型自动帮我们创建一个代码沙箱
        ExampleCodeSandBox exampleCodeSandBox = new ExampleCodeSandBox();
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1,2", "3,4");
        //使用lombok链式创建一个请求对象
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest().builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        exampleCodeSandBox.executeCode(executeCodeRequest);
    }


    @Test
    void executeCodeByTypeTest() {
        CodeSandBox example = new CodeSanBoxFactory().createCodeSandBoxByType("Example");
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1,2", "3,4");
        //使用lombok链式创建一个请求对象
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest().builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        example.executeCode(executeCodeRequest);
    }


    @Test
    void executeCodeByTypeProxyTest() {
        CodeSandBox example = new CodeSanBoxFactory().createCodeSandBoxByType(type);
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1,2", "3,4");
        //使用lombok链式创建一个请求对象
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest().builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        CodeSanBoxProxy codeSanBoxProxy = new CodeSanBoxProxy(example);
        codeSanBoxProxy.executeCode(executeCodeRequest);
    }
}