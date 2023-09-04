package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본요청
     * 둘다 허용 /hello-basic, /hello-basic/
     * -> 두가지 요청을 다른 URL이지만, 스프링은 다음 URL 요청들을 같은 요청으로 매핑한다.
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     * -> @RequestMapping에 method 속성으로 HTTP 메서드를 지정하지 않으면 HTTP 메서드와 무관하게 호출한다.
     *
     * hello-basic URL 호출이 오면 이 메서드가 실행되도록 매핑한다.
     * 대부분의 속성을 '배열[]'로 제공하므로 다중 설정이 가능하다. {"/hello-basic", "/hello-go/"}
     */
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic start");
        return "ok";
    }

    @RequestMapping(value = "mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1 start");
        return "ok";
    }

    /**
     * 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2 start");
        return "ok";
    }

    /**
     * PathVariable(경로변수) 사용
     * 변수명이 같으면 생략 가능
     *
     * @PathVariable("userId") String userId -> @PathVariable userId
     * ex) /mapping/userA
     *
     * URL경로에 값을 템플릿 형식으로 사용할 수 있다. (URL경로를 템플릿화)
     * userId의 값을 @PathVariable 이라는걸로 꺼내서 사용할 수 있다. (@PathVariable을 사용하여 매칭되는 부분을 편리하게 조회)
     * @PathVariable의 이름과 파라미터 이름이 같으면 생략할 수 있다.
     *
     * 비슷한 예
     * ?userId=userA -> 쿼리파라미터 방식
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
    //public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath start");
        log.info("mappingPath userId={}", data);
        //log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * PathVariable 다중 사용
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath 다중 사용 start");
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params = "mode",
     * params = "!mode",
     * params = "mode=debug",
     * params = "mode!=debug",
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam start");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers = "mode",
     * headers = "!mode",
     * headers = "mode=debug",
     * headers = "mode!=debug",
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader start");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type (요청헤더의 Content-Type)
     * consumes = "application/json"
     * consumes = "!application/json"
     * consumes = "application/*"
     * consumes = "*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    //@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes start");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type (요청헤더의 Accept)
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     *
     * 예시)
     * produce = "text/plain"
     * produce = {"text/plain","application/*"}
     * produce = MediaType.TEXT_PLAIN_VALUE
     * produce = "text/plain;charset=UTF-8"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    //@PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_PLAIN_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces start");
        return "ok";
    }

}
