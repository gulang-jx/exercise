package zl.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 测试类
 * @author zhouliang
 * @date 2017年9月23日
 */
public class Test {
	public static void main(String[] args) {
		/**
		 * 静态代理
		 */
		Landlord lan = new Landlord();
		ISubject obj = new StaticProxy(lan);
		obj.rent();
		
		/**
		 * 动态代理
		 */
		Landlord lan2 = new Landlord();			//代理的真实对象
		InvocationHandler handler = new DynamicProxy(lan2);
		ISubject obj2 = (ISubject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), lan2.getClass().getInterfaces(),handler);
		obj2.rent();
	}
}
