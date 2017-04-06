package com.yanli.db;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

public class DBAction {
	
	private static String issueToken(String username) {
		 Random random = new SecureRandom();
		 String token = new BigInteger(130, random).toString(32);
		 return token;
	}
	
	public static boolean verifyUsernameAndPassword(String username, String password){
		DBConn con = new DBConn();
		Connection conn = con.getConnection();
		String sql = "select * from attedance.account";
		Statement statement = null;
		try {
			 statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				 String tempUserName = rs.getString("username");
				 if(username.equals(tempUserName)){
					 return password.equals(rs.getString("password"));
				 }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}
	
	
	public static String getTokenFromDB(String username){
		DBConn con = new DBConn();
		Connection conn = con.getConnection();
		String sql = "select * from attedance.account";
		Statement statement = null;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String tempUserName = rs.getString("username");
				if(tempUserName.equals(username)){
					return rs.getString("token");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String createAccount(String username, String password){
		String newToken = issueToken(username);
		DBConn con = new DBConn();
		Connection conn = con.getConnection();
		String sql = "INSERT INTO account(username, password, role, token, exp_date) values(?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, "user");
			ps.setString(4, newToken);
			ps.setDate(5, new Date(0));
			int row = ps.executeUpdate();
			if(row > 0){
				System.out.println("User: "+username+" is updated.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newToken;
	}
	
	
	public static boolean findUsername(String username){
		DBConn con = new DBConn();
		Connection conn = con.getConnection();
		String sql = "select username from attedance.account";
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				 String tempUserName = rs.getString("username");
				 if(username.equals(tempUserName)){
					 if(conn != null){
						 conn.close();
					 }
					 return true;
				 }else{
					 if(conn != null){
						 conn.close();
					 }
					 return false;
				 }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(conn != null){
			 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return false;
	}
	
	
	public static void insertTokenToDB(String username, String token){
		DBConn con = new DBConn();
		Connection conn = con.getConnection();
		String sql = "select * from attedance.account";
		Statement statement = null;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				 String tempUserName = rs.getString("username");
				 if(username.equals(tempUserName)){
					 long timestamp = System.currentTimeMillis() / 1000  + 2592000;
					 String sql2 = "update account set token=" + token + ", exp_date=" + timestamp + " where username=" + tempUserName;
					 statement.executeQuery(sql2);
					 if(conn != null){
						 conn.close();
					 }
					 break;
				 }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean isTokenInDB(String token){
		DBConn con = new DBConn();
		Connection conn = con.getConnection();
		String sql = "select token from attedance.account";
		Statement statement = null;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String tempToken = rs.getString("token");
				if(token.equals(tempToken)){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
