package com.model.rfi;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.AddRFIForm;
import com.model.rfi.RFI;
import com.model.rfi.RFIService;
import com.mysql.jdbc.StringUtils;
 
@SuppressWarnings("deprecation") 
public class LayerExceptionsController extends SimpleFormController { 
 
private RFIService rfiService;

 
public LayerExceptionsController() { 
setCommandClass(LayerException.class); 
setCommandName("layerException"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap(); 
	referenceData.put("itemsList", rfiService.getItemsList());
	List<String> sideList = rfiService.getSideList();
	if (sideList != null)
	{
		sideList.remove("BS");
	}
	referenceData.put("sideList", sideList); 
	referenceData.put("layerList", rfiService.getLayerList());
	referenceData.put("activityList", rfiService.getActivityList());
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	
	
	String deleteException  =  request.getParameter("deleteException");
	if(deleteException != null && !StringUtils.isEmptyOrWhitespaceOnly(deleteException))
	{
		String[] exceptionId = request.getParameterValues("exceptionId");
		if( exceptionId != null && exceptionId.length > 0)
		{
			for (int i = 0; i<exceptionId.length; ++i)
			{
				rfiService.deleteLayerExceptions(Long.valueOf(exceptionId[i]));
			}
		}
		
		return null;
	}
	
	LayerException layerException = (LayerException) command;
	String actionType = request.getParameter("actionType");
	if("add".equals(actionType))
	{
		List<LayerException> layerExceptionList = rfiService.getLayerExceptionsList(0, 0, "", " where activity = '"+layerException.getActivity()+"' AND item_description = '"+layerException.getItemDescription()+"' and side = '"+layerException.getSide()+"' ");
		if(layerExceptionList.contains(layerException))
		{
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>error</resultCode><resultText>Layer Exception cannot be added because it overlap with database entry!</resultText></result>");
		    out.close();
		    
		    return null;
		}
		else
		{
			rfiService.addLayerException(layerException);
			
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>success</resultCode><resultText>Layer Exception added successfully!</resultText></result>");
		}
	}
	else if ("edit".equals(actionType))
	{
		List<LayerException> layerExceptionList = rfiService.getLayerExceptionsList(0, 0, "", " where exception_id <> '"+layerException.getExceptionId()+"' and item_description = '"+layerException.getItemDescription()+"' and side = '"+layerException.getSide()+"' ");
		if(layerExceptionList.contains(layerException))
		{
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>error</resultCode><resultText>Layer Exception cannot be added because it overlap with database entry!</resultText></result>");
		    out.close();
		    
		    return null;
		}
		else
		{
			rfiService.editLayerExceptions(layerException);
			
			response.setContentType("text/xml");
		    PrintWriter out = response.getWriter();
		    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><resultCode>success</resultCode><resultText>Layer Exception edited successfully!</resultText></result>");
		    out.close();
		}
		
	}
	
	
	
    
    return null;
	//return new ModelAndView("rfiSuccess","rfi",rfi);
	//return new ModelAndView("redirect:addRFI.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}