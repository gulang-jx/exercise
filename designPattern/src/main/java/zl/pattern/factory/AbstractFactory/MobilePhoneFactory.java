package zl.pattern.factory.AbstractFactory;

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
