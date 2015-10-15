package com.model.rfi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class PrepareBillController extends AbstractController
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
		 boolean isBillUnderPrep = rfiService.isBillUnderPreparation();
		 if(isBillUnderPrep)
		 {
			 // forward to bill inbox
			 request.getRequestDispatcher("/jsp/prepareBill.jsp").forward(request, response);
		 }
		 else
		 {
			 // forward to no bill under preparation page
			 request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		 }
		 return null;
		 
	}
}