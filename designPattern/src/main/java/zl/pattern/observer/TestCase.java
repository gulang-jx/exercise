package zl.pattern.observer;

public class TestCase {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();

        Student zhagnsan = new Student(teacher,"zhangsan");

        Student lisi = new Student(teacher,"lisi");

        teacher.setHomeWork("第三章");
    }
}
