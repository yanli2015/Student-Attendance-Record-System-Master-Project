package com.yanli.services;

import java.util.Date;
import java.util.List;
import com.yanli.bean.Course;
import com.yanli.bean.Record;
import com.yanli.bean.dao.CourseDao;
import com.yanli.bean.dao.RecordDao;
import com.yanli.util.RandomString;

public class CourseService {
	
	private CourseDao courseDao;
	private RecordDao recordDao;

	public RecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	public boolean verifyQRCode(String qrcode, String accountId){
		if(qrcode == null){
			return false;
		}
		String[] arr = qrcode.split(":");
		String courseId = arr[0];
		Course course = getCourseFromDB(courseId);
		if(course == null){
			return false;
		}else{
			String targetQRcode = course.getQRcode();
			if(!qrcode.equals(targetQRcode)){
				return false;
			}else{
				Record rec = new Record();
				rec.setAccountId(Integer.parseInt(accountId));
				Date date = new java.sql.Date(System.currentTimeMillis());
				rec.setDate(date);
				rec.setCourseId(Integer.parseInt(courseId));
				recordDao.save(rec);
				return true;
			}
		}
	}
	
	public String getQRCode(String courseId){
		Course course = getCourseFromDB(courseId);
		if(course.getQRcode() != null){
			return course.getQRcode();
		}else{
			RandomString rs = new RandomString();
			String randomStr = rs.getRandomString();
			String qrcode = courseId + ":" + randomStr;
			course.setQRcode(qrcode);
			courseDao.update(course);
			return qrcode;
		}
	}
	
	public Course getCourseFromDB(String courseId){
		Class entityClazz;
		try {
			entityClazz = Class.forName("com.yanli.bean.Course");
			List<Course> list = courseDao.findAll(entityClazz);
			for(Course c : list){
				if(c.getId() == Integer.parseInt(courseId)){
					return c;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateQRCodeInDB(String qrcode){
		
	}
}
