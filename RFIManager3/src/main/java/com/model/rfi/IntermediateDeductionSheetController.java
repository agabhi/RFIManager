package com.model.rfi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class IntermediateDeductionSheetController extends AbstractController
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
		request.setAttribute("itemMap", rfiService.prepareBill("viewDeduction"));
		request.getRequestDispatcher("/viewDeductionSheet.htm").forward(request, response);
		return null;
	}	

}
