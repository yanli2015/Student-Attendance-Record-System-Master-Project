package com.yanli.bean.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.yanli.bean.Course;
import com.yanli.bean.dao.CourseDao;
import com.yanli.common.dao.impl.BaseDaoHibernate4;
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, timeout=5)
public class CourseDaoHibernate4 extends BaseDaoHibernate4<Course> implements CourseDao{

}
