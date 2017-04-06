package com.yanli.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yanli.services.AccountService;


public class TestSpring {
	
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		AccountService as = ctx.getBean("accountService", AccountService.class);
		System.out.println("testsssss");
		try {
			System.out.println(as.isUsernameExsit("yanli2"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("xxxx");
	}

}
