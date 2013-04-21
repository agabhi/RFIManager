package com.springmvc.rfi;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.AddRFIForm;
import com.model.rfi.BOQ;
import com.model.rfi.Quantity;
import com.model.rfi.Quantity;
import com.model.rfi.RFI;
import com.model.rfi.RFIService;
import com.mysql.jdbc.StringUtils;
 
@SuppressWarnings("deprecation") 
public class QuantityController extends SimpleFormController { 
 
private RFIService rfiService;

 
public QuantityController() { 
setCommandClass(Quantity.class); 
setCommandName("quantity"); 
} 

public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap();
	referenceData.put("workItemDescriptionList", rfiService.getWorkItemDescriptionList());
	referenceData.put("layerList", rfiService.getLayerList());
	referenceData.put("sideList", rfiService.getSideList());
	referenceData.put("activityList", rfiService.getActivityList());
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	
	System.out.println("\n\n\n\n\nInside the submit method     \n");
	Quantity quantity = (Quantity) command;
	String actionType = request.getParameter("actionType");
	if("add".equals(actionType))
	{
		List<Quantity> quantityList = rfiService.getQuantityList(0, 0, "", " where activity = '"+quantity.getActivity()+"' AND item_description = '"+quantity.getItemDescription()+"' and side = '"+quantity.getSide()+"' and layer = '"+quantity.getLayer()+"' ");
		if(quantityList.contains(quantity))
		{
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>error</resultCode><resultText>Area cannot be added because it overlap with database entry!</resultText></result>");
		    out.close();
		    
		    return null;
		}
		else
		{
			rfiService.addQuantity(quantity);
			
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>success</resultCode><resultText>Area added successfully!</resultText></result>");
		}
	}
	else if ("edit".equals(actionType))
	{
		List<Quantity> quantityList = rfiService.getQuantityList(0, 0, "", " where quantity_id <> '"+quantity.getQuantityId()+"' and item_description = '"+quantity.getItemDescription()+"' and side = '"+quantity.getSide()+"' and layer = '"+quantity.getLayer()+"' ");
		if(quantityList.contains(quantity))
		{
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>error</resultCode><resultText>Area cannot be added because it overlap with database entry!</resultText></result>");
		    out.close();
		    
		    return null;
		}
		else
		{
			rfiService.editQuantity(quantity);
			
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>success</resultCode><resultText>Area edited successfully!</resultText></result>");
		    out.close();
		}
		
	}
	
	return null;
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}