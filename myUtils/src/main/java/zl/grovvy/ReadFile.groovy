package zl.grovvy

class ReadFile {
	/**
	 * 读取properties文件，并将文件内容转化成map格式
	 * author zhouliang
	 * date 2017年9月12日
	 */
	static Map<String,Object> readPropertiesToMap(def filename){
		Map<String,Object> map = new HashMap<String,Object>();
		Properties prop = new Properties();
		
		new File(filename).withInputStream{
			stream->prop.load(stream);
		}
		
		Enumeration e = prop.keys();
		while(e.hasMoreElements()){
			def key = e.nextElement();
			def val = prop.get(key);
			map.put(key, val);
		}
		return map;
	}
}
