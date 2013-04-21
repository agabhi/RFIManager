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
public class ActivityController extends SimpleFormController { 
 
private RFIService rfiService;

 
public ActivityController() { 
setCommandClass(ActivityForm.class); 
setCommandName("activity"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInactivity the reference data     \n");
	Map referenceData = new HashMap(); 
	referenceData.put("activityList", rfiService.getActivityList());
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInactivity the submit method     \n");
	ActivityForm activityForm = (ActivityForm) command;
	Activity activity = new Activity();
	activity.setActivity(activityForm.getActivity());
	if (activityForm != null)
	{
		String actionType = request.getParameter("actionType");
		if("add".equals(actionType))
		{
			rfiService.addActivity(activity);
		}
		else if ("edit".equals(actionType))
		{
			rfiService.editActivity(activityForm.getActivity(), activityForm.getNewActivity());
		}
		else if ("delete".equals(actionType))
		{
			rfiService.deleteActivity(activity);
		}
	}
	return new ModelAndView("redirect:activity.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}