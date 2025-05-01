package lambda.ex1;

public class M2After {

    // 숫자(무게)와 단위 모두 매개변수화
    public static void print(int weight, String unit) {
        System.out.println("무게: " + weight + unit);
    }

    public static void main(String[] args) {
        print(10, "kg");
        print(50, "kg");
        print(200, "g");
        print(40, "g");
    }
}
