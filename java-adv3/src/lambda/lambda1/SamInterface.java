package lambda.lambda1;

@FunctionalInterface // 애노테이션 추가
public interface SamInterface {

    void run();
    //void gogo(); // 실수로 누군가 추가시 컴파일 오류 발생
}
