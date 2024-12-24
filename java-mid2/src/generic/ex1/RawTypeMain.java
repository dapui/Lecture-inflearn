package generic.ex1;

public class RawTypeMain {

    public static void main(String[] args) {
        GenericBox integerBox = new GenericBox(); // 하위 호환을 위해 존재. 사용하지 않는 것을 권장
//        GenericBox<Object> integerBox = new GenericBox<>(); // 권장
        integerBox.set(10);
        Integer result = (Integer) integerBox.get();
        System.out.println("result = " + result);
    }
}
