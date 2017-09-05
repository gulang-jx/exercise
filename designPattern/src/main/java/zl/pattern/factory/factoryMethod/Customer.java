package zl.pattern.factory.factoryMethod;

public class Customer {
	public static void main(String[] args) {
		Factory factory = new PersonFactory();
		Person student = factory.getInstance(Student.class);
		Person teacher = factory.getInstance(Teacher.class);
		
		student.exam();
		
		teacher.exam();
	}
}
