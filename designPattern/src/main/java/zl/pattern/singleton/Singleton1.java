package zl.pattern.singleton;

/**
 * 懒汉式
 * @author zhouliang
 * @date 2017年9月6日
 */
public class Singleton1 {
	private static Singleton1 instance = null;
	private Singleton1(){}
	public static synchronized Singleton1 getInstatnce() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}
