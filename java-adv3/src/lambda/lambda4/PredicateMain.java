package lambda.lambda4;

import java.util.function.Predicate;

public class PredicateMain {

    public static void main(String[] args) {
        // 익명 클래스
        Predicate<Integer> isEvenPrdicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer value) {
                return value % 2 == 0;
            }
        };
        System.out.println("isEvenPrdicate.test(10) = " + isEvenPrdicate.test(10));

        // 람다
        Predicate<Integer> predicate = value -> value % 2 == 0;
        System.out.println("predicate.test(10) = " + predicate.test(10));
    }
}
