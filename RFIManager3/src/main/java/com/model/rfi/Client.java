package com.model.rfi;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Client {

	public static void main(String[] args) {
		  Session session = null;

		  try{
		  // This step will read hibernate.cfg.xml and prepare hibernate for use
		  SessionFactory sessionFactory = new 

		  Configuration().configure().buildSessionFactory();
		  session =sessionFactory.openSession();
		  session.beginTransaction();

		  //Create new instance of Contact and set values in it by reading them from form object
		  
		  System.out.println("Inserting Record");
		  RFI rfi = new RFI();
		  rfi.setRfiNumber("5");
		  //rfi.setDescription("FDD Checking of Embankment");
		  rfi.setFromChainage(17200);
		  rfi.setToChainage(17500);
		  rfi.setGrade("A");
		  rfi.setIssueDate(new Date());
		  rfi.setInspectionDate(new Date());
		  rfi.setLastEditedByUserName("aagarwal");
		  rfi.setCreatedByUserName("aagarwal");
		  //rfi.setLayer(new Layer("1"));
		  rfi.setRemarks("First Entry");
		  rfi.setRfiCode("HW");
		  //rfi.setSide(new Side("LHS"));
		  rfi.setStatus("Pending");
		  rfi.setBreakRfiString("");
		  session.save(rfi);
		  
		  
		  
session.getTransaction().commit();		  
		  
		  String SQL_QUERY ="from RFI rfi";
		   Query query = session.createQuery(SQL_QUERY);
		   for(Iterator<RFI> it=query.iterate();it.hasNext();){
		 RFI rfi1=it.next();
		  System.out.println("RFI Number: " + 
		 rfi1.getRfiNumber());
		  System.out.println("From Chainage: " + 
					 rfi1.getFromChainage());
		  
		  }
		   session.flush();
		  session.close();
		  System.out.println("Done");
		  }catch(Exception e){
		  System.out.println(e.getMessage());
		  e.printStackTrace();
		  }finally{
		  // Actual contact insertion will happen at this step
		 

		  }
		  
		  }


}
