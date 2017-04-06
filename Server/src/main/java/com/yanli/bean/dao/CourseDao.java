package com.yanli.bean.dao;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.yanli.bean.Course;
import com.yanli.common.dao.BaseDao;
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, timeout=5)
public interface CourseDao extends BaseDao<Course>{

}
