package com.yanli.common.dao;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, timeout=5)
public interface BaseDao<T>
{
	
	T get(Class<T> entityClazz , Serializable id);
	Serializable save(T entity);
	void update(T entity);
	void delete(T entity);
	void delete(Class<T> entityClazz , Serializable id);
	List<T> findAll(Class<T> entityClazz);
	long findCount(Class<T> entityClazz);
	public List<T> find(String hql);
}
