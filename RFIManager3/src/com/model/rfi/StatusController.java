package com.model.rfi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
 
@SuppressWarnings("deprecation") 
public class StatusController extends SimpleFormController { 
 
private RFIService rfiService;

 
public StatusController() { 
setCommandClass(StatusForm.class); 
setCommandName("status"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap();
	List<String> statusList = rfiService.getStatusList();
	statusList.remove("In Process");
	statusList.remove("Approved");
	referenceData.put("statusList", statusList);
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInside the submit method     \n");
	StatusForm statusForm = (StatusForm) command;
	Status status = new Status();
	status.setStatus(statusForm.getStatus());
	if (statusForm != null)
	{
		String actionType = request.getParameter("actionType");
		if("add".equals(actionType))
		{
			rfiService.addStatus(status);
		}
		else if ("edit".equals(actionType))
		{
			rfiService.editStatus(statusForm.getStatus(), statusForm.getNewStatus());
		}
		else if ("delete".equals(actionType))
		{
			rfiService.deleteStatus(status);
		}
	}
	return new ModelAndView("redirect:status.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}