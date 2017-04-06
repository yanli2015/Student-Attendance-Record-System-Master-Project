package com.yanli.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yanli.namebinding.annotation.Auth;
import com.yanli.services.AccountService;
import com.yanli.services.CourseService;
import com.yanli.util.RandomString;

@Path("/course")
@Auth
public class CourseResource {
	
	
	@GET
	@Path("/getQRString")
	public Response getQRString(@QueryParam("courseId") String courseId){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		CourseService cs = ctx.getBean("courseService", CourseService.class);
		String qrcode = cs.getQRCode(courseId);
		return Response.status((Response.Status.CREATED)).entity(qrcode).build();
	}
	
	@POST
	@Path("/verifyQRString")
	public Response verifyQRString(@FormParam("qrcode") String qrcode, @FormParam("accountid") String id){

		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		CourseService cs = ctx.getBean("courseService", CourseService.class);
		boolean bl = cs.verifyQRCode(qrcode, id);
		if(bl == true){
			return Response.status(Status.ACCEPTED).entity("Record was just updated").build();
		}else{
			return Response.status(Status.FORBIDDEN).entity("The QRcode is in valid").build();
		}
	}
	
}
