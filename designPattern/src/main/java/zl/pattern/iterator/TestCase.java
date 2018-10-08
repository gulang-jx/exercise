package zl.pattern.iterator;

public class TestCase {
    private Iterator it;
    private  Teacher teacher = new ConcreteTeacher();

    public void operation(){
        it = teacher.createIterator();
        while(!it.isDone()){
            System.out.println(it.currentItems().toString());
            it.next();
        }
    }
    public static void main(String[] args) {
        TestCase testCase = new TestCase();
        testCase.operation();
    }
}
