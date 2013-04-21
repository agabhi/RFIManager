package com.springmvc.rfi;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.EditRFIForm;
import com.model.rfi.RFI;
import com.model.rfi.RFIApproval;
import com.model.rfi.RFIBill;
import com.model.rfi.RFIService;

public class EditRFIController extends SimpleFormController {
	 
	private RFIService rfiService;

	 
	public EditRFIController() { 
	setCommandClass(EditRFIForm.class); 
	setCommandName("editRfi"); 
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
		binder.registerCustomEditor(Date.class, "approvalDate1", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate2", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate3", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate4", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate5", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate6", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate7", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate8", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate9", editor1);
		binder.registerCustomEditor(Date.class, "approvalDate10", editor1);
	}
		

	protected Map referenceData(HttpServletRequest request) throws Exception {
		System.out.println("\n\n\n\n\nInside the reference data     \n");
		Map referenceData = new HashMap(); 
		referenceData.put("itemsList", rfiService.getWorkItemDescriptionList());
		referenceData.put("sideList", rfiService.getSideList()); 
		referenceData.put("layerList", rfiService.getLayerList());
		referenceData.put("billList", rfiService.getBillList());
		referenceData.put("rfiCodeList", rfiService.getRfiCodeList());
		//List<RFI> rfiList = rfiService.getRfiList();
		//referenceData.put("rfiList", rfiList);
		//rfiService.getStatusPercentages(rfiList, referenceData);
		referenceData.put("statusList", rfiService.getStatusList());
		referenceData.put("activityList", rfiService.getActivityList());
		referenceData.put("gradeList", rfiService.getGradeList());
		//referenceData.put("expandedRfiList", rfiService.getExpandedRfiList());
		//referenceData.put("rejectedPercentage", "11%");
		//referenceData.put("InProcessPercentage", "72%");
		//referenceData.put("ApprovedPercentage", "17%");
		referenceData.put("boqNumberList", rfiService.getBoqNumberList(""));
		return referenceData; 
	}



    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
    		HttpServletResponse response, Object command, BindException errors)
    		throws Exception {
    	try {
		System.out.println("\n\n\n\n\nInside the submit method     \n");
	EditRFIForm editRfiForm = (EditRFIForm) command;
	RFI rfi = new RFI();
	if(editRfiForm.getBreakIndicator() != null && editRfiForm.getBreakIndicator()) {
		rfi.setRfiNumber(editRfiForm.getRfiNumber()+"-"+editRfiForm.getBreakRfiString());
	}
	else
	{
		rfi.setRfiNumber(editRfiForm.getRfiNumber());
	}
	rfi.setRfiCode(editRfiForm.getRfiCode());
	rfi.setFromChainage(editRfiForm.getFromChainage());
	rfi.setToChainage(editRfiForm.getToChainage());
		rfi.setSide(editRfiForm.getSide());
	rfi.setLayer(editRfiForm.getLayer());
	rfi.setActivity(editRfiForm.getActivity());
	rfi.setItemDescription(editRfiForm.getItemDescription());
	rfi.setWiRemarks(editRfiForm.getWiRemarks());
	rfi.setRemarks(editRfiForm.getRemarks());
	rfi.setBreakRfiString("");
	rfi.setStatus(editRfiForm.getStatus());
	rfi.setGrade(editRfiForm.getGrade());
	rfi.setIssueDate(editRfiForm.getIssueDate());
	rfi.setInspectionDate(editRfiForm.getInspectionDate());
	
	if("Approved".equals(rfi.getStatus())) {
		rfi.setBillNumber(editRfiForm.getBillNumber());
		addApprovals(editRfiForm, rfi);
	}
	
		
		//File file = new File("C:\\Users\\Abhishek\\Desktop\\rfiBill1.txt");
		//FileWriter fos = new FileWriter(file);
		//fos.write("Checking the data\n");
		
		
		
		
		
		
		System.out.println("\n\n\nChecking11\n\n\n\n");
		
		if(editRfiForm.getBreakIndicator() != null && editRfiForm.getBreakIndicator()) {
			rfiService.add(rfi);
		}
		else
		{
			rfiService.edit(rfi);
		}
		
		request.setAttribute("editSuccess", "true");
		
	    		
	return new ModelAndView("rfiXml.htm");
	//return new ModelAndView("redirect:editRFI.htm");
		//return null;
	}
	catch (Exception e) {
		System.out.println("skdjaldj     \n");
		e.printStackTrace();
		throw e;
	}
	}
	
	private void addApprovals(EditRFIForm editRfiForm, RFI rfi) {
		
		Set<RFIApproval> rfiApprovals = new HashSet<RFIApproval>();
		
	System.out.println("\n\n\nChecking1\n\n\n\n");

			if(editRfiForm.getApprovalFrom1() != null) {
			RFIApproval rfiApproval1 = new RFIApproval();
			rfiApproval1.setApprovedFrom(editRfiForm.getApprovalFrom1());
			rfiApproval1.setApprovedTo(editRfiForm.getApprovalTo1());
			rfiApproval1.setApprovedSide(editRfiForm.getApprovalSide1());
			rfiApproval1.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval1.setApprovalDate(editRfiForm.getApprovalDate1());
			rfiApprovals.add(rfiApproval1);
			}
			if(editRfiForm.getApprovalFrom2() != null) {
			System.out.println("\n\n\nChecking2\n\n\n\n");
			
			RFIApproval rfiApproval2 = new RFIApproval();
			rfiApproval2.setApprovedFrom(editRfiForm.getApprovalFrom2());
			rfiApproval2.setApprovedTo(editRfiForm.getApprovalTo2());
			rfiApproval2.setApprovedSide(editRfiForm.getApprovalSide2());
			rfiApproval2.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval2.setApprovalDate(editRfiForm.getApprovalDate2());
			rfiApprovals.add(rfiApproval2);
			}
			if(editRfiForm.getApprovalFrom3() != null) {
			System.out.println("\n\n\nChecking3\n\n\n\n");
			RFIApproval rfiApproval3 = new RFIApproval();
			rfiApproval3.setApprovedFrom(editRfiForm.getApprovalFrom3());
			rfiApproval3.setApprovedTo(editRfiForm.getApprovalTo3());
			rfiApproval3.setApprovedSide(editRfiForm.getApprovalSide3());
			rfiApproval3.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval3.setApprovalDate(editRfiForm.getApprovalDate3());
			rfiApprovals.add(rfiApproval3);
			}
			if(editRfiForm.getApprovalFrom4() != null) {
			System.out.println("\n\n\nChecking4\n\n\n\n");
			RFIApproval rfiApproval4 = new RFIApproval();
			rfiApproval4.setApprovedFrom(editRfiForm.getApprovalFrom4());
			rfiApproval4.setApprovedTo(editRfiForm.getApprovalTo4());
			rfiApproval4.setApprovedSide(editRfiForm.getApprovalSide4());
			rfiApproval4.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval4.setApprovalDate(editRfiForm.getApprovalDate4());
			rfiApprovals.add(rfiApproval4);
			}
			if(editRfiForm.getApprovalFrom5() != null) {
			System.out.println("\n\n\nChecking5\n\n\n\n");
			RFIApproval rfiApproval5 = new RFIApproval();
			rfiApproval5.setApprovedFrom(editRfiForm.getApprovalFrom5());
			rfiApproval5.setApprovedTo(editRfiForm.getApprovalTo5());
			rfiApproval5.setApprovedSide(editRfiForm.getApprovalSide5());
			rfiApproval5.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval5.setApprovalDate(editRfiForm.getApprovalDate5());
			rfiApprovals.add(rfiApproval5);
			}
			if(editRfiForm.getApprovalFrom6() != null) {
			System.out.println("\n\n\nChecking6\n\n\n\n");
			RFIApproval rfiApproval6 = new RFIApproval();
			rfiApproval6.setApprovedFrom(editRfiForm.getApprovalFrom6());
			rfiApproval6.setApprovedTo(editRfiForm.getApprovalTo6());
			rfiApproval6.setApprovedSide(editRfiForm.getApprovalSide6());
			rfiApproval6.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval6.setApprovalDate(editRfiForm.getApprovalDate6());
			rfiApprovals.add(rfiApproval6);
			}
			if(editRfiForm.getApprovalFrom7() != null) {
			System.out.println("\n\n\nChecking7\n\n\n\n");
			RFIApproval rfiApproval7 = new RFIApproval();
			rfiApproval7.setApprovedFrom(editRfiForm.getApprovalFrom7());
			rfiApproval7.setApprovedTo(editRfiForm.getApprovalTo7());
			rfiApproval7.setApprovedSide(editRfiForm.getApprovalSide7());
			rfiApproval7.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval7.setApprovalDate(editRfiForm.getApprovalDate7());
			rfiApprovals.add(rfiApproval7);
			}
			if(editRfiForm.getApprovalFrom8() != null) {
			System.out.println("\n\n\nChecking8\n\n\n\n");
			RFIApproval rfiApproval8 = new RFIApproval();
			rfiApproval8.setApprovedFrom(editRfiForm.getApprovalFrom8());
			rfiApproval8.setApprovedTo(editRfiForm.getApprovalTo8());
			rfiApproval8.setApprovedSide(editRfiForm.getApprovalSide8());
			rfiApproval8.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval8.setApprovalDate(editRfiForm.getApprovalDate8());
			rfiApprovals.add(rfiApproval8);
			}
			if(editRfiForm.getApprovalFrom9() != null) {
			System.out.println("\n\n\nChecking9\n\n\n\n");
			RFIApproval rfiApproval9 = new RFIApproval();
			rfiApproval9.setApprovedFrom(editRfiForm.getApprovalFrom9());
			rfiApproval9.setApprovedTo(editRfiForm.getApprovalTo9());
			rfiApproval9.setApprovedSide(editRfiForm.getApprovalSide9());
			rfiApproval9.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval9.setApprovalDate(editRfiForm.getApprovalDate9());
			rfiApprovals.add(rfiApproval9);
			}
			if(editRfiForm.getApprovalFrom10() != null) {
			System.out.println("\n\n\nChecking10\n\n\n\n");
			RFIApproval rfiApproval10 = new RFIApproval();
			rfiApproval10.setApprovedFrom(editRfiForm.getApprovalFrom10());
			rfiApproval10.setApprovedTo(editRfiForm.getApprovalTo10());
			rfiApproval10.setApprovedSide(editRfiForm.getApprovalSide10());
			rfiApproval10.setApprovedLayer(editRfiForm.getLayer());
			rfiApproval10.setApprovalDate(editRfiForm.getApprovalDate10());
			rfiApprovals.add(rfiApproval10);
			}
			
			
			rfi.setRfiApprovals(rfiApprovals);
		
	}
	/*
	private void addBills(EditRFIForm editRfiForm, RFI rfi) {
		Set<RFIBill> rfiBills = new HashSet<RFIBill>();
		if(editRfiForm.getBillFrom1() != null) {
			//fos.write("Bill number is "+editRfiForm.getBillNumber1());
			RFIBill rfiBill1 = new RFIBill();
			rfiBill1.setBillNumber(editRfiForm.getBillNumber1());
			rfiBill1.setBillFrom(editRfiForm.getBillFrom1());
			rfiBill1.setBillTo(editRfiForm.getBillTo1());
			rfiBill1.setBillSide(editRfiForm.getBillSide1());
			rfiBill1.setBillLayer(editRfiForm.getBillLayer1());
			rfiBills.add(rfiBill1);
			}
			if(editRfiForm.getBillFrom2() != null) {
			System.out.println("\n\n\nChecking2\n\n\n\n");
			
			RFIBill rfiBill2 = new RFIBill();
			rfiBill2.setBillNumber(editRfiForm.getBillNumber2());
			rfiBill2.setBillFrom(editRfiForm.getBillFrom2());
			rfiBill2.setBillTo(editRfiForm.getBillTo2());
			rfiBill2.setBillSide(editRfiForm.getBillSide2());
			rfiBill2.setBillLayer(editRfiForm.getBillLayer2());
			rfiBills.add(rfiBill2);
			}
			if(editRfiForm.getBillFrom3() != null) {
			System.out.println("\n\n\nChecking3\n\n\n\n");
			RFIBill rfiBill3 = new RFIBill();
			rfiBill3.setBillNumber(editRfiForm.getBillNumber3());
			rfiBill3.setBillFrom(editRfiForm.getBillFrom3());
			rfiBill3.setBillTo(editRfiForm.getBillTo3());
			rfiBill3.setBillSide(editRfiForm.getBillSide3());
			rfiBill3.setBillLayer(editRfiForm.getBillLayer3());
			rfiBills.add(rfiBill3);
			}
			if(editRfiForm.getBillFrom4() != null) {
			System.out.println("\n\n\nChecking4\n\n\n\n");
			RFIBill rfiBill4 = new RFIBill();
			rfiBill4.setBillNumber(editRfiForm.getBillNumber4());
			rfiBill4.setBillFrom(editRfiForm.getBillFrom4());
			rfiBill4.setBillTo(editRfiForm.getBillTo4());
			rfiBill4.setBillSide(editRfiForm.getBillSide4());
			rfiBill4.setBillLayer(editRfiForm.getBillLayer4());
			rfiBills.add(rfiBill4);
			}
			if(editRfiForm.getBillFrom5() != null) {
			System.out.println("\n\n\nChecking5\n\n\n\n");
			RFIBill rfiBill5 = new RFIBill();
			rfiBill5.setBillNumber(editRfiForm.getBillNumber5());
			rfiBill5.setBillFrom(editRfiForm.getBillFrom5());
			rfiBill5.setBillTo(editRfiForm.getBillTo5());
			rfiBill5.setBillSide(editRfiForm.getBillSide5());
			rfiBill5.setBillLayer(editRfiForm.getBillLayer5());
			rfiBills.add(rfiBill5);
			}
			if(editRfiForm.getBillFrom6() != null) {
			System.out.println("\n\n\nChecking6\n\n\n\n");
			RFIBill rfiBill6 = new RFIBill();
			rfiBill6.setBillNumber(editRfiForm.getBillNumber6());
			rfiBill6.setBillFrom(editRfiForm.getBillFrom6());
			rfiBill6.setBillTo(editRfiForm.getBillTo6());
			rfiBill6.setBillSide(editRfiForm.getBillSide6());
			rfiBill6.setBillLayer(editRfiForm.getBillLayer6());
			rfiBills.add(rfiBill6);
			}
			if(editRfiForm.getBillFrom7() != null) {
			System.out.println("\n\n\nChecking7\n\n\n\n");
			RFIBill rfiBill7 = new RFIBill();
			rfiBill7.setBillNumber(editRfiForm.getBillNumber7());
			rfiBill7.setBillFrom(editRfiForm.getBillFrom7());
			rfiBill7.setBillTo(editRfiForm.getBillTo7());
			rfiBill7.setBillSide(editRfiForm.getBillSide7());
			rfiBill7.setBillLayer(editRfiForm.getBillLayer7());
			rfiBills.add(rfiBill7);
			}
			if(editRfiForm.getBillFrom8() != null) {
			System.out.println("\n\n\nChecking8\n\n\n\n");
			RFIBill rfiBill8 = new RFIBill();
			rfiBill8.setBillNumber(editRfiForm.getBillNumber8());
			rfiBill8.setBillFrom(editRfiForm.getBillFrom8());
			rfiBill8.setBillTo(editRfiForm.getBillTo8());
			rfiBill8.setBillSide(editRfiForm.getBillSide8());
			rfiBill8.setBillLayer(editRfiForm.getBillLayer8());
			rfiBills.add(rfiBill8);
			}
			if(editRfiForm.getBillFrom9() != null) {
			System.out.println("\n\n\nChecking9\n\n\n\n");
			RFIBill rfiBill9 = new RFIBill();
			rfiBill9.setBillNumber(editRfiForm.getBillNumber9());
			rfiBill9.setBillFrom(editRfiForm.getBillFrom9());
			rfiBill9.setBillTo(editRfiForm.getBillTo9());
			rfiBill9.setBillSide(editRfiForm.getBillSide9());
			rfiBill9.setBillLayer(editRfiForm.getBillLayer9());
			rfiBills.add(rfiBill9);
			}
			if(editRfiForm.getBillFrom10() != null) {
			System.out.println("\n\n\nChecking10\n\n\n\n");
			RFIBill rfiBill10 = new RFIBill();
			rfiBill10.setBillNumber(editRfiForm.getBillNumber10());
			rfiBill10.setBillFrom(editRfiForm.getBillFrom10());
			rfiBill10.setBillTo(editRfiForm.getBillTo10());
			rfiBill10.setBillSide(editRfiForm.getBillSide10());
			rfiBill10.setBillLayer(editRfiForm.getBillLayer10());
			rfiBills.add(rfiBill10);
			}
			
			//fos.close();
			rfi.setRfiBills(rfiBills);

	
	}
	*/
}
