package com.yanli.bean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class Account implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="userName") 
	private String userName;
	@Column(name="password") 
	private String password;
	@Column(name="token") 
	private String token;
	@Column(name="expriation") 
	private String expriation;
	
	@Column(name="role") 
	@Enumerated(EnumType.STRING) 
	private Role role;
	
	public Account(){
		
	}

//	public Account(String userName, String password, String token, Date expriation){
//		this.userName = userName;
//		this.password = password;
//	}
	
	public Integer getId() {
		return id;
	}

	public Account( String userName, String password, String token, String expriation, Role role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.expriation = expriation;
		this.role = role;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpriation() {
		return expriation;
	}
	public void setExpriation(String expriation) {
		this.expriation = expriation;
	}
	

}
