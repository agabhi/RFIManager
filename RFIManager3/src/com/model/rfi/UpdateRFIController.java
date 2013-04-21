package com.model.rfi;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.mysql.jdbc.StringUtils;

public class UpdateRFIController extends AbstractCommandController {
	 
	private RFIService rfiService;
	
	public UpdateRFIController(){
        setCommandClass(EditRFIForm.class);
    }
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// TODO Auto-generated method stub
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

	public void setRfiService(RFIService rfiService) { 
	this.rfiService = rfiService; 
	} 
	
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		
		
		System.out.println("\n\n\n\n\nInside the submit method     \n");
		EditRFIForm editRfiForm = (EditRFIForm) arg2;
		RFI rfi = new RFI();
		if(editRfiForm.getBreakRfiString() != null && !StringUtils.isEmptyOrWhitespaceOnly(editRfiForm.getBreakRfiString())) {
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
		if(editRfiForm.getGrade() != null && !StringUtils.isEmptyOrWhitespaceOnly(editRfiForm.getGrade()))
			rfi.setGrade(editRfiForm.getGrade());
		rfi.setIssueDate(editRfiForm.getIssueDate());
		rfi.setInspectionDate(editRfiForm.getInspectionDate());
		
		if("Approved".equals(rfi.getStatus())) {
			rfi.setBillNumber(editRfiForm.getBillNumber());
			addApprovals(editRfiForm, rfi);
		}
	
		
		if(editRfiForm.getBreakRfiString() != null && !StringUtils.isEmptyOrWhitespaceOnly(editRfiForm.getBreakRfiString())) {
			rfiService.add(rfi);
		}
		else
		{
				rfiService.edit(rfi);
		}
		
		//arg0.setAttribute("editSuccess", "true");
		arg1.setContentType("text/xml");
	    PrintWriter out = arg1.getWriter();
	    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +"<status>true</status>");
	    out.close();
	    return null;
		
	    
        
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
			if(editRfiForm.getQuantity1() != null)
			{
				rfiApproval1.setQuantity(editRfiForm.getQuantity1());
			}
			else if(editRfiForm.getArea1() != null)
			{
				rfiApproval1.setArea(editRfiForm.getArea1());
			}
			else
			{
				rfiApproval1.setBreadth(editRfiForm.getBreadth1());
				rfiApproval1.setDepth(editRfiForm.getDepth1());
			}
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
			if(editRfiForm.getQuantity2() != null)
			{
				rfiApproval2.setQuantity(editRfiForm.getQuantity2());
			}
			else if(editRfiForm.getArea2() != null)
			{
				rfiApproval2.setArea(editRfiForm.getArea2());
			}
			else
			{
				rfiApproval2.setBreadth(editRfiForm.getBreadth2());
				rfiApproval2.setDepth(editRfiForm.getDepth2());
			}
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
			if(editRfiForm.getQuantity3() != null)
			{
				rfiApproval3.setQuantity(editRfiForm.getQuantity3());
			}
			else if(editRfiForm.getArea3() != null)
			{
				rfiApproval3.setArea(editRfiForm.getArea3());
			}
			else
			{
				rfiApproval3.setBreadth(editRfiForm.getBreadth3());
				rfiApproval3.setDepth(editRfiForm.getDepth3());
			}
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
			if(editRfiForm.getQuantity4() != null)
			{
				rfiApproval4.setQuantity(editRfiForm.getQuantity4());
			}
			else if(editRfiForm.getArea4() != null)
			{
				rfiApproval4.setArea(editRfiForm.getArea4());
			}
			else
			{
				rfiApproval4.setBreadth(editRfiForm.getBreadth4());
				rfiApproval4.setDepth(editRfiForm.getDepth4());
			}
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
			if(editRfiForm.getQuantity5() != null)
			{
				rfiApproval5.setQuantity(editRfiForm.getQuantity5());
			}
			else if(editRfiForm.getArea5() != null)
			{
				rfiApproval5.setArea(editRfiForm.getArea5());
			}
			else
			{
				rfiApproval5.setBreadth(editRfiForm.getBreadth5());
				rfiApproval5.setDepth(editRfiForm.getDepth5());
			}
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
			if(editRfiForm.getQuantity6() != null)
			{
				rfiApproval6.setQuantity(editRfiForm.getQuantity6());
			}
			else if(editRfiForm.getArea6() != null)
			{
				rfiApproval6.setArea(editRfiForm.getArea6());
			}
			else
			{
				rfiApproval6.setBreadth(editRfiForm.getBreadth6());
				rfiApproval6.setDepth(editRfiForm.getDepth6());
			}
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
			if(editRfiForm.getQuantity7() != null)
			{
				rfiApproval7.setQuantity(editRfiForm.getQuantity7());
			}
			else if(editRfiForm.getArea7() != null)
			{
				rfiApproval7.setArea(editRfiForm.getArea7());
			}
			else
			{
				rfiApproval7.setBreadth(editRfiForm.getBreadth7());
				rfiApproval7.setDepth(editRfiForm.getDepth7());
			}
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
			if(editRfiForm.getQuantity8() != null)
			{
				rfiApproval8.setQuantity(editRfiForm.getQuantity8());
			}
			else if(editRfiForm.getArea8() != null)
			{
				rfiApproval8.setArea(editRfiForm.getArea8());
			}
			else
			{
				rfiApproval8.setBreadth(editRfiForm.getBreadth8());
				rfiApproval8.setDepth(editRfiForm.getDepth8());
			}
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
			if(editRfiForm.getQuantity9() != null)
			{
				rfiApproval9.setQuantity(editRfiForm.getQuantity9());
			}
			else if(editRfiForm.getArea9() != null)
			{
				rfiApproval9.setArea(editRfiForm.getArea9());
			}
			else
			{
				rfiApproval9.setBreadth(editRfiForm.getBreadth9());
				rfiApproval9.setDepth(editRfiForm.getDepth9());
			}
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
			if(editRfiForm.getQuantity10() != null)
			{
				rfiApproval10.setQuantity(editRfiForm.getQuantity10());
			}
			else if(editRfiForm.getArea10() != null)
			{
				rfiApproval10.setArea(editRfiForm.getArea10());
			}
			else
			{
				rfiApproval10.setBreadth(editRfiForm.getBreadth10());
				rfiApproval10.setDepth(editRfiForm.getDepth10());
			}
			rfiApprovals.add(rfiApproval10);
			}
			
			
			rfi.setRfiApprovals(rfiApprovals);
		
	}
	

	
}
