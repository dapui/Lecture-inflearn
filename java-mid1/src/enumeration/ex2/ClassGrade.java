package enumeration.ex2;

public class ClassGrade {
    public static final ClassGrade BASIC = new ClassGrade();
    public static final ClassGrade GOLD = new ClassGrade();
    public static final ClassGrade DIAMOND = new ClassGrade();

    // 외부에서 생성하지 못하도록 private으로 막기
    private ClassGrade() {}
}
