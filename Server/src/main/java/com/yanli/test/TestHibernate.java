package com.yanli.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yanli.bean.Account;


public class TestHibernate {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Account user = (Account) sess.get(Account.class, 2);
		System.out.println(user.getExpriation());
		tx.commit();
		sess.close();
	}

}
