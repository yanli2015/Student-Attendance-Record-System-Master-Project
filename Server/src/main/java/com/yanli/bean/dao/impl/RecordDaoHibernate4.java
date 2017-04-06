package com.yanli.bean.dao.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yanli.bean.Record;
import com.yanli.bean.dao.RecordDao;
import com.yanli.common.dao.impl.BaseDaoHibernate4;

@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, timeout=5)
public class RecordDaoHibernate4 extends BaseDaoHibernate4<Record> implements RecordDao{

}
