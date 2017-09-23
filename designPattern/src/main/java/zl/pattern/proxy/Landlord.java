package zl.pattern.proxy;

/**
 * 真实主题角色  房东
 * @author zhouliang
 * @date 2017年9月23日
 */
public class Landlord implements ISubject{

	@Override
	public void rent() {
		// TODO Auto-generated method stub
		System.out.println("我想出租一套三居室的房子，，，，");
	}

}
