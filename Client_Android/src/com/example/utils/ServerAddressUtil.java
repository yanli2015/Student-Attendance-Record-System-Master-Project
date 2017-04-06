package com.example.utils;

public class ServerAddressUtil {
	
	public static final String baseUrl = "http://ec2-54-88-134-121.compute-1.amazonaws.com:8080/yan";
	public static final String siginUrl = baseUrl + "/account/signin";
	public static final String signupUrl = baseUrl + "/account/signup";
	public static final String getQRCodeUrl = baseUrl+"/course/getQRString?courseId=1";
	public static final String verifyQRCodeUrl = baseUrl + "/course/verifyQRString";

}
