package com.yanli.util;
import java.util.UUID;
public class RandomString {
	public String getRandomString(){
		return UUID.randomUUID().toString();
	}
}
