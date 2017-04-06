package com.yanli.filter;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import com.yanli.db.DBAction;
import com.yanli.namebinding.annotation.Auth;

import javax.ws.rs.container.ContainerRequestContext;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.NameBinding;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Auth
public class BearerTokenFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		// TODO Auto-generated method stub
		String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$");
		if(authHeader == null){
			throw new NotAuthorizedException("Bearer");
		}
		String token = parseToken(authHeader);
		Set<String> tokenSet = new HashSet<>();
		if(!tokenSet.contains(token)){
			if(token == null || verifyToken(token) == false){
				throw new NotAuthorizedException("Bear error=\"invalid_token\"");
			}else{
				tokenSet.add(token);
			}
		}
		
	}
	
	private String parseToken(String header){
		String token = header.substring(7);
		return token;
	}
	
	private boolean verifyToken(String token){
		return DBAction.isTokenInDB(token);
	}
}
