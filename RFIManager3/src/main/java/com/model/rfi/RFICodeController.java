package com.model.rfi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
 
@SuppressWarnings("deprecation") 
public class RFICodeController extends SimpleFormController { 
 
private RFIService rfiService;

 
public RFICodeController() { 
setCommandClass(RFICodeForm.class); 
setCommandName("rfiCode"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap(); 
	referenceData.put("rfiCodeList", rfiService.getRfiCodeList());
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInside the submit method     \n");
	RFICodeForm rfiCodeForm = (RFICodeForm) command;
	RFICode rfiCode = new RFICode();
	rfiCode.setRfiCode(rfiCodeForm.getRfiCode());
	if (rfiCodeForm != null)
	{
		String actionType = request.getParameter("actionType");
		if("add".equals(actionType))
		{
			rfiService.addRFICode(rfiCode);
		}
		else if ("edit".equals(actionType))
		{
			rfiService.editRFICode(rfiCodeForm.getRfiCode(), rfiCodeForm.getNewRFICode());
		}
		else if ("delete".equals(actionType))
		{
			rfiService.deleteRFICode(rfiCode);
		}
	}
	return new ModelAndView("redirect:rfiCode.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}