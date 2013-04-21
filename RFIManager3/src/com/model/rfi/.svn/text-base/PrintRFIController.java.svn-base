package com.model.rfi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class PrintRFIController extends AbstractController
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
		 
		 String[] rfiNumberArray = request.getParameterValues("rfiNumber");
		 List<ExpandedRFI> rfiList = null;
		 if(rfiNumberArray != null && rfiNumberArray.length > 0)
		 {
			 rfiList = rfiService.getMultipleRFI(rfiNumberArray);
			 request.setAttribute("rfiList",rfiList);
		 }
		 
		 String[] copyArray = request.getParameterValues("copy");
		 if(rfiNumberArray != null && rfiNumberArray.length > 0)
		 {
			 request.setAttribute("copyArray", copyArray);
		 }
		 
		 
		return new ModelAndView("/jsp/rfiFormat.jsp","rfiList",rfiList);
	}	

}
