package hellojpa;

public class ValueMain {
    public static void main(String[] args) {

        int a = 10;
        int b = 10;

        System.out.println("a == b: " + (a == b));

        Address address1 = new Address("city", "street", "123-123");
        Address address2 = new Address("city", "street", "123-123");

        System.out.println("address1 == address2: " + (address1 == address2));
        System.out.println("address1 equals address2: " + (address1.equals(address2))); // 값타입의 비교는 equals() 메소드를 사용하여 동등성 비교를 해야한다
    }
}
