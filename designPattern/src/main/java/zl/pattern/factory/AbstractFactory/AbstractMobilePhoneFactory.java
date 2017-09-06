package zl.pattern.factory.AbstractFactory;

/**
 * 抽象工厂
 * @author ZhouLiang
 * @date 2017年9月6日
 */
public interface AbstractMobilePhoneFactory {
	IPhone7Series createIphone7();
	
	HuaweiPSeries createHuaWeiP10();
}
