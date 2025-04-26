package annotation.java;

// @Deprecated는 더 이상 사용되지 않는다는 뜻이다. 이 애노테이션이 적용된 기능은 사용을 권장하지 않는다.
public class DeprecatedClass {

    public void call1() {
        System.out.println("DeprecatedClass.call1");
    }

    @Deprecated
    public void call2() {
        System.out.println("DeprecatedClass.call2");
    }

    @Deprecated(since = "2.4", forRemoval = true)
    public void call3() {
        System.out.println("DeprecatedClass.call3");
    }
}
