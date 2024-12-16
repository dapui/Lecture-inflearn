package nested.test;

// 지역 클래스 LocalClass를 구현
public class OuterClass3 {

    public void myMethod() {

        class LocalClass {
            public void hello() {
                System.out.println("LocalClass.hello");
            }
        }

        LocalClass local = new LocalClass();
        local.hello();
    }
}
