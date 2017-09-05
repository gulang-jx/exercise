package zl.pattern.factory.simpleFactory;

/**
 * @author ZhouLiang
 * @date 2017年9月5日
 */
public class PersonFactory {
	public static Person getInstance(String type) {
		switch (type) {
		case "student":
			return new Student();
		case "teacher":
			return new Teacher();
		default:
			break;
		}
		return null;
	}
}
