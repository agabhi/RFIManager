package com.model.rfi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class ViewPreviousBillController extends SimpleFormController{
	
	private RFIService rfiService;

	 
	public ViewPreviousBillController() { 
	setCommandClass(Bill.class); 
	setCommandName("editBill"); 
	} 
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor editor1 = new CustomDateEditor(dateFormat1, true);
		
		binder.registerCustomEditor(Date.class, editor1);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
		throws Exception {
 
		String billNumber = request.getParameter("billNumber");
		Bill bill = rfiService.getBill(billNumber);
		return bill;
	}
	
	public void setRfiService(RFIService rfiService) { 
	this.rfiService = rfiService; 
	}
	
	@Override
    protected ModelAndView onSubmit(HttpServletRequest request,
    		HttpServletResponse response, Object command, BindException errors)
    		throws Exception {
		
		//Bill bill = (Bill) command;
		String billNumber = request.getParameter("billNumber");
    	//LinkedHashMap itemMap = rfiService.prepareBill();
    	return new ModelAndView("redirect:editPreviousBill.htm?billNumber="+billNumber);
	}

}
