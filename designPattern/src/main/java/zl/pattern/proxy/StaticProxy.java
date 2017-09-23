package zl.pattern.proxy;

/**
 * 代理角色
 * @author zhouliang
 * @date 2017年9月23日
 */
public class StaticProxy implements ISubject{
	private ISubject target;
	
	public StaticProxy(ISubject subject){
		super();
		this.target = subject;
	}
	@Override
	public void rent() {
		System.out.println("我是静态代理");     //对真实对象的额外的操作
		target.rent();
	}
}
