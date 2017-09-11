package zl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import zl.utils.LogUtils;


@Controller
public class DemoController {
	@RequestMapping("/demo")
	@ResponseBody
	public void demo() {
		JSONObject obj = new JSONObject();
		obj.put("id", 12341234);
		obj.put("time", System.currentTimeMillis());
		obj.put("message", "----------------test message------------");
		LogUtils.send(obj.toJSONString());
	}
}
