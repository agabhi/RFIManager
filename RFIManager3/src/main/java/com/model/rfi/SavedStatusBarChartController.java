package com.model.rfi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class SavedStatusBarChartController extends SimpleFormController 
{
	
private RFIService rfiService;
	
	public void setRfiService(RFIService rfiService)
	{ 
		this.rfiService = rfiService; 
	}
	
	protected Map referenceData(HttpServletRequest request) throws Exception {
		System.out.println("\n\n\n\n\nInside the reference data     \n");
		Map referenceData = new HashMap(); 
		referenceData.put("savedStatusBarChartList", rfiService.getStatusBarChartNames());
		
		return referenceData;
	}
	
	
	public SavedStatusBarChartController() { 
		setCommandClass(DrawStatusBarChartForm.class); 
		setCommandName("drawStatusBarChart"); 
		} 
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
			return null;
	}


}
