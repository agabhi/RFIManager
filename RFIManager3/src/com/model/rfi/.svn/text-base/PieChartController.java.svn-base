package com.model.rfi;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class PieChartController extends AbstractController
{
	
	private RFIService rfiService;
	
	
	public void setRfiService(RFIService rfiService)
	{ 
		this.rfiService = rfiService; 
	}
	
	
	 @Override
	 protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 										HttpServletResponse response) throws Exception
	{
		 String pieChart = request.getParameter("pieChart");
		 if(pieChart != null)
		 {
			 List<String> statusList = rfiService.getStatusList();
			 ArrayList array = new ArrayList();
			 if( statusList != null)
			 {
				 Iterator<String> it = statusList.iterator();
				 
				 while (it.hasNext())
				 {
					 
					 String status = it.next();
					 if ("allStatus".equals(pieChart))
					 {
						 String where = " where 1=1 ";
						 BigInteger count = rfiService.getStatusCount(status, where);
						 status = status +"("+count+")";
						 array.add(status);
						 array.add(count);
					 }
					 else if ("unbilledStatus".equals(pieChart))
					 {
						 String where = " where 1=1 AND (bill_number IS NULL OR bill_number = '') ";
						 BigInteger count = rfiService.getStatusCount(status,  where);
						 status = status +"("+count+")";
						 array.add(status);
						 array.add(count);
					 }
					 
				 }
			 }
		 
			String arrayString = array.toString(); 
			String output = array.toString().substring(1,arrayString.length()-1);
			System.out.println(array);
			response.setContentType("text");
		    PrintWriter out = response.getWriter();
		    out.println(output);
		    out.close();
		 }
	    return null;
	}	

}
