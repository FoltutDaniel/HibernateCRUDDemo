package com.zeke.HibernateTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {
	 public static void main( String[] args )
	    {
	    	SessionFactory factory = new Configuration()
	    							.configure("hibernate.cfg.xml")
	    							.addAnnotatedClass(Student.class)
	    							.buildSessionFactory();
	    	Session session = factory.getCurrentSession();
	    	
	    	try {
				Student tempStudent = new Student("Daffy", "Duck", "paul@zeke.com");
				
				session.beginTransaction();
				
				System.out.println(tempStudent);
				session.save(tempStudent);
				
				session.getTransaction().commit();
				
				//retrieve object
				//find out the student's id: primary key
				System.out.println("Saved student. Generated id: "+tempStudent.getId());
				
				//now get a new session and start tranzaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				//retrieve student based on id
				Student readStudent = session.get(Student.class, tempStudent.getId());
				
				System.out.println(readStudent.toString());
				//commit transaction
				session.getTransaction().commit();
				
				
				
			} finally {
				factory.close();
			}
	    }
}
