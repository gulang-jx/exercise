package zl.pattern.factory.AbstractFactory;

public class Customer {
	public static void main(String[] args) {
		AbstractMobilePhoneFactory factory = new MobilePhoneFactory();
		IPhone7Series iphone7 = factory.createIphone7();
		
		HuaweiPSeries p10 = factory.createHuaWeiP10();
		
		
		iphone7.product();
		p10.product();
	}
}
