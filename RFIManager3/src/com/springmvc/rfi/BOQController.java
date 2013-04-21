package com.springmvc.rfi;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.AddRFIForm;
import com.model.rfi.BOQ;
import com.model.rfi.RFI;
import com.model.rfi.RFIService;
 
@SuppressWarnings("deprecation") 
public class BOQController extends SimpleFormController { 
 
private RFIService rfiService;

 
public BOQController() { 
setCommandClass(BOQ.class); 
setCommandName("boq"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap(); 
	referenceData.put("boqMap", rfiService.getBoqList());
	referenceData.put("categoryList", rfiService.getCategoryList());
	referenceData.put("unitsList", rfiService.getUnitsList());
	referenceData.put("boqNumberList", rfiService.getBoqNumberList());
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInside the submit method     \n");
	BOQ boq = (BOQ) command;
	String actionType = request.getParameter("actionType");
	if("add".equals(actionType))
	{
		rfiService.addBoq(boq);
	}
	else if ("edit".equals(actionType))
	{
		rfiService.editBoq(boq);
	}
	else if ("delete".equals(actionType))
	{
		rfiService.deleteBoq(boq);
	}
	return new ModelAndView("redirect:boq.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}