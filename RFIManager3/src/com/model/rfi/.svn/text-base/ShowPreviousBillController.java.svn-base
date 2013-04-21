package com.model.rfi;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.mysql.jdbc.StringUtils;

public class ShowPreviousBillController extends AbstractController
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
		HashMap itemMap = new HashMap();
		String billNumber = request.getParameter("billNumber");
		List<String> preparedItemsList = rfiService.getPreviousBilledItems(billNumber);
		Iterator<String> itemIterator = preparedItemsList.iterator();
		while (itemIterator.hasNext())
		{
			String item = itemIterator.next();
			List<RFIBill> rfiBillList = rfiService.getRfiBillList(billNumber,item);
			itemMap.put(item, rfiBillList);
		}
		request.setAttribute("itemMap", itemMap);
		request.getRequestDispatcher("/viewPreviousBill.htm?billNumber="+billNumber).forward(request, response);
		return null;
	}	

}
