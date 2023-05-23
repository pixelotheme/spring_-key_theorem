package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자 DI
public class LogDemoController {

    private final LogDemoService logDemoService;
    //MyLogger를 찾을수있는 DL(Dependency Lookup)기능의 provider가 DI 된다
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    //프록시 적용
    private final MyLogger myLogger;


    @RequestMapping("log-demo")
    @ResponseBody //view 템플릿 없이 직접응답 보낼수 있다
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //http 의 servlet 규약으로 요청정보를 받을수 있다
        String requestURL = request.getRequestURL().toString();

        //DL 기능을 이용한 요청 - getObject 시점에 request scope 이 생성된다
//        MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");

        Thread.sleep(1000);

        logDemoService.logic("testId");

        return "OK";
    }
}
