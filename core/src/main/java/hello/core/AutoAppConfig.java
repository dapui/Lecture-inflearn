package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(     // @ComponentScan : '@Component' 애노테이션이 붙은 클래스를 모두 스캔해서 자동으로 스프링 컨테이너에 등록해준다.
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
    // @ComponentScan.Filter : ComponentScan을 다 뒤져서 스프링 빈으로 자동 등록하는데, 그 중에서 뺄 것을 지정해 주는 것.
    // -> 예제는 Configuration 애노테이션이 붙은 클래스를 제외. (공부목적으로 기존 예제코드를 남기기위해 Configuration 제외함)
)
public class AutoAppConfig {

}
