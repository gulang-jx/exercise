package zl.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * @author zhouliang
 * @date 2017年9月23日
 */
public class DynamicProxy implements InvocationHandler{
	private Object obj;
	public DynamicProxy(Object obj){
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("我是 动态代理");
		Object result = method.invoke(this.obj, args);
		return result;
	}
}
