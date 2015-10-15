package com.model.rfi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
 
@SuppressWarnings("deprecation") 
public class GradeController extends SimpleFormController { 
 
private RFIService rfiService;

 
public GradeController() { 
setCommandClass(GradeForm.class); 
setCommandName("grade"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap(); 
	referenceData.put("gradeList", rfiService.getGradeList());
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInside the submit method     \n");
	GradeForm gradeForm = (GradeForm) command;
	Grade grade = new Grade();
	grade.setGrade(gradeForm.getGrade());
	if (gradeForm != null)
	{
		String actionType = request.getParameter("actionType");
		if("add".equals(actionType))
		{
			rfiService.addGrade(grade);
		}
		else if ("edit".equals(actionType))
		{
			rfiService.editGrade(gradeForm.getGrade(), gradeForm.getNewGrade());
		}
		else if ("delete".equals(actionType))
		{
			rfiService.deleteGrade(grade);
		}
	}
	return new ModelAndView("redirect:grade.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}