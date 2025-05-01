package lambda.ex1;

public class M1After {

    // 하나의 메서드로 합치고, 매개변수(문자열)만 다르게 받아 처리
    public static void greet(String message) {
        System.out.println("=== 시작 ===");
        System.out.println(message);
        System.out.println("=== 끝 ===");
    }

    public static void main(String[] args) {
        greet("Good Morning!");
        greet("Good Afternoon!");
        greet("Good Evening!");
    }
}
