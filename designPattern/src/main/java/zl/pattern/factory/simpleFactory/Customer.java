package zl.pattern.factory.simpleFactory;

public class Customer {
public static void main(String[] args) {
	Person student = PersonFactory.getInstance("student");
	
	
	Person teacher = PersonFactory.getInstance("teacher");
	
}
}
