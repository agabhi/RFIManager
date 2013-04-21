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
public class SideController extends SimpleFormController { 
 
private RFIService rfiService;

 
public SideController() { 
setCommandClass(SideForm.class); 
setCommandName("side"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap(); 
	List<String> sideList = rfiService.getSideList();
	sideList.remove("-");
	sideList.remove("LHS");
	sideList.remove("RHS");
	sideList.remove("BS");
	referenceData.put("sideList", sideList);
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInside the submit method     \n");
	SideForm sideForm = (SideForm) command;
	Side side = new Side();
	side.setSide(sideForm.getSide());
	if (sideForm != null)
	{
		String actionType = request.getParameter("actionType");
		if("add".equals(actionType))
		{
			rfiService.addSide(side);
		}
		else if ("edit".equals(actionType))
		{
			rfiService.editSide(sideForm.getSide(), sideForm.getNewSide());
		}
		else if ("delete".equals(actionType))
		{
			rfiService.deleteSide(side);
		}
	}
	return new ModelAndView("redirect:side.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}