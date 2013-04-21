package com.model.rfi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ChangeRFIBillController extends AbstractController
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
		 if(rfiNumberArray != null && rfiNumberArray.length > 0)
		 {
			 if(request.getParameter("prepareBill") != null && "add".equals(request.getParameter("prepareBill")))
			 {
				 rfiService.changeMultipleRFIBillNumber(rfiNumberArray, "Under Preparation");
			 }
			 
			 else if(request.getParameter("prepareBill") != null && "remove".equals(request.getParameter("prepareBill")))
			 {
				 rfiService.changeMultipleRFIBillNumber(rfiNumberArray, "");
			 }
			 else if(request.getParameter("previousBill") != null && "add".equals(request.getParameter("previousBill")))
			 {
				 String billNumber = request.getParameter("billNumber");
				 List<String> list = Arrays.asList(rfiNumberArray);
				 Set<String> rfiNumberSet = new HashSet<String>(list);
				 LinkedHashMap itemMap = rfiService.preparePreviousBill(rfiNumberSet, billNumber);
				 rfiService.editPreviousBill(billNumber, itemMap, list);
				 
			 }
			 else if(request.getParameter("previousBill") != null && "remove".equals(request.getParameter("previousBill")))
			 {
				 rfiService.deleteRFIFromBill(rfiNumberArray);
			 }
		 }
		 
		 return null;
	}	

}
