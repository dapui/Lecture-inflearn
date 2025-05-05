package lambda.lambda5.filter;

import java.util.List;

public class FilterMainV5 {

    public static void main(String[] args) {
        // 숫자 사용 필터
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numbersResult = GenericFilter.filter(numbers, n -> n % 2 == 0);
        System.out.println("numbersResult = " + numbersResult); // [2, 4, 6, 8, 10]

        // 문자 사용 필터
        List<String> strings = List.of("A", "BB", "CCC");
        List<String> stringsResult = GenericFilter.filter(strings, s -> s.length() >= 2);
        System.out.println("stringsResult = " + stringsResult); // [BB, CCC]
    }
}
