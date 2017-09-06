package zl.pattern.singleton;

/**
 * 饿汉式
 * @author zhouliang
 * @date 2017年9月6日
 */
public class Singleton2 {
	private static final Singleton2 instance = new Singleton2();

	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		return instance;
	}
}
