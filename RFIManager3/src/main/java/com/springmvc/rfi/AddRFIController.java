package com.springmvc.rfi;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.model.rfi.ExpandedRFI;
import com.model.rfi.RFI;
import com.model.rfi.RFIService;
 
@SuppressWarnings("deprecation") 
public class AddRFIController extends SimpleFormController { 
 
private RFIService rfiService;

 
public AddRFIController() { 
setCommandClass(AddRFIForm.class); 
setCommandName("addRfi"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
	CustomDateEditor editor1 = new CustomDateEditor(dateFormat1, true);
	
	binder.registerCustomEditor(Date.class,"inspectionDate", editor);
	binder.registerCustomEditor(Date.class, "issueDate", editor1);
}

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInside the reference data     \n");
	Map referenceData = new HashMap(); 
	referenceData.put("itemsList", rfiService.getWorkItemDescriptionList());
	referenceData.put("sideList", rfiService.getSideList());
	referenceData.put("activityList", rfiService.getActivityList());
	referenceData.put("layerList", rfiService.getLayerList());
	referenceData.put("rfiCodeList", rfiService.getRfiCodeList());
	referenceData.put("nextRfiNumber", rfiService.getNextRfiNumber());
	referenceData.put("boqNumberList", rfiService.getBoqNumberList(""));
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInside the submit method     \n");
	AddRFIForm addRfiForm = (AddRFIForm) command;
	RFI rfi = new RFI();
	rfi.setRfiNumber(addRfiForm.getRfiNumber());
	rfi.setRfiCode(addRfiForm.getRfiCode());
	rfi.setFromChainage(addRfiForm.getFromChainage());
	rfi.setToChainage(addRfiForm.getToChainage());
	rfi.setSide(addRfiForm.getSide());
	rfi.setLayer(addRfiForm.getLayer());
	rfi.setActivity(addRfiForm.getActivity());
	rfi.setItemDescription(addRfiForm.getItemDescription());
	rfi.setWiRemarks(addRfiForm.getWiRemarks());
	rfi.setRemarks(addRfiForm.getRemarks());
	rfi.setBreakRfiString("");
	rfi.setStatus("In Process");
	rfi.setIssueDate(addRfiForm.getIssueDate());
	rfi.setInspectionDate(addRfiForm.getInspectionDate());
	List<ExpandedRFI> overlappedRfiList = null;
	if(!(request.getParameter("force") != null && "true".equals(request.getParameter("force"))))
	{
		overlappedRfiList = rfiService.isOverlap(rfi);
	}
	PrintWriter out = response.getWriter();
	if (overlappedRfiList != null)
	{
		String message = "";
		Iterator<ExpandedRFI> rfiIt = overlappedRfiList.iterator();
		while (rfiIt.hasNext())
		{
			ExpandedRFI overlappedRfi = rfiIt.next();
			if(overlappedRfi.getStatus().equals("Approved"))
			{
				message += "Overlap: "+overlappedRfi.getIssueDateAsText()+" Approved RFI Number = "+overlappedRfi.getRfiNumber()+", Appr From = "+overlappedRfi.getApprovedFrom()+", Appr To = "+overlappedRfi.getApprovedTo()+"\n";
			}
			else
			{
				message += "Overlap: "+overlappedRfi.getIssueDateAsText()+" In Process RFI Number = "+overlappedRfi.getRfiNumber()+", From = "+overlappedRfi.getFromChainage()+", To = "+overlappedRfi.getToChainage()+"\n";
			}
		}
		response.setContentType("text/xml");
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +"<rfi><status>error</status><message>"+message+"</message></rfi>");
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +"<rfi><status>error</status><message>"+message+"</message></rfi>");
		out.close();
	}
	else
	{
		rfi =rfiService.add(rfi);
		int generatedRfiNumber = Integer.parseInt(rfi.getRfiNumber());
		int nextRfiNumber = generatedRfiNumber + 1;
		System.out.println("Next rfi number is "+nextRfiNumber);
		response.setContentType("text/xml");
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +"<nextRfiNumber>"+nextRfiNumber+"</nextRfiNumber><status>true</status>");
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +"<rfi><generatedRfiNumber>"+generatedRfiNumber+"</generatedRfiNumber><nextRfiNumber>"+nextRfiNumber+"</nextRfiNumber><status>true</status></rfi>");
		out.close();
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