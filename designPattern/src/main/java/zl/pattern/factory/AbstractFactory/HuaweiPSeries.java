package zl.pattern.factory.AbstractFactory;

/**
 * 抽象产品类（华为P系列）
 * @author zhouliang
 * @date 2017年9月6日
 */
public class HuaweiPSeries implements HuaWeiFactory{

	@Override
	public void product() {
		// TODO Auto-generated method stub
		System.out.println("生产了一台华为P系列手机:");
	}

}
