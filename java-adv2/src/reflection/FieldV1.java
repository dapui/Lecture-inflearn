package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Field;

public class FieldV1 {

    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("====== fields() =====");
        // 해당 클래스와 상위 클래스에서 상속된 모든 public 필드를 반환
        Field[] fields = helloClass.getFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        System.out.println("====== declaredFields() =====");
        // 해당 클래스에서 선언된 모든 필드를 반환
        Field[] declaredFields = helloClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("declaredField = " + field);
        }
    }
}
