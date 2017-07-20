package com.xbsafe.sqlDB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xbsafe.sqlDB.bean.User;
import com.xbsafe.sqlDB.config.DatabaseContextHolder;
import com.xbsafe.sqlDB.config.DatabaseType;
import com.xbsafe.sqlDB.mapper.UserMapper;

@Component
public class UserService {
	@Autowired UserMapper user;
	public User getUser(String id){
		return user.getUser(id);
	}
	
	public User getUser2(String id){
		DatabaseContextHolder.setDatabaseType(DatabaseType.operateDS);
		return user.getUser(id);
	}
}
