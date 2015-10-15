package com.model.rfi;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.EditRFIForm;
import com.model.rfi.RFI;
import com.model.rfi.RFIApproval;
import com.model.rfi.RFIBill;
import com.model.rfi.RFIService;

public class ViewCalculationSheetController extends SimpleFormController {
	 
	private RFIService rfiService;

	public ViewCalculationSheetController() { 
		setCommandClass(Bill.class); 
		setCommandName("finalizeBill"); 
		}
	
	public void setRfiService(RFIService rfiService) { 
	this.rfiService = rfiService; 
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
    		HttpServletResponse response, Object command, BindException errors)
    		throws Exception
	{
		return null;
	}
	
}