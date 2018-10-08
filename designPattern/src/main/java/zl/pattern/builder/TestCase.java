package zl.pattern.builder;

public class TestCase {
    public static void main(String[] args) {
        Computer computer =  new Computer.ComputerBuilder("thinkpad E430","8G")
                .graphic(8)
                .board("华硕")
                .display("高分屏")
                .processor("i7-4600u")
                .build();
        System.out.println(computer);
    }
}
