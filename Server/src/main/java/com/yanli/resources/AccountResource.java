package com.yanli.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yanli.bean.Account;
import com.yanli.namebinding.annotation.Auth;
import com.yanli.services.AccountService;

@Path("/account")
public class AccountResource {
	
	@POST
	@Path("/signup")
	@Produces("application/json")
	public Response signup(@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("isTeacher") Boolean isTeacher) throws Exception {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		AccountService as = ctx.getBean("accountService", AccountService.class);
		if(!as.isUsernameExsit(username)){
			System.out.println("signup.....");
			as.createAccount(username, password, isTeacher);
			Account acc = as.getAccount(username, password);
			return Response.status(Status.CREATED).entity(acc).build();
		}else{
			return Response.status(Status.FORBIDDEN).build();
		}
	}
	
	@POST
	@Path("/signin")
	@Produces("application/json")
	public Response auth(@FormParam("username") String username,
			@FormParam("password") String password) throws Exception {
		System.out.println("signin is starting");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		AccountService as = ctx.getBean("accountService", AccountService.class);
		Account acc = as.getAccount(username, password);
		if(!acc.getToken().equals(null)){
			System.out.println("auth.....");
			acc.setPassword(null);
			return Response.ok().entity(acc).build();
		}else{
			return Response.status((Response.Status.UNAUTHORIZED)).entity("Unauthorized").build();
		}
	}
	
}
