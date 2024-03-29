package com.dlwlrma.oj.judge.codesandbox;

import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dlwlrma.oj.judge.codesandbox.model.ExecuteCodeResponse;
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
        CodeSandBox codeSandBox = new CodeSandBoxFactory().createCodeSandBoxByType(type);
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果: \" + (a + b));\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        //使用lombok链式创建一个请求对象
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest().builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
    }


    @Test
    void executeCodeByTypeTest() {
        CodeSandBox example = new CodeSandBoxFactory().createCodeSandBoxByType("Example");
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
        CodeSandBox example = new CodeSandBoxFactory().createCodeSandBoxByType(type);
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1,2", "3,4");
        //使用lombok链式创建一个请求对象
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest().builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        CodeSandBoxProxy codeSandBoxProxy = new CodeSandBoxProxy(example);
        codeSandBoxProxy.executeCode(executeCodeRequest);
    }
}