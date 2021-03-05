package com.zeke.HibernateTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			//retrieve all students
			List<Student> allStudents = session
										.createQuery("from Student")
										.getResultList();
			for(int i = 0; i<allStudents.size(); i++) {
				System.out.println(allStudents.get(i).toString());
			}
			
			
			//retrieve student with firstName Mihai

			
			List<Student> mihaiStudents = session.createQuery("from Student s where s.firstName='Mihai'").getResultList();
			
			
			for(int i = 0; i<mihaiStudents.size(); i++) {
				System.out.println(mihaiStudents.get(i).toString());
			}
			
			//retrieve students with email domain zeke.com
			
			List<Student>emailStudents = session.createQuery("from Student s where s.email LIKE '%zeke.com'").getResultList();
			
			for(int i = 0; i<emailStudents.size(); i++) {
				System.out.println(emailStudents.get(i).toString());
			}
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}
}
