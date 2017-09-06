package zl.pattern.singleton;

/**
 * 双重锁式
 * @author zhouliang
 * @date 2017年9月6日
 */
public class Singleton3 {
	private static volatile Singleton3 instance = null;

	private Singleton3() {
	}

	public static Singleton3 getInstance() {
		if (instance == null) {
			synchronized (Singleton3.class) {
				if (instance == null) {
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
}
