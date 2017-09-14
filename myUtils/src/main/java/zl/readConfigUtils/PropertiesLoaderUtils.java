package zl.readConfigUtils;

import java.lang.reflect.Field;
import java.util.Map;

import zl.grovvy.ReadFile;

/**
 * properties 配置文件读取工具类
 * @author zhouliang
 * @date 2017年9月12日
 */
public class PropertiesLoaderUtils {
	public static void main(String[] args) {
		/*Map<String,Object> map = ReadFile.readPropertiesToMap("src/main/resource/htttpclient.properties");
		map.forEach((k,v)->{
			System.out.println(k+"===="+v);
		});*/
	}
	/**
	 * 将读取出来的配置文件的值封装到实体bean
	 * author zhouliang
	 * date 2017年9月12日
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T extends Object>T readConfig2Bean(Class<T> clazz,String filename) throws Exception{
		T obj = clazz.newInstance();
		
		Map<String,Object> map = ReadFile.readPropertiesToMap(filename);
		
		Field[] fileds = clazz.getDeclaredFields();
		for(Field f:fileds){
			f.setAccessible(true);
			for(String key:map.keySet()){
				if(key.equalsIgnoreCase(f.getName())){
					String type = f.getType().toString();
					if(type.endsWith("String")){
						f.set(obj, map.get(key).toString());
					}
					if(type.endsWith("int") || type.endsWith("Integer")){
						f.set(obj, Integer.parseInt(map.get(key).toString()));
					}
				}
			}
		}
		return obj;
	}
}
