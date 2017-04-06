package com.example.bean;



public class Account {		
		private Integer id;
		private String userName;
		private String password;
		private String token;
		private String expriation;
		private Role role;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
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
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}

}
