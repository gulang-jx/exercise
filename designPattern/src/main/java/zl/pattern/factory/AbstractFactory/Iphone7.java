package zl.pattern.factory.AbstractFactory;

/**
 * 实体产品类（iphone7）
 * @author zhouliang
 * @date 2017年9月6日
 */
public class Iphone7 extends IPhone7Series{

	@Override
	public void product() {
		// TODO Auto-generated method stub
		super.product();
		System.out.println("iPhone 7");
	}

}
