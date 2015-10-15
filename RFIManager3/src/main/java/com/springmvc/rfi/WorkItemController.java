package com.springmvc.rfi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.BOQ;
import com.model.rfi.RFIService;
import com.model.rfi.WorkItem;
 
@SuppressWarnings("deprecation") 
public class WorkItemController extends SimpleFormController { 
 
private RFIService rfiService;

 
public WorkItemController() { 
setCommandClass(WorkItem.class); 
setCommandName("workItem"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap(); 
	referenceData.put("boqNumberList", rfiService.getBoqNumberList());
	referenceData.put("workItemsList", rfiService.getWorkItemsList());
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
	WorkItem workItem = (WorkItem) command;
	String actionType = request.getParameter("actionType");
	if("add".equals(actionType))
	{
		rfiService.addWorkItem(workItem);
	}
	else if ("edit".equals(actionType))
	{
		rfiService.editWorkItem(workItem);
	}
	else if ("delete".equals(actionType))
	{
		rfiService.deleteWorkItem(workItem);
	}
	return new ModelAndView("redirect:workItem.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}