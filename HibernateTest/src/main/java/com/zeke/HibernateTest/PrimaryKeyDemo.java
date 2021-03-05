package com.zeke.HibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
	
	public static void main( String[] args )
    {
    	SessionFactory factory = new Configuration()
    							.configure("hibernate.cfg.xml")
    							.addAnnotatedClass(Student.class)
    							.buildSessionFactory();
    	Session session = factory.getCurrentSession();
    	
    	try {
			Student tempStudent1 = new Student("Mihai", "Wallt", "mihai@zeke.com");
			Student tempStudent2 = new Student("Ion", "Wally", "ion@zeke.com");
			Student tempStudent3 = new Student("Rengle", "Wallz", "rengle@zeke.com");
			
			session.beginTransaction();
			
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
    }
}
