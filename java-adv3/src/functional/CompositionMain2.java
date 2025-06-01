package functional;

import java.util.function.Function;

public class CompositionMain2 {

    public static void main(String[] args) {
        // 1. String -> Integer
        Function<String, Integer> parseInt = Integer::parseInt;

        // 2. Integer -> Integer (여기서는 제곱)
        Function<Integer, Integer> square = x -> x * x;

        // 3. Integer -> String
        Function<Integer, String> toString = x -> "결과: " + x;

        // compose 혹은 andThen으로 합성하기
        // parseInt -> square -> toString 순서로 하고 싶다면 andThen()을 사용
        Function<String, String> finalFunc = parseInt
                .andThen(square)
                .andThen(toString);

        // 문자열 "5"를 입력하면 파싱-> 제곱-> 문자열 출력 순서
        String result1 = finalFunc.apply("5");
        System.out.println(result1); // "결과: 25"

        String result2 = finalFunc.apply("10");
        System.out.println(result2); // "결과: 100"

        // 또 다른 조합으로 사용 가능
        Function<String, Integer> stringToSquareFunc = parseInt
                .andThen(square);
        Integer result3 = stringToSquareFunc.apply("5");
        System.out.println("result3 = " + result3);
    }
}
