package zl.pattern.factory.AbstractFactory;

/**
 * 抽象产品类（苹果7系列）
 * @author zhouliang
 * @date 2017年9月6日
 */
public class IPhone7Series implements AppleFactory{

	@Override
	public void product() {
		// TODO Auto-generated method stub
		System.out.println("生产了一台苹果7系列手机:");
	}
	
}
