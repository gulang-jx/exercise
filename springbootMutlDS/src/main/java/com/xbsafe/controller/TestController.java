package com.xbsafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xbsafe.sqlDB.bean.User;
import com.xbsafe.sqlDB.service.UserService;

@Controller
@Component
public class TestController {
	@Autowired UserService user;
	
	@RequestMapping("/demo")
	@ResponseBody
	public String demo(){
		System.out.println("==============");
		User u = user.getUser("1");
		System.out.println(u.getName());
		return u.getName()+"------>"+u.getAge();
	}
	
	
	@RequestMapping("/demoa")
	@ResponseBody
	public String demo2(){
		System.out.println("==============");
		User u = user.getUser2("1");
		System.out.println(u.getName());
		return u.getName()+"------>"+u.getAge();
	}
}
