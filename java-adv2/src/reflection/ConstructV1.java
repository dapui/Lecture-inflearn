package reflection;

import java.lang.reflect.Constructor;

public class ConstructV1 {

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        System.out.println("====== constructors() =====");
        // 해당 클래스에서 선언된 모든 public 생성자를 반환
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("====== declaredConstructors() =====");
        // 해당 클래스에서 선언된 모든 생성자를 반환
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor);
        }
    }
}
