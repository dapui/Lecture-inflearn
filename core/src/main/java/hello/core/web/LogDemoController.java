package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor    // 생성자 의존관계 주입과 동일한 기능
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;    // ObjectProvider 덕분에 ObjectProvider.getObject()를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.

    @RequestMapping("log-demo")
    @ResponseBody    // 뷰 화면 없이 문자를 반환
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURI().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
//        Thread.sleep(1000);
        logDemoService.logic("testId");

        return "OK";
    }
}
