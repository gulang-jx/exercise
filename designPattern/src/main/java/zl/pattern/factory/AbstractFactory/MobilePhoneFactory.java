package zl.pattern.factory.AbstractFactory;

/**
 * 实体工厂类
 * @author zhouliang
 * @date 2017年9月6日
 */
public class MobilePhoneFactory implements AbstractMobilePhoneFactory{

	@Override
	public IPhone7Series createIphone7() {
		// TODO Auto-generated method stub
		return new Iphone7();
	}

	@Override
	public HuaweiPSeries createHuaWeiP10() {
		// TODO Auto-generated method stub
		return new HuaWeiP10();
	}

}
