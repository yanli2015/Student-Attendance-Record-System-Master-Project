package com.yanli.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yanli.services.AccountService;
import com.yanli.util.RandomString;
public class TestAccountService {
	
	public static void main(String[] args){
		System.out.println(new RandomString().getRandomString());
	}

}
