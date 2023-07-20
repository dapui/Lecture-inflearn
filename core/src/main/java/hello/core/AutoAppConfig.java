package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(     // @ComponentScan : '@Component' 애노테이션이 붙은 클래스를 모두 스캔해서 자동으로 스프링 컨테이너에 등록해준다.
    basePackages = "hello.core",
        // 탐색할 패키지의 시작 위치를 지정한다 -> hello.core 위치에서부터 찾아달라는 뜻.
        // basePackages = {"hello.core", "hello.service"} 이렇게 여러 시작 위치를 지정할 수도 있다.
        // 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // @ComponentScan.Filter : ComponentScan을 다 뒤져서 스프링 빈으로 자동 등록하는데, 그 중에서 뺄 것을 지정해 주는 것.
        // -> 예제는 Configuration 애노테이션이 붙은 클래스를 제외. (공부목적으로 기존 예제코드를 남기기위해 Configuration 제외함)
)
public class AutoAppConfig {

/*  
 * 동일한 이름의 Bean 충돌 테스트
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
