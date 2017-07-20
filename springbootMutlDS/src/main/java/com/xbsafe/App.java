package com.xbsafe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@MapperScan("com.xbsafe.sqlDB.mapper")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class,args);
	}
}
