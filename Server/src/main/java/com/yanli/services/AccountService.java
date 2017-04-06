package com.yanli.services;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yanli.bean.Account;
import com.yanli.bean.Role;
import com.yanli.bean.dao.AccountDao;

public class AccountService {
	
	private AccountDao accountDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	//	System.out.println("inject accountDao");
	}
	
	public boolean isUsernameExsit(String username) throws ClassNotFoundException{
		
		//String hql = "select username from Account a where a.username = " + username;
		Class entityClazz = Class.forName("com.yanli.bean.Account");
		List<Account> list = accountDao.findAll(entityClazz);
		for(Account acc : list){
			if(username.equals(acc.getUserName())){
				return true;
			}
		}
		return false;
	}
	
	public void createAccount(String username, String password, Boolean isTeacher) throws ClassNotFoundException{
	
		
		String token = this.issueToken(username);
		String exp = this.getExpDate();
		Role role = Role.STUDENT;
		if(isTeacher){
			role = Role.TEACHER;
		}
		Account entity = new Account(username, password, token, exp, role);
		Class entityClazz = Class.forName("com.yanli.bean.Account");
		accountDao.save(entity);
		
	}
	
	public String issueToken(String username) {
		 Random random = new SecureRandom();
		 String token = new BigInteger(130, random).toString(32);
		 return token;
	}
	
	public String getExpDate(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 30);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
		
	}
	
	public String toMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String result = null;
		byte[] bytesOfMessage = password.getBytes("UTF-8");
	
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		StringBuffer sb = new StringBuffer();
		for (byte b : thedigest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		result = sb.toString();
		return result;
	}
	
	
	public Account getAccount(String username, String password) throws ClassNotFoundException{
		
		//String hql = "select username from Account a where a.username = " + username;
		Class entityClazz = Class.forName("com.yanli.bean.Account");
		List<Account> list = accountDao.findAll(entityClazz);
		for(Account acc : list){
			if(username.equals(acc.getUserName()) && acc.getPassword().equals(password)){
				return acc;
			}
		}
		return null;
	}

	

}
