package com.xbsafe.sqlDB.mapper;

import org.apache.ibatis.annotations.Param;

import com.xbsafe.sqlDB.bean.User;

public interface UserMapper {
	public User getUser(@Param("id") String id);
}
