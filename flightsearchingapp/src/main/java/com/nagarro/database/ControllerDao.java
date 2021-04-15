package com.nagarro.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.model.CSVData;
import com.nagarro.services.HibernateUtil;

public class ControllerDao {

	public static List<CSVData> getList() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tnx = session.beginTransaction();

		Query query = session.createQuery("from CSVData ");
		tnx.commit();
		return query.list();
	}
}
