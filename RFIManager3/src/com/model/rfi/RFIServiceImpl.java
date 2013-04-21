package com.model.rfi;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

import com.mysql.jdbc.StringUtils;
import com.springmvc.rfi.UserRegistrationForm;


public class RFIServiceImpl implements RFIService {
	
	private Set<Side> existingSideHash;
	private Set<Activity> existingActivityHash;
	private Set<Layer> existingLayerHash;
	private Set<BOQ> existingBoqHash;
	private Set<WorkItem> existingWorkItemHash;
	private Set<Grade> existingGradeHash;
	private Set<Status> existingStatusHash;
	private Set<RFICode> existingRfiCodeHash;
	
	private HashMap<String,WorkItem> workItemMap = new HashMap<String,WorkItem>();
	
	private RFIServiceDAO rfiServiceDao;
	static SimpleDateFormat inspectionDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	static SimpleDateFormat issueDateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
	
	@Override
	public RFI add(RFI rfi) {
		rfi.setCreatedByUserName("system");
		rfi.setLastEditedByUserName("system");
		System.out.println("RFI Added Successfully");
		return rfiServiceDao.add(rfi);
		
	}
	
	@Override
	public void addBoqList(Set<BOQ> workItemHash)
	{
		rfiServiceDao.addBoqList(workItemHash);
	}
	
	@Override
	public BOQ getBoq(String boqNumber)
	{
		return rfiServiceDao.getBoq(boqNumber);
	}
	
	@Override
	public List<String> getBoqNumberList()
	{
		return rfiServiceDao.getBoqNumberList();
	}
	
	@Override
	public void deleteBoq(BOQ boq)
	{
		rfiServiceDao.deleteBoq(boq);
	}
	
	@Override
	public void addBoq(BOQ boq)
	{
		rfiServiceDao.addBoq(boq);
	}
	
	@Override
	public RFI getRFI(String rfiNumber) {
		return rfiServiceDao.getRFI(rfiNumber);
	}
	
	@Override
	public void editBoq(BOQ boq)
	{
		rfiServiceDao.editBoq(boq);
	}
	
	@Override
	public List<ExpandedRFI> getMultipleRFI(String[] rfiNumberList)
	{
		return rfiServiceDao.getMultipleRFI(rfiNumberList);
	}
	
	@Override
	public Map<String, List<BOQ>> getBoqList() {
		return rfiServiceDao.getBoqList();
	}
	
	@Override
	public List<String> getCategoryList() {
		return rfiServiceDao.getCategoryList();
	}
	
	@Override
	public List<String> getUnitsList() {
		return rfiServiceDao.getUnitsList();
	}
	
	@Override
	public void edit(RFI rfi) {
		//rfi.setCreatedByUserName("system");
		//rfi.setLastEditedByUserName("system");
		rfiServiceDao.edit(rfi);
		System.out.println("RFI Edited Successfully");
	}
	
	public List<String> getItemsList() {
		List<WorkItem> items = rfiServiceDao.getItemsList();
		ArrayList<String> itemDescriptions = null;
		if (items != null) {
			Iterator<WorkItem> it = items.iterator();
			while (it.hasNext()) {
				if (itemDescriptions == null)
				{
					itemDescriptions = new ArrayList<String>(); 
				}
				WorkItem item = (WorkItem) it.next();
				itemDescriptions.add(item.getItemDescription());
			}
		}
		return itemDescriptions;
	}
	
	public List<String> getSideList() {
		List<Side> sidess = rfiServiceDao.getSideList();
		ArrayList<String> sides = null;
		if (sidess != null) {
			Iterator<Side> it = sidess.iterator();
			while (it.hasNext()) {
				if (sides == null)
				{
					sides = new ArrayList<String>(); 
				}
				Side side = (Side) it.next();
				sides.add(side.getSide());
			}
		}
		return sides;
	}
	
	@Override
	public List<Side> getSideObjectsList() {
		return rfiServiceDao.getSideList();
		
	}
	
	@Override
	public List<Activity> getActivityObjectsList() {
		return rfiServiceDao.getActivityList();
		
	}

	
	public List<String> getLayerList() {
		List<Layer> layerss = rfiServiceDao.getLayerList();
		ArrayList<String> layers = null;
		if (layerss != null) {
			Iterator<Layer> it = layerss.iterator();
			while (it.hasNext()) {
				if (layers == null)
				{
					layers = new ArrayList<String>(); 
				}
				Layer layer = (Layer) it.next();
				layers.add(layer.getLayer());
			}
		}
		return layers;
	}
	
	@Override
	public List<Layer> getLayerObjectsList() {
		return rfiServiceDao.getLayerList();
		
	}
	
	@Override
	public List<String> getGradeList() {
		List<Grade> gradess = rfiServiceDao.getGradeList();
		ArrayList<String> grades = null;
		if (gradess != null) {
			Iterator<Grade> it = gradess.iterator();
			while (it.hasNext()) {
				if (grades == null)
				{
					grades = new ArrayList<String>(); 
				}
				Grade grade = (Grade) it.next();
				grades.add(grade.getGrade());
			}
		}
		return grades;
	}
	
	@Override
	public List<String> getStatusList() {
		List<Status> statusss = rfiServiceDao.getStatusList();
		ArrayList<String> statuss = null;
		if (statusss != null) {
			Iterator<Status> it = statusss.iterator();
			while (it.hasNext()) {
				if (statuss == null)
				{
					statuss = new ArrayList<String>(); 
				}
				Status status = (Status) it.next();
				statuss.add(status.getStatus());
			}
		}
		return statuss;
	}
	
	@Override
	public List<String> getRfiCodeList() {
		List<RFICode> rfiCodess = rfiServiceDao.getRfiCodeList();
		ArrayList<String> rfiCodes = null;
		if (rfiCodess != null) {
			Iterator<RFICode> it = rfiCodess.iterator();
			while (it.hasNext()) {
				if (rfiCodes == null)
				{
					rfiCodes = new ArrayList<String>(); 
				}
				RFICode rfiCode = (RFICode) it.next();
				rfiCodes.add(rfiCode.getRfiCode());
			}
		}
		return rfiCodes;
	}
	
	public List<String> getBillList() {
		List<Bill> billss = rfiServiceDao.getBillList();
		ArrayList<String> bills = new ArrayList<String>();
		bills.add("");
		if (billss != null) {
			Iterator<Bill> it = billss.iterator();
			while (it.hasNext()) {
				if (bills == null)
				{
					//bills = new ArrayList<String>(); 
				}
				Bill bill = (Bill) it.next();
				bills.add(bill.getBillNumber());
			}
		}
		return bills;
	}

	@Override
	public List<RFI> getRfiList() {
		return rfiServiceDao.getRfiList();
	}
	
	@Override
	public void getStatusPercentages(List<RFI> rfiList, Map map)
	{
		String approvedPercentage = null;
		String inProcessPercentage = null;
		String rejectedPercentage = null;
		
		int approvedCounter = 0;
		int inProcessCounter = 0;
		int rejectedCounter = 0;
		Iterator<RFI> rfiListIterator = rfiList.iterator();
		while(rfiListIterator.hasNext()) {
			RFI rfi = (RFI)rfiListIterator.next();
			if("Approved".equals(rfi.getStatus()))
			{
				++approvedCounter;
			}
			else if("In Process".equals(rfi.getStatus()))
			{
				++inProcessCounter;
			}
			else if("Rejected".equals(rfi.getStatus()))
			{
				++rejectedCounter;
			}
		}
		int total = approvedCounter+inProcessCounter+rejectedCounter;
		if(total == 0) {
			map.put("rejectedPercentage", "0%");
			map.put("InProcessPercentage", "0%");
			map.put("ApprovedPercentage", "0%");
		}
		else {
			approvedPercentage = ((approvedCounter*100/total))+"%";
			inProcessPercentage = ((inProcessCounter*100/total))+"%";
			rejectedPercentage = (100 - ((approvedCounter*100/total)) - ((inProcessCounter*100/total)))+"%"; 
		}
		map.put("rejectedPercentage", rejectedPercentage);
		map.put("InProcessPercentage", inProcessPercentage);
		map.put("ApprovedPercentage", approvedPercentage);
	}
	
	
	@Override
	public List<RFI> getRfiAllDetailsList() {
		return null;
		//return rfiServiceDao.getRfiAllDetailsList();
	}
	
	@Override
	public Integer getNextRfiNumber() {
		return rfiServiceDao.getNextRfiNumber();
		
	}
	
	@Override
	public BigInteger getStatusCount(String status, String where) {
		return rfiServiceDao.getStatusCount(status, where);
		
	}
	
	
	@Override
	public List<ExpandedRFI> getExpandedRfiList()
	{
	 return rfiServiceDao.getExpandedRfiList();	
	}
	
	@Override
	public Integer getExpandedRfiListSize(String whereClause) {
		// TODO Auto-generated method stub
		return rfiServiceDao.getExpandedRfiListSize(whereClause);
	}
	
	@Override
	public List<ExpandedRFI> getExpandedRfiList(int posStart, int count, String orderBy, String where) {
		// TODO Auto-generated method stub
		return rfiServiceDao.getExpandedRfiList(posStart, count, orderBy, where);
	}
	
	@Override
	public List<ExpandedRFI> getExpandedRfiBillList(int posStart, int count, String orderBy, String where) {
		// TODO Auto-generated method stub
		return rfiServiceDao.getExpandedRfiBillList(posStart, count, orderBy, where);
	}

	/**
	 * @param rfiServiceDao the rfiServiceDao to set
	 */
	public void setRfiServiceDao(RFIServiceDAO rfiServiceDao) {
		this.rfiServiceDao = rfiServiceDao;
	}
	
	public static String getDateAsSimpleText(Date issueDate) {
		// TODO Auto-generated method stub
		if(issueDate != null)
		{
			return issueDateFormat.format(issueDate);
		}
		else
		{
			return null;
		}
	}
	
	public static String getInspectionDateAsText(Date inspectionDate) {
		// TODO Auto-generated method stub
		if(inspectionDate != null)
		{
			return inspectionDateFormat.format(inspectionDate);
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void editStatus(String oldStatus, String newStatus)
	{
		rfiServiceDao.editStatus(oldStatus, newStatus);
	}
	
	@Override
	public void addStatus(Status status)
	{
		rfiServiceDao.addStatus(status);
	}
	
	@Override
	public void deleteStatus(Status status)
	{
		rfiServiceDao.deleteStatus(status);
	}
	
	@Override
	public void editGrade(String oldGrade, String newGrade)
	{
		rfiServiceDao.editGrade(oldGrade, newGrade);
	}
	
	@Override
	public void addGrade(Grade grade)
	{
		rfiServiceDao.addGrade(grade);
	}
	
	@Override
	public void deleteGrade(Grade grade)
	{
		rfiServiceDao.deleteGrade(grade);
	}

	
	@Override
	public void addSide(Side side)
	{
		rfiServiceDao.addSide(side);
	}
	
	@Override
	public void deleteSide(Side side)
	{
		rfiServiceDao.deleteSide(side);
	}
	
	@Override
	public void editSide(String oldSide, String newSide)
	{
		rfiServiceDao.editSide(oldSide, newSide);
	}
	
	
	@Override
	public void addLayer(Layer layer)
	{
		rfiServiceDao.addLayer(layer);
	}
	
	@Override
	public void deleteLayer(Layer layer)
	{
		rfiServiceDao.deleteLayer(layer);
	}
	
	@Override
	public void editLayer(String oldLayer, String newLayer)
	{
		rfiServiceDao.editLayer(oldLayer, newLayer);
	}
	
	@Override
	public List<WorkItem> getWorkItemsList()
	{
		return rfiServiceDao.getWorkItemsList();
	}
	
	@Override
	public void addWorkItem(WorkItem workItem)
	{
		rfiServiceDao.addWorkItem(workItem);
	}
	
	@Override
	public void editWorkItem(WorkItem workItem)
	{
		rfiServiceDao.editWorkItem(workItem);
	}
	
	@Override
	public void deleteWorkItem(WorkItem workItem)
	{
		rfiServiceDao.deleteWorkItem(workItem);
	}
	
	@Override
	public WorkItem getWorkItem(String itemDescription)
	{
		return rfiServiceDao.getWorkItem(itemDescription);
	}
	
	@Override
	public void addRFICode(RFICode rfiCode)
	{
		rfiServiceDao.addRFICode(rfiCode);
	}
	
	@Override
	public void deleteRFICode(RFICode rfiCode)
	{
		rfiServiceDao.deleteRFICode(rfiCode);
	}
	
	@Override
	public void editRFICode(String oldRFICode, String newRFICode)
	{
		rfiServiceDao.editRFICode(oldRFICode, newRFICode);
	}
	
	public List<String> getWorkItemDescriptionList()
	{
		return rfiServiceDao.getWorkItemDescriptionList();
	}
	
	@Override
	public File uploadRFIFile(File file) {
		
		File errorsFile = new File("C:\\temp\\errors_123"+".txt");
		FileWriter fos = null;
		try
		{
			
			fos = new FileWriter(errorsFile);
			try
			{
				Workbook workbook = Workbook.getWorkbook(file);
				Sheet sheet = workbook.getSheet(0);
				HashSet<RFI> rfiHash = new HashSet<RFI>();
				existingSideHash = new HashSet<Side>(rfiServiceDao.getSideList());
				existingActivityHash = new HashSet<Activity>(rfiServiceDao.getActivityList());
				existingLayerHash = new HashSet<Layer>(rfiServiceDao.getLayerList());
				existingBoqHash = new HashSet<BOQ>(rfiServiceDao.getCompleteBoqList());
				System.out.println(existingBoqHash);
				existingWorkItemHash = new HashSet<WorkItem>(rfiServiceDao.getItemsList());
				existingGradeHash = new HashSet<Grade>(rfiServiceDao.getGradeList());
				existingStatusHash = new HashSet<Status>(rfiServiceDao.getStatusList());
				existingRfiCodeHash = new HashSet<RFICode>(rfiServiceDao.getRfiCodeList());
				
				HashSet<Side> sideHash = new HashSet<Side>();
				HashSet<Activity> activityHash = new HashSet<Activity>();
				HashSet<Layer> layerHash = new HashSet<Layer>();
				HashSet<WorkItem> workItemHash = new HashSet<WorkItem>();
				HashSet<Grade> gradeHash = new HashSet<Grade>();
				HashSet<Status> statusHash = new HashSet<Status>();
				HashSet<RFICode> rfiCodeHash = new HashSet<RFICode>();
				HashSet<BOQ> boqHash = new HashSet<BOQ>();
				
				System.out.println("*********Outside the loop************");
				System.out.println(sideHash);
				
				int errorFound = 0;
				for (int i=1;i < sheet.getRows();++i)
					{
						try
						{
							
							int result = readAndAddRFI(sheet, rfiHash, sideHash, gradeHash, rfiCodeHash, statusHash, layerHash, boqHash, activityHash, workItemHash, i, fos);
							if( result == 2)
							{
								continue;
							}
							
							else if( result == 0)
							{
								errorFound = 1;
								continue;
							}
							
							else
							{
								break;
							}
						}
						catch (Exception ex)
						{
							errorFound = 1;
							System.out.println("*********Error reading record "+i+"************");
						}
					}
					if(errorFound == 0)
					{
						rfiServiceDao.importRfiHashes(rfiHash, sideHash, gradeHash, rfiCodeHash, statusHash, layerHash, boqHash, activityHash, workItemHash);
					}
					
	
					System.out.println("*********Program Finished************");
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				if(fos != null)
				{
					fos.write("Error importing the file. Please note the imported file should be in the prescribed excel Format. You can import the excel format from Edit rfi page. If everything is correct, please contact support.\n");
				}
				
			}
			finally
			{
				if(fos != null)
				{
					fos.close();
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			
		}
		return errorsFile;
	}
	

private int readAndAddRFI(Sheet sheet, Set<RFI> rfiHash, Set<Side> sideHash, Set<Grade> gradeHash, Set<RFICode> rfiCodeHash, Set<Status> statusHash, Set<Layer> layerHash, Set<BOQ> boqHash, Set<Activity> activityHash, Set<WorkItem> workItemHash, int i, FileWriter fos) throws Exception
{
	
	RFI rfi = new RFI();
	rfi.setBreakRfiString("");
	RFIApproval rfiApproval = new RFIApproval();
	
	String rfiCode = sheet.getCell(0,i).getContents(); 
	if(rfiCode == null || StringUtils.isEmptyOrWhitespaceOnly(rfiCode))
	{
		return 1;
	}
	else
	{
		RFICode rfiCode1 = new RFICode();
		rfiCode1.setRfiCode(rfiCode.trim());
		if(!rfiCodeHash.contains(rfiCode1) && !existingRfiCodeHash.contains(rfiCode1))
		{
			rfiCodeHash.add(rfiCode1);
		}
		
		rfi.setRfiCode(rfiCode.trim());
	}

	
	
	Cell approvedFromCell = sheet.getCell(16,i);
	String approvedFromCellContents = sheet.getCell(16,i).getContents();
	if(!StringUtils.isEmptyOrWhitespaceOnly(approvedFromCellContents))
	{
		if (approvedFromCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) approvedFromCell; 
			int approvedFrom = (int)nc.getValue();
			rfiApproval.setApprovedFrom(approvedFrom);
		}
		else
		{
			//approvedFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because From Chainage is not in the number format.\n");
			return 0;
		}
	}
	
	Cell approvedToCell = sheet.getCell(17,i);
	String approvedToCellContents = sheet.getCell(17,i).getContents();
	if(!StringUtils.isEmptyOrWhitespaceOnly(approvedToCellContents))
	{
		if (approvedToCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) approvedToCell; 
			int approvedTo = (int)nc.getValue();
			rfiApproval.setApprovedTo(approvedTo);
		}
		else
		{
			//approveTo Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because To Chainage is not in the number format.\n");
			return 0;
		}
	}
	
	if(rfiApproval.getApprovedFrom() != null || rfiApproval.getApprovedTo() != null)
	{
		if(rfiApproval.getApprovedFrom() == null)
		{
			fos.write("Record No. "+i+" - is not imported because Approved From Chainage cannot be blank.\n");
			return 0;
		}
		
		if(rfiApproval.getApprovedTo() == null)
		{
			fos.write("Record No. "+i+" - is not imported because Approved To Chainage cannot be blank.\n");
			return 0;
		}
		
		if(rfiApproval.getApprovedFrom() > rfiApproval.getApprovedTo())
		{
			fos.write("Record No. "+i+" - is not imported because Approved To Chainage should be equal to or greater than Approved From Chainage.\n");
			return 0;
		}
	
		String approvedSide = sheet.getCell(18,i).getContents(); 
		if(approvedSide == null || StringUtils.isEmptyOrWhitespaceOnly(approvedSide))
		{
			fos.write("Record No. "+i+" - is not imported because Approved Side cannot be blank.\n");
			return 0;
		}
	
		else
		{
			Side side1 = new Side();
			side1.setSide(approvedSide.trim());
			side1.setSideDescription(approvedSide.trim());
			if(!sideHash.contains(side1) && !existingSideHash.contains(side1))
			{
				sideHash.add(side1);
			}
			
			rfiApproval.setApprovedSide(approvedSide);
		}
	
		String approvedLayer = sheet.getCell(19,i).getContents(); 
		if(approvedLayer == null || StringUtils.isEmptyOrWhitespaceOnly(approvedLayer))
		{
			fos.write("Record No. "+i+" - is not imported because Approved Layer cannot be blank.\n");
			return 0;
		}
		else
		{
			Layer layer1 = new Layer();
			layer1.setLayer(approvedLayer);
			if(!layerHash.contains(layer1) && !existingLayerHash.contains(layer1))
			{
				try
				{
					int intLayer = Integer.parseInt(approvedLayer);
				}
				catch (Exception ex)
				{
					fos.write("Record No. "+i+" - is not imported because Approved Layer should be a number.\n");
					return 0;
				}
				
				layerHash.add(layer1);
			}
			rfiApproval.setApprovedLayer(approvedLayer.trim());
		}
	
		Cell approvalDateCell = sheet.getCell(20,i);
		String approvalDateCellContents = sheet.getCell(20,i).getContents();
		if(!StringUtils.isEmptyOrWhitespaceOnly(approvalDateCellContents))
		{
			if (approvalDateCell.getType() == CellType.DATE) 
			{ 
			  DateCell dc = (DateCell) approvalDateCell; 
			  Date approvalDate = dc.getDate(); 
			
			  if(approvalDate == null)
			  {
				//Write in Log for the row details not saved
				//return 0;
			  }
			  else
			  {
				rfiApproval.setApprovalDate(approvalDate);
			  }
			}
			else
			{
				//Approval date is not in the date Format
				fos.write("Record No. "+i+" - is not imported because Approval date ("+approvalDateCellContents+") is not in the date format.\n");
				return 0;
			}
		}
	
	
		System.out.println("*********Inside the loop2************");
		String rfiNumber = sheet.getCell(1,i).getContents(); 
		if(rfiNumber == null || StringUtils.isEmptyOrWhitespaceOnly(rfiNumber))
		{
			fos.write("Record No. "+i+" - RFI Number cannot be blank.\n");
			return 0;
		}
		else
		{
			rfi.setRfiNumber(rfiNumber);
			if(rfiHash.contains(rfi))
			{
				Iterator<RFI> it = rfiHash.iterator();
				while(it.hasNext())
				{
					RFI containedRfi = it.next();
					if(rfi.equals(containedRfi) && containedRfi.getStatus().trim().equalsIgnoreCase("Approved"))
					{
						containedRfi.addApproval(rfiApproval);
					}
				}
				return 2;
			}
		}
	}
	System.out.println("*********Inside the loop3************");
	Cell issueDateCell = sheet.getCell(2,i); 
	String issueDateCellContents = sheet.getCell(2,i).getContents();
	if(!StringUtils.isEmptyOrWhitespaceOnly(issueDateCellContents))
	{
		if (issueDateCell.getType() == CellType.DATE) 
		{ 
		  DateCell dc = (DateCell) issueDateCell; 
		  Date issueDate = dc.getDate(); 
		
		  if(issueDate == null)
		  {
			//Write in Log for the row details not saved
			//return 0;
		  }
		  else
		  {
			rfi.setIssueDate(issueDate);
		  }
		}
		else
		{
			//Issue date is not in the date Format
			fos.write("Record No. "+i+" - is not imported because Issue date is not in the date format.\n");
			System.out.println("Record no. " + i + ", Issued date is not in date format");
		}
	}
	System.out.println("*********Inside the loop4************");
	
	String boqNumber = sheet.getCell(3,i).getContents(); 
	if(boqNumber == null || StringUtils.isEmptyOrWhitespaceOnly(boqNumber))
	{
		fos.write("Record No. "+i+" - is not imported because BOQ Number cannot be blank.");
		return 0;
	}
	else
	{
		BOQ boq = new BOQ();
		boq.setBoqNumber(boqNumber.trim());
		boq.setCategory("Unknown");
		boq.setCategorySequence(0);
		boq.setDescription("Unknown");
		if(!existingBoqHash.contains(boq) && !boqHash.contains(boq))			
		{
			boqHash.add(boq);
		}
	}
	
	WorkItem workItem = null;
	String itemDescription = sheet.getCell(4,i).getContents(); 
	if(itemDescription == null || StringUtils.isEmptyOrWhitespaceOnly(itemDescription))
	{
		//Write in Log for the row details not saved
		fos.write("Record No. "+i+" - is not imported because Item Description cannot be empty.\n");
		return 0;
	}
	else
	{
		workItem = new WorkItem();
		
		workItem.setItemDescription(itemDescription);
		if(workItemHash.contains(workItem))
		{
			workItem = workItemMap.get(workItem.getItemDescription().toLowerCase().trim());
			
		}
		else if(existingWorkItemHash.contains(workItem))
		{
			workItem = rfiServiceDao.getWorkItem(itemDescription);
			//workItemHash.add(workItem);
		}
		else
		{
			workItemHash.add(workItem);
			workItemMap.put(workItem.getItemDescription().toLowerCase().trim(), workItem);
		}
		workItem.setBoqNumber(boqNumber);
		
		
		rfi.setItemDescription(itemDescription.trim());
	}
	
	
	String wiRemarks = sheet.getCell(5,i).getContents(); 
	if(wiRemarks == null || StringUtils.isEmptyOrWhitespaceOnly(wiRemarks))
	{
		//Write in Log for the row details not saved
		//return 0;
	}
	else
	{
		rfi.setWiRemarks(wiRemarks.trim());
	}
	
	
	String activity = sheet.getCell(6,i).getContents(); 
	if(activity == null || StringUtils.isEmptyOrWhitespaceOnly(activity))
	{
		fos.write("Record No. "+i+" - is not imported because Activity cannot be blank.\n");
		return 0;
	}
	else
	{
		
		Activity activity1 = new Activity();
		activity1.setActivity(activity.trim());
		
		if (workItem != null)
		{
			workItem.addActivity(activity.trim());
		}
		
		if(!activityHash.contains(activity1) && !existingActivityHash.contains(activity1))
		{
			activityHash.add(activity1);
		}
		
		rfi.setActivity(activity);
	}
	
	
	Cell fromChainageCell = sheet.getCell(7,i); 
	String fromChainageCellContents = sheet.getCell(7,i).getContents();
	if(fromChainageCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(fromChainageCellContents))
	{
		fos.write("Record No. "+i+" - is not imported because From Chainage should not be blank.\n");
		return 0;
	}
	if(!StringUtils.isEmptyOrWhitespaceOnly(fromChainageCellContents))
	{
		if (fromChainageCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) fromChainageCell; 
			int fromChainage = (int)nc.getValue();
			rfi.setFromChainage(fromChainage);
		}
		else
		{
			//approveFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because From Chainage is not in the number format.\n");
			return 0;
		}
	}
	
	Cell toChainageCell = sheet.getCell(8,i);
	String toChainageCellContents = sheet.getCell(8,i).getContents();
	if(toChainageCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(toChainageCellContents))
	{
		fos.write("Record No. "+i+" - is not imported because To Chainage should not be blank.\n");
		return 0;
	}
	if(!StringUtils.isEmptyOrWhitespaceOnly(toChainageCellContents))
	{
		if (toChainageCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) toChainageCell; 
			int toChainage = (int)nc.getValue();
			rfi.setToChainage(toChainage);
		}
		else
		{
			//approveTo Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because To Chainage is not in the number format.\n");
			return 0;
		}
	}
	
	String side = sheet.getCell(9,i).getContents(); 
	if(side == null || StringUtils.isEmptyOrWhitespaceOnly(side))
	{
		fos.write("Record No. "+i+" - is not imported because Side cannot be blank.\n");
		return 0;
	}
	else
	{
		
		Side side1 = new Side();
		side1.setSide(side.trim());
		side1.setSideDescription(side.trim());
		
		if (workItem != null)
		{
			workItem.addSide(side.trim());
		}
		
		if(!sideHash.contains(side1) && !existingSideHash.contains(side1))
		{
			sideHash.add(side1);
		}
		
		rfi.setSide(side);
	}
	
	
	String layer = sheet.getCell(10,i).getContents(); 
	if(layer == null || StringUtils.isEmptyOrWhitespaceOnly(layer))
	{
		fos.write("Record No. "+i+" - is not imported because Layer cannot be blank.\n");
		return 0;
	}
	else
	{
		Layer layer1 = new Layer();
		layer1.setLayer(layer.trim());
		
		if (workItem != null)
		{
			workItem.addLayer(layer.trim());
		}
		
		if(!layerHash.contains(layer1) && !existingLayerHash.contains(layer1))
		{
			try
			{
				int intLayer = Integer.parseInt(layer);
			}
			catch (Exception ex)
			{
				fos.write("Record No. "+i+" - is not imported because Approved Layer should be a number.\n");
				return 0;
			}
			
			layerHash.add(layer1);
		}
		
		rfi.setLayer(layer);
	}
	
	String status = sheet.getCell(11,i).getContents(); 
	if(status == null || StringUtils.isEmptyOrWhitespaceOnly(status))
	{
		fos.write("Record No. "+i+" - is not imported because Status cannot be blank.\n");
		return 0;
	}
	else
	{
		Status status1 = new Status();
		status1.setStatus(status.trim());
		if(!statusHash.contains(status1) && !existingStatusHash.contains(status1))
		{
			statusHash.add(status1);
		}
		
		rfi.setStatus(status.trim());
	}

	String grade = sheet.getCell(12,i).getContents(); 
	if(grade == null || StringUtils.isEmptyOrWhitespaceOnly(grade))
	{
		//fos.write("Record No. "+i+" - is not imported because Grade cannot be blank.\n");
		//return 0;
	}
	else
	{
		Grade grade1 = new Grade();
		grade1.setGrade(grade.trim());
		if(!gradeHash.contains(grade1) && !existingGradeHash.contains(grade1))
		{
			gradeHash.add(grade1);
		}
		
		rfi.setGrade(grade.trim());
	}
	System.out.println("*********Inside the loop5************");
	Cell inspectionDateCell = sheet.getCell(13,i); 
	String inspectionDateCellContents = sheet.getCell(13,i).getContents();
	if(!StringUtils.isEmptyOrWhitespaceOnly(inspectionDateCellContents))
	{
		if (inspectionDateCell.getType() == CellType.DATE) 
		{ 
		  DateCell dc = (DateCell) inspectionDateCell; 
		  Date inspectionDate = dc.getDate(); 
		
		  if(inspectionDate == null)
		  {
			//Write in Log for the row details not saved
			//return 0;
		  }
		  else
		  {
			rfi.setInspectionDate(inspectionDate);
		  }
		}
		else
		{
			//Inspection date is not in the date Format
			fos.write("Record No. "+i+" - is not imported because Inspection date is not in the number format.\n");
			return 0;
		}
	}
	
	String createdByUsername = sheet.getCell(14,i).getContents(); 
	if(createdByUsername == null || StringUtils.isEmptyOrWhitespaceOnly(createdByUsername))
	{
		//Write in Log for the row details not saved
		//return 0;
	}
	else
	{
		rfi.setCreatedByUserName(createdByUsername.trim());
	}
	
	String lastUpdatedByUsername = sheet.getCell(15,i).getContents(); 
	if(lastUpdatedByUsername == null || StringUtils.isEmptyOrWhitespaceOnly(lastUpdatedByUsername))
	{
		//Write in Log for the row details not saved
		//return 0;
	}
	else
	{
		rfi.setLastEditedByUserName(lastUpdatedByUsername.trim());
	}
	
	
	
	
	String billNumber = sheet.getCell(21,i).getContents(); 
	if(billNumber == null || StringUtils.isEmptyOrWhitespaceOnly(billNumber))
	{
		//Write in Log for the row details not saved
		//return 0;
	}
	else
	{
		rfi.setBillNumber(billNumber.trim());
	}
	
	String remarks = sheet.getCell(22,i).getContents(); 
	if(remarks == null || StringUtils.isEmptyOrWhitespaceOnly(remarks))
	{
		//Write in Log for the row details not saved
		//return 0;
	}
	else
	{
		rfi.setRemarks(remarks.trim());
	}
	
	if(rfi.getStatus().trim().equals("Approved"))
		rfi.addApproval(rfiApproval);
	rfiHash.add(rfi);
	System.out.println("*********End of the Loop************");
	return 2;
//			i++;
}

@Override
public void createNewUser(String role, String username, String firstName, String lastName, String password)
{
	rfiServiceDao.createNewUser(role, username, firstName, lastName, password);
}

@Override
public List<String> getWorkItemsDescription(String where)
{
	return rfiServiceDao.getWorkItemsDescription(where);
}

@Override
public List<String> getBoqNumberList(String where)
{
	return rfiServiceDao.getBoqNumberList(where);
}

@Override
public List<String> getLayersForWorkItem(String itemDescription)
{
	return rfiServiceDao.getLayersForWorkItem(itemDescription);
}

@Override
public List<String> getItemsInSequence(String[] items)
{
	return rfiServiceDao.getItemsInSequence(items);
}

@Override
public List<Object[]> getTopLayerTableEntry(String item, int from, int to, String side)
{
	return rfiServiceDao.getTopLayerTableEntry(item, from, to, side);
}

@Override
public void addBarChart(BarChart barchart)
{
	rfiServiceDao.addBarChart(barchart);
}

@Override
public List<String> getStatusBarChartNames()
{
	return rfiServiceDao.getStatusBarChartNames();
}

@Override
public BarChart getStatusBarChart(String barChartName)
{
	return rfiServiceDao.getStatusBarChart(barChartName);
}

@Override
public void addLayerException(LayerException layerException)
{
	rfiServiceDao.addLayerException(layerException);
}

@Override
public Integer getLayerExceptionsListSize(String whereClause)
{
	return rfiServiceDao.getLayerExceptionsListSize(whereClause);
}

@Override
public List<LayerException> getLayerExceptionsList(int posStart, int count, String orderBy, String where)
{
	return rfiServiceDao.getLayerExceptionsList(posStart, count, orderBy, where);
}

@Override
public void deleteLayerExceptions(Long exceptionId)
{
	rfiServiceDao.deleteLayerExceptions(exceptionId);
}

@Override
public void addQuantity(Quantity quantity)
{
	rfiServiceDao.addQuantity(quantity);
}

@Override
public void editQuantity(Quantity quantity)
{
	rfiServiceDao.editQuantity(quantity);
}

@Override
public void deleteQuantity(Quantity quantity)
{
	rfiServiceDao.deleteQuantity(quantity);
}

@Override
public Integer getQuantityListSize(String whereClause)
{
	return rfiServiceDao.getQuantityListSize(whereClause);
}

@Override
public List<Quantity> getQuantityList(int posStart, int count, String orderBy, String where)
{
	return rfiServiceDao.getQuantityList(posStart, count, orderBy, where);
}

@Override
public Boolean isBillUnderPreparation()
{
	return rfiServiceDao.isBillUnderPreparation();
}

@Override
public void changeMultipleRFIBillNumber(String[] rfiNumberList, String billNumber)
{
	rfiServiceDao.changeMultipleRFIBillNumber(rfiNumberList, billNumber);
}

@Override
public List<Object[]> getPreparedItems()
{
	return rfiServiceDao.getPreparedItems();
}

@Override
public List<Object[]> getItemsForRFINumbers(Set rfiNumberSet, String billNumber)
{
	return rfiServiceDao.getItemsForRFINumbers(rfiNumberSet, billNumber);
}

@Override
public Quantity getQuantity(Long quantityId)
{
	return rfiServiceDao.getQuantity(quantityId);
}

@Override
public void addQuantityList(Set<Quantity> quantityHash)
{
	rfiServiceDao.addQuantityList(quantityHash);
}

@Override
public void deleteQuantity(Long quantityId)
{
	rfiServiceDao.deleteQuantity(quantityId);
}

@Override
public void editLayerExceptions(LayerException layerException)
{
	rfiServiceDao.editLayerExceptions(layerException);
}

@Override
public LayerException getLayerException(Long exceptionId)
{
	return rfiServiceDao.getLayerException(exceptionId);
}

@Override
public void addLayerExceptionList(Set<LayerException> layerExceptionHash)
{
	rfiServiceDao.addLayerExceptionList(layerExceptionHash);
}

@Override
public void deleteActivity(Activity activity)
{
	rfiServiceDao.deleteActivity(activity);
}

@Override
public void editActivity(String oldActivity, String newActivity)
{
	rfiServiceDao.editActivity(oldActivity, newActivity);
}

@Override
public List<String> getActivityList() {
	List<Activity> activityss = rfiServiceDao.getActivityList();
	ArrayList<String> activitys = null;
	if (activityss != null) {
		Iterator<Activity> it = activityss.iterator();
		while (it.hasNext()) {
			if (activitys == null)
			{
				activitys = new ArrayList<String>(); 
			}
			Activity activity = (Activity) it.next();
			activitys.add(activity.getActivity());
		}
	}
	return activitys;
}

@Override
public void addActivity(Activity activity)
{
	rfiServiceDao.addActivity(activity);
}

@Override
public List<String> getActivityWorkItemsDescription(String where)
{
	return rfiServiceDao.getActivityWorkItemsDescription(where);
}

@Override
public WorkItem getActivityWorkItem(String itemDescription, String activity)
{
	return rfiServiceDao.getActivityWorkItem(itemDescription, activity);
}

@Override
public List<String> getActivityBoqNumberList(String where)
{
	return rfiServiceDao.getActivityBoqNumberList(where);
}

@Override
public LinkedHashMap formItemMap(List<Object[]> preparedItemsList)
{
	LinkedHashMap itemMap = new LinkedHashMap();
	 if (preparedItemsList != null)
	 {
		 Iterator<Object[]> it = preparedItemsList.iterator();
		 while(it.hasNext())
		 {
			 Object[] itemObject = it.next();
			 if(itemObject != null)
			 {
				 String activity = (String) itemObject[0];
				 String item = (String) itemObject[1];
				 String side = (String) itemObject[2];
				 String layer = (String) itemObject[3];
				 
				 int sideLoop = 1;
				 if("BS".equalsIgnoreCase(side.trim()))
				 {
					 sideLoop = 2;
				 }
				 
				 for (int counter = 0; counter<sideLoop; ++counter)
				 {
					 if (counter == 0 && sideLoop == 2)
					 {
						 side = "LHS";
					 }
					 if (counter == 1 && sideLoop == 2)
					 {
						 side = "RHS";
					 }
					 if(!itemMap.containsKey(item))
					 {
						 itemMap.put(item, new LinkedHashMap());
					 }
					 LinkedHashMap activityMap = (LinkedHashMap)itemMap.get(item);
					 if(!activityMap.containsKey(activity))
					 {
						 activityMap.put(activity, new LinkedHashMap());
					 }
					 LinkedHashMap sideMap = (LinkedHashMap)activityMap.get(activity);
					 
					 if(!sideMap.containsKey(side))
					 {
						 sideMap.put(side, new LinkedHashMap());
					 }
					 LinkedHashMap layerMap = (LinkedHashMap)sideMap.get(side);
					 if(!layerMap.containsKey(layer))
					 {
						 layerMap.put(layer, new ArrayList<RFIBill>());
					 }
				 }
			}
		}
	 }
	 
	 return itemMap;
}



@Override
public LinkedHashMap prepareBill(String viewString) throws Exception
{
	synchronized (RFIBill.class) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		List<Object[]> preparedItemsList = this.getPreparedItems();
		 LinkedHashMap itemMap = formItemMap(preparedItemsList);
		 
			
		
		Iterator itemMapIterator = itemMap.entrySet().iterator();
		while (itemMapIterator.hasNext())
		{
			Map.Entry<String, LinkedHashMap> itemEntry = (Map.Entry<String, LinkedHashMap>)itemMapIterator.next();
			String item = itemEntry.getKey();
			LinkedHashMap activityMap = itemEntry.getValue();
			Iterator activityMapIterator = activityMap.entrySet().iterator();
			while (activityMapIterator.hasNext())
			{
				Map.Entry<String, LinkedHashMap> activityEntry = (Map.Entry<String, LinkedHashMap>)activityMapIterator.next();
				String activity = activityEntry.getKey();
			
				LinkedHashMap sideMap = activityEntry.getValue();
				
				
				Iterator sideMapIterator = sideMap.entrySet().iterator();
				while (sideMapIterator.hasNext())
				{
					Map.Entry<String, LinkedHashMap> sideEntry = (Map.Entry<String, LinkedHashMap>)sideMapIterator.next();
					String side = sideEntry.getKey();
					LinkedHashMap layerMap = sideEntry.getValue();
					
					Iterator layerMapIterator = layerMap.entrySet().iterator();
					while (layerMapIterator.hasNext())
					{
						Map.Entry<String, List<RFIBill>> layerEntry = (Map.Entry<String, List<RFIBill>>)layerMapIterator.next();
						String layer = layerEntry.getKey();
						List<RFIBill> rfiBillList = layerEntry.getValue();
						int addBS = 0;
						if ("LHS".equalsIgnoreCase(side.trim()) || "RHS".equalsIgnoreCase(side.trim()))
						{
							addBS = 1;
						}
						String where = "";
						if (addBS == 0)
							where = " where bill_number IS NOT NULL AND bill_number <> '' AND activity = '"+activity+"' AND item_description = '"+item+"' and approved_side = '"+side+"' and approved_layer in ('"+layer+"', 'Top')";
						else
							where = " where bill_number IS NOT NULL AND bill_number <> '' AND activity = '"+activity+"' AND item_description = '"+item+"' and approved_side in ('"+side+"', 'BS') and approved_layer in ('"+layer+"', 'Top')";
						List<ExpandedRFI> expandedRfiList = this.getExpandedRfiBillList(0, 0, " order by approved_from, approved_to ", where);
				 
						Iterator<ExpandedRFI> itList = expandedRfiList.listIterator();
				 
						//ExpandedRFI[] rfiArray  = (ExpandedRFI [])expandedRfiList.toArray(new ExpandedRFI[0]);
				 
						for (int i = 0; i < expandedRfiList.size(); ++i)
						{
							StringBuffer rfiCalculationString = new StringBuffer("");
							ExpandedRFI expRfi1 = null;
							ExpandedRFI expRfi2 = null;
							ExpandedRFI expRfi3 = null;
					 
							expRfi2 = expandedRfiList.get(i);
							
							
							if(i > 0)
							{
								expRfi1 = expandedRfiList.get(i-1);
							}
					 
							if (i <= (expandedRfiList.size()-2))
							{
								expRfi3 = expandedRfiList.get(i+1);
							}
							
							if(!layer.equals("Top"))
							{
								if(expRfi1 != null && expRfi1.getApprovedLayer().equals("Top"))
								{
									expRfi1.setBillNumber("Checking Overlap");
								}
								
								if(expRfi2 != null && expRfi2.getApprovedLayer().equals("Top"))
								{
									expRfi2.setBillNumber("Checking Overlap");
								}
								
								if(expRfi3 != null && expRfi3.getApprovedLayer().equals("Top"))
								{
									expRfi3.setBillNumber("Checking Overlap");
								}
							}
					 
							if(expRfi2 != null )
							{
								boolean overlapFound = false;
								if(expRfi1 != null && "Under Preparation".equals(expRfi2.getBillNumber()) && expRfi2 != null)
								{
									Integer approvedFrom1 = expRfi1.getApprovedFrom();
									Integer approvedTo1 = expRfi1.getApprovedTo();
							 
									Integer approvedFrom2 = expRfi2.getApprovedFrom();
									Integer approvedTo2 = expRfi2.getApprovedTo();
							 
									if(approvedFrom2 < approvedTo1)
									{
										
										expRfi2.setApprovedFrom(approvedTo1);
										overlapFound = true;
										if(approvedTo1 >= approvedTo2)
										{
											expRfi2.setApprovedTo(approvedTo1);
											overlapFound = true;
										}
									}
								}
						 
								if(expRfi2 != null && expRfi3 != null)
								{
									Integer approvedFrom3 = expRfi3.getApprovedFrom();
									Integer approvedTo3 = expRfi3.getApprovedTo();
							 
									Integer approvedFrom2 = expRfi2.getApprovedFrom();
									Integer approvedTo2 = expRfi2.getApprovedTo();
							 
									if((approvedTo2 > approvedFrom3) && (approvedFrom3 >= approvedFrom2) && "Under Preparation".equals(expRfi2.getBillNumber()))
									{
										expRfi2.setApprovedTo(approvedFrom3);
										overlapFound = true;
									}
									
									if(approvedTo2 > approvedTo3)
									{
										ExpandedRFI newExpRfi = expRfi2.clone();
										newExpRfi.setApprovedFrom(approvedTo3);
										newExpRfi.setApprovedTo(approvedTo2);
										expandedRfiList.add(i+2, newExpRfi);
									}
								}
								
								if("Under Preparation".equals(expRfi2.getBillNumber()) && expRfi2.getApprovedLayer().trim().equals(layer) && !(overlapFound == true && (expRfi2.getApprovedFrom() >= expRfi2.getApprovedTo())))
								{
								boolean directQuantityFound = false;
								if(expRfi2.getQuantity() != null)
								{
									directQuantityFound = true;
									rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td></tr>");
									//rfiCalculationString.append("<tr><td>"+expRfi2.getRfiNumber()+"</td><td>"+expRfi2.getApprovedFrom()+"</td><td>"+expRfi2.getApprovedTo()+"</td><td>"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td>"+(expRfi2.getBreadth() != null?+expRfi2.getBreadth():"")+"<td/><td/><td>"+(expRfi2.getDepth() != null?+expRfi2.getDepth():"")+"</td><td>"+(expRfi2.getArea() != null?+expRfi2.getArea():"")+"</td></tr>");
								}
								else if(expRfi2.getArea() != null)
								{
									directQuantityFound = true;
									expRfi2.setQuantity(expRfi2.getArea()*(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom()));
									rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getArea())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td></tr>");
								}
								else if(expRfi2.getBreadth() != null && expRfi2.getDepth() != null)
								{
									directQuantityFound = true;
									expRfi2.setQuantity(expRfi2.getBreadth()*expRfi2.getDepth()*(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom()));
									rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getBreadth())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getDepth())+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td></tr>");
								}
								
								else if((!"viewDeduction".equals(viewString)) && expRfi2 != null && (!(expRfi2.getQuantity() != null || expRfi2.getArea() != null || (expRfi2.getBreadth() !=null && expRfi2.getDepth() != null)))  && expRfi2.getApprovedFrom() != null && expRfi2.getApprovedTo() != null)
								{
									//find quantity
									String whereC = " where activity = '"+activity+"' AND item_description = '"+item+"' and side='"+side+"' and layer = '"+layer+"' and ((from_chainage >= "+expRfi2.getApprovedFrom()+" and from_chainage < "+expRfi2.getApprovedTo()+" ) or (to_chainage > "+expRfi2.getApprovedFrom()+" and to_chainage <= "+expRfi2.getApprovedTo()+") or (from_chainage <= "+expRfi2.getApprovedFrom()+" and to_chainage >= "+expRfi2.getApprovedTo()+")) ";  
									List<Quantity> qtyList = this.getQuantityList(0, 0, " order by from_chainage, to_chainage ", whereC);
									Double quantity = null;
									int appFromOffset =  10 - expRfi2.getApprovedFrom()%10;
									if(appFromOffset == 10)
									{
											appFromOffset = 0;
									}
									int appFrom = expRfi2.getApprovedFrom() + appFromOffset;
									int appToOffset = expRfi2.getApprovedTo()%10;
									int appTo = expRfi2.getApprovedTo() - appToOffset;
									for (int k =appFrom+10; k <= appTo; k=k+10 )
									{
										boolean quantityFound = false;
										Iterator<Quantity> qtyListIterator = qtyList.iterator();
										
										while (qtyListIterator.hasNext())
										{
											Quantity qty = qtyListIterator.next();
											if(qty.getFromChainage() <= (k-10) && qty.getToChainage() >= k && qty.getQuantity() != null )
											{
												quantityFound = true;
												if(quantity == null)
												{
													quantity = 0.0d;
												}
												
												
												
												if(k > (appFrom+10))
												{
													quantity = quantity + (qty.getQuantity()*10);
													rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
													if(k==appTo && appToOffset > 0)
													{
														quantity = quantity + (qty.getQuantity()*appToOffset);
														rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+appTo+"</td><td style=\"border-top-style: dashed;\">"+(appTo+appToOffset)+"</td><td style=\"border-top-style: dashed;\">"+appToOffset+"</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*appToOffset)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
													}
													
												}
												else
												{
													if(k == (appFrom+10) && appFromOffset > 0)
													{
														quantity = quantity + (qty.getQuantity()*appFromOffset);
														rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(appFrom-appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+appFrom+"</td><td style=\"border-top-width: thick;\">10</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(qty.getQuantity()*appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(quantity)+"</td></tr>");
														quantity = quantity + (qty.getQuantity()*10);
														rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
													}
													else
													{
														quantity = quantity + (qty.getQuantity()*10);
														rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(k-10)+"</td><td style=\"border-top-width: thick;\">"+k+"</td><td style=\"border-top-width: thick;\">10</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(quantity)+"</td></tr>");
													}
												}
												
												break;
											}
										}
										
										if(quantityFound == false)
										{
											quantity = null;
											if(k > (appFrom+10))
											{
												rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">-</td><td style=\"border-top-style: dashed;\">-</td></tr>");
												if(k==appTo && appToOffset > 0)
												{
													rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+appTo+"</td><td style=\"border-top-style: dashed;\">"+(appTo+appToOffset)+"</td><td style=\"border-top-style: dashed;\">appToOffset</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">-</td><td style=\"border-top-style: dashed;\">-</td></tr>");
												}
												
											}
											else
											{
												if(k == (appFrom+10) && appFromOffset > 0)
												{
													rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(appFrom-appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+appFrom+"</td><td style=\"border-top-width: thick;\">10</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">-</td><td style=\"border-top-width: thick;\">-</td></tr>");
													//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
												}
												else
												{
													rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(k-10)+"</td><td style=\"border-top-width: thick;\">"+k+"</td><td style=\"border-top-width: thick;\">10</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">-</td><td style=\"border-top-width: thick;\">-</td></tr>");
												}
											}
											break;
										}
									}
									expRfi2.setQuantity(quantity);
								}
								
								
								
									RFIBill rfiBill = new RFIBill();
									rfiBill.setBillFrom(expRfi2.getApprovedFrom());
									rfiBill.setBillLayer(expRfi2.getApprovedLayer());
									rfiBill.setBillSide(side);
									rfiBill.setBillTo(expRfi2.getApprovedTo());
									rfiBill.setBillActivity(expRfi2.getActivity());
									rfiBill.setItemDescription(expRfi2.getItemDescription());
									rfiBill.setQuantity(expRfi2.getQuantity());
									rfiBill.setRfiNumber(expRfi2.getRfiNumber());
									rfiBill.setIssueDate(expRfi2.getIssueDate());
									rfiBill.setRemarks(expRfi2.getRemarks());
									rfiBill.setRfiCalculationString(rfiCalculationString.toString());
									
									Double deductQuantity = null;
									StringBuffer deductString = new StringBuffer("");
									if((!"viewCalculation".equals(viewString)) && directQuantityFound == false && (expRfi2.getApprovedTo() > expRfi2.getApprovedFrom()) && expRfi2.getApprovedLayer().trim().equals("Top"))
									{
										//Find all rfi for which quantity needs to be deducted.
										String deductWhere = " where a.bill_number IS NOT NULL AND a.bill_number <> '' and a.bill_number <> 'Under Preparation' AND activity = '"+activity+"' AND item_description = '"+item+"' and d.bill_side = '"+side+"' and ((d.bill_from >= "+expRfi2.getApprovedFrom()+" and d.bill_from < "+expRfi2.getApprovedTo()+" ) or (d.bill_to > "+expRfi2.getApprovedFrom()+" and d.bill_to <= "+expRfi2.getApprovedTo()+") or (d.bill_from <= "+expRfi2.getApprovedFrom()+" and d.bill_to >= "+expRfi2.getApprovedTo()+")) ";
										System.out.println("Executing Deduction query");
										List<ExpandedRFI> deductRfiList = this.getDeductionRfiBillList(" order by d.bill_from, d.bill_to ", deductWhere);
										System.out.println("Got Deduction result");
										 
										//ExpandedRFI[] rfiArray  = (ExpandedRFI [])expandedRfiList.toArray(new ExpandedRFI[0]);
										 
										for (int l = 0; l < deductRfiList.size(); ++l)
										{
											boolean fromInBetween = false;
											boolean toInBetween = false;
											boolean totallyInclusive = false;
											int overlapLength = 0;
											ExpandedRFI deductRFI = deductRfiList.get(l);
											if(deductRFI.getQuantity() != null)
											{
												if(deductRFI.getApprovedFrom() >= expRfi2.getApprovedFrom() && deductRFI.getApprovedFrom() < expRfi2.getApprovedTo())
												{
													fromInBetween = true;
												}
												if(deductRFI.getApprovedTo() > expRfi2.getApprovedFrom() && deductRFI.getApprovedTo() <= expRfi2.getApprovedTo())
												{
													toInBetween = true;
												}
												if(deductRFI.getApprovedFrom() <= expRfi2.getApprovedFrom() && deductRFI.getApprovedTo() >= expRfi2.getApprovedTo())
												{
													totallyInclusive = true;
												}
												if(fromInBetween && !toInBetween)
												{
													overlapLength = expRfi2.getApprovedTo() - deductRFI.getApprovedFrom();
												}
												else if(fromInBetween && toInBetween)
												{
													overlapLength = deductRFI.getApprovedTo() - deductRFI.getApprovedFrom();
												}
												else if(!fromInBetween && toInBetween)
												{
													overlapLength = deductRFI.getApprovedTo() - expRfi2.getApprovedFrom();
												}
												else if(totallyInclusive)
												{
													overlapLength = expRfi2.getApprovedTo() - expRfi2.getApprovedFrom();
												}
												double qty = deductRFI.getQuantity();
												if((deductRFI.getApprovedTo() - deductRFI.getApprovedFrom()) > 0.0000001)
													qty = (overlapLength*deductRFI.getQuantity())/(deductRFI.getApprovedTo() - deductRFI.getApprovedFrom());
												if(deductQuantity == null)
												{	
													deductQuantity = qty;
												}
												else
												{
													deductQuantity += qty;
												}
												
												if(l > 0)
												{
													deductString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getBillNumber()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getApprovedFrom()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getApprovedTo()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getApprovedSide()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getApprovedLayer()+"</td><td style=\"border-top-style: dashed;\">"+(deductRFI.getApprovedTo()-deductRFI.getApprovedFrom())+"</td><td style=\"border-top-style: dashed;\">"+overlapLength+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(deductRFI.getQuantity())+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(deductQuantity)+"</td></tr>");
												}
												else
												{
													deductString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getBillNumber()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getActivity()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getApprovedSide()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getApprovedLayer()+"</td><td style=\"border-top-width: thick;\">"+(deductRFI.getApprovedTo()-deductRFI.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+overlapLength+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(deductRFI.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(qty)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(deductQuantity)+"</td></tr>");
												}
												
												
											}
											
										}
	
									}
									
									rfiBill.setDeductionString("");
									if(deductQuantity != null)
									{
										rfiBill.setDeductionString(deductString.toString());
										rfiBill.setDeductQuantity(deductQuantity);
									}
									
									double g = rfiBill.getQuantity() == null? 0.0d:rfiBill.getQuantity();
									double d = deductQuantity == null? 0.0d:deductQuantity;
									
									if(!(rfiBill.getQuantity() == null && deductQuantity == null))
									{
										rfiBill.setPayableQuantity(g-d);
									}
									
									
									Double rate = this.getBoq(this.getWorkItem(expRfi2.getItemDescription()).getBoqNumber()).getRate();
									Double amount = null;
									if(rfiBill.getPayableQuantity() != null && rate != null)
										amount = rfiBill.getPayableQuantity() * rate;
									rfiBill.setRate (rate);
									rfiBill.setAmount(amount);
									
									layerEntry.getValue().add(rfiBill);
								}
								
							}
					 
						}
					}
				}
			}
		}
		
		return itemMap;
	}
	
}


@Override
public LinkedHashMap preparePreviousBill(Set rfiNumberSet, String billNumber) throws Exception
{
	List<Object[]> preparedItemsList = this.getItemsForRFINumbers(rfiNumberSet, billNumber);
	 LinkedHashMap itemMap = new LinkedHashMap();
	 if (preparedItemsList != null)
	 {
		 Iterator<Object[]> it = preparedItemsList.iterator();
		 while(it.hasNext())
		 {
			 Object[] itemObject = it.next();
			 if(itemObject != null)
			 {
				 String activity = (String) itemObject[0];
				 String item = (String) itemObject[1];
				 String side = (String) itemObject[2];
				 String layer = (String) itemObject[3];
				 
				 int sideLoop = 1;
				 if("BS".equalsIgnoreCase(side.trim()))
				 {
					 sideLoop = 2;
				 }
				 
				 for (int counter = 0; counter<sideLoop; ++counter)
				 {
					 if (counter == 0 && sideLoop == 2)
					 {
						 side = "LHS";
					 }
					 if (counter == 1 && sideLoop == 2)
					 {
						 side = "RHS";
					 }
					 if(!itemMap.containsKey(item))
					 {
						 itemMap.put(item, new LinkedHashMap());
					 }
					 LinkedHashMap activityMap = (LinkedHashMap)itemMap.get(item);
					 if(!activityMap.containsKey(activity))
					 {
						 activityMap.put(activity, new LinkedHashMap());
					 }
					 LinkedHashMap sideMap = (LinkedHashMap)activityMap.get(activity);
					 
					 if(!sideMap.containsKey(side))
					 {
						 sideMap.put(side, new LinkedHashMap());
					 }
					 LinkedHashMap layerMap = (LinkedHashMap)sideMap.get(side);
					 if(!layerMap.containsKey(layer))
					 {
						 layerMap.put(layer, new ArrayList<RFIBill>());
					 }
				 }
			}
		}
	 }
		
	
	Iterator itemMapIterator = itemMap.entrySet().iterator();
	while (itemMapIterator.hasNext())
	{
		Map.Entry<String, LinkedHashMap> itemEntry = (Map.Entry<String, LinkedHashMap>)itemMapIterator.next();
		String item = itemEntry.getKey();
		LinkedHashMap activityMap = itemEntry.getValue();
		Iterator activityMapIterator = activityMap.entrySet().iterator();
		while (activityMapIterator.hasNext())
		{
			Map.Entry<String, LinkedHashMap> activityEntry = (Map.Entry<String, LinkedHashMap>)activityMapIterator.next();
			String activity = activityEntry.getKey();
		
			LinkedHashMap sideMap = activityEntry.getValue();
			
			
			Iterator sideMapIterator = sideMap.entrySet().iterator();
			while (sideMapIterator.hasNext())
			{
				Map.Entry<String, LinkedHashMap> sideEntry = (Map.Entry<String, LinkedHashMap>)sideMapIterator.next();
				String side = sideEntry.getKey();
				LinkedHashMap layerMap = sideEntry.getValue();
				
				Iterator layerMapIterator = layerMap.entrySet().iterator();
				while (layerMapIterator.hasNext())
				{
					Map.Entry<String, List<RFIBill>> layerEntry = (Map.Entry<String, List<RFIBill>>)layerMapIterator.next();
					String layer = layerEntry.getKey();
					List<RFIBill> rfiBillList = layerEntry.getValue();
					int addBS = 0;
					if ("LHS".equalsIgnoreCase(side.trim()) || "RHS".equalsIgnoreCase(side.trim()))
					{
						addBS = 1;
					}
					String where = "";
					if (addBS == 0)
						where = " where bill_number IS NOT NULL AND bill_number <> '' AND bill_number <> 'Under Preparation' AND activity = '"+activity+"' AND item_description = '"+item+"' and approved_side = '"+side+"' and approved_layer in ('"+layer+"', 'Top')";
					else
						where = " where bill_number IS NOT NULL AND bill_number <> '' AND bill_number <> 'Under Preparation' AND activity = '"+activity+"' AND item_description = '"+item+"' and approved_side in ('"+side+"', 'BS') and approved_layer in ('"+layer+"', 'Top')";
					List<ExpandedRFI> expandedRfiList = this.getExpandedRfiBillList(0, 0, " order by approved_from, approved_to ", where);
			 
					Iterator<ExpandedRFI> itList = expandedRfiList.listIterator();
			 
					//ExpandedRFI[] rfiArray  = (ExpandedRFI [])expandedRfiList.toArray(new ExpandedRFI[0]);
			 
					for (int i = 0; i < expandedRfiList.size(); ++i)
					{
						ExpandedRFI expRfi1 = null;
						ExpandedRFI expRfi2 = null;
						ExpandedRFI expRfi3 = null;
				 
						expRfi2 = expandedRfiList.get(i);
						
						
						if(i > 0)
						{
							expRfi1 = expandedRfiList.get(i-1);
						}
				 
						if (i <= (expandedRfiList.size()-2))
						{
							expRfi3 = expandedRfiList.get(i+1);
						}
						
						if(!layer.equals("Top"))
						{
							if(expRfi1 != null && expRfi1.getApprovedLayer().equals("Top"))
							{
								expRfi1.setBillNumber("Checking Overlap");
							}
							
							if(expRfi2 != null && expRfi2.getApprovedLayer().equals("Top"))
							{
								expRfi2.setBillNumber("Checking Overlap");
							}
							
							if(expRfi3 != null && expRfi3.getApprovedLayer().equals("Top"))
							{
								expRfi3.setBillNumber("Checking Overlap");
							}
						}
				 
						if(expRfi2 != null )
						{
							boolean overlapFound = false;
							if(expRfi1 != null && "Under Edition".equals(expRfi2.getBillNumber()) && expRfi2 != null)
							{
								Integer approvedFrom1 = expRfi1.getApprovedFrom();
								Integer approvedTo1 = expRfi1.getApprovedTo();
						 
								Integer approvedFrom2 = expRfi2.getApprovedFrom();
								Integer approvedTo2 = expRfi2.getApprovedTo();
						 
								if(approvedFrom2 < approvedTo1)
								{
									
									expRfi2.setApprovedFrom(approvedTo1);
									overlapFound = true;
									if(approvedTo1 >= approvedTo2)
									{
										expRfi2.setApprovedTo(approvedTo1);
										overlapFound = true;
									}
								}
							}
					 
							if(expRfi2 != null && expRfi3 != null)
							{
								Integer approvedFrom3 = expRfi3.getApprovedFrom();
								Integer approvedTo3 = expRfi3.getApprovedTo();
						 
								Integer approvedFrom2 = expRfi2.getApprovedFrom();
								Integer approvedTo2 = expRfi2.getApprovedTo();
						 
								if((approvedTo2 > approvedFrom3) && (approvedFrom3 >= approvedFrom2) && "Under Edition".equals(expRfi2.getBillNumber()))
								{
									expRfi2.setApprovedTo(approvedFrom3);
									overlapFound = true;
								}
								
								if(approvedTo2 > approvedTo3)
								{
									ExpandedRFI newExpRfi = expRfi2.clone();
									newExpRfi.setApprovedFrom(approvedTo3);
									newExpRfi.setApprovedTo(approvedTo2);
									expandedRfiList.add(i+2, newExpRfi);
								}
							}
							if("Under Edition".equals(expRfi2.getBillNumber()) && expRfi2.getApprovedLayer().trim().equals(layer) && !(overlapFound == true && (expRfi2.getApprovedFrom() >= expRfi2.getApprovedTo())))
							{
							boolean directQuantityFound = false;
							if(expRfi2.getQuantity() != null)
							{
								directQuantityFound = true;
								//rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td></tr>");
								//rfiCalculationString.append("<tr><td>"+expRfi2.getRfiNumber()+"</td><td>"+expRfi2.getApprovedFrom()+"</td><td>"+expRfi2.getApprovedTo()+"</td><td>"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td>"+(expRfi2.getBreadth() != null?+expRfi2.getBreadth():"")+"<td/><td/><td>"+(expRfi2.getDepth() != null?+expRfi2.getDepth():"")+"</td><td>"+(expRfi2.getArea() != null?+expRfi2.getArea():"")+"</td></tr>");
							}
							else if(expRfi2.getArea() != null)
							{
								directQuantityFound = true;
								expRfi2.setQuantity(expRfi2.getArea()*(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom()));
								//rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getArea())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td></tr>");
							}
							else if(expRfi2.getBreadth() != null && expRfi2.getDepth() != null)
							{
								directQuantityFound = true;
								expRfi2.setQuantity(expRfi2.getBreadth()*expRfi2.getDepth()*(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom()));
								//rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+(expRfi2.getApprovedTo()-expRfi2.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getBreadth())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getDepth())+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(expRfi2.getQuantity())+"</td></tr>");
							}
							
							else if(expRfi2 != null && (!(expRfi2.getQuantity() != null || expRfi2.getArea() != null || (expRfi2.getBreadth() !=null && expRfi2.getDepth() != null)))  && expRfi2.getApprovedFrom() != null && expRfi2.getApprovedTo() != null)
							{
								//find quantity
								String whereC = " where activity = '"+activity+"' AND item_description = '"+item+"' and side='"+side+"' and layer = '"+layer+"' and ((from_chainage >= "+expRfi2.getApprovedFrom()+" and from_chainage < "+expRfi2.getApprovedTo()+" ) or (to_chainage > "+expRfi2.getApprovedFrom()+" and to_chainage <= "+expRfi2.getApprovedTo()+") or (from_chainage <= "+expRfi2.getApprovedFrom()+" and to_chainage >= "+expRfi2.getApprovedTo()+")) ";  
								List<Quantity> qtyList = this.getQuantityList(0, 0, " order by from_chainage, to_chainage ", whereC);
								Double quantity = null;
								int appFromOffset =  10 - expRfi2.getApprovedFrom()%10;
								if(appFromOffset == 10)
								{
										appFromOffset = 0;
								}
								int appFrom = expRfi2.getApprovedFrom() + appFromOffset;
								int appToOffset = expRfi2.getApprovedTo()%10;
								int appTo = expRfi2.getApprovedTo() - appToOffset;
								for (int k =appFrom+10; k <= appTo; k=k+10 )
								{
									boolean quantityFound = false;
									Iterator<Quantity> qtyListIterator = qtyList.iterator();
									
									while (qtyListIterator.hasNext())
									{
										Quantity qty = qtyListIterator.next();
										if(qty.getFromChainage() <= (k-10) && qty.getToChainage() >= k && qty.getQuantity() != null )
										{
											quantityFound = true;
											if(quantity == null)
											{
												quantity = 0.0d;
											}
											
											
											
											if(k > (appFrom+10))
											{
												quantity = quantity + (qty.getQuantity()*10);
												//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
												if(k==appTo && appToOffset > 0)
												{
													quantity = quantity + (qty.getQuantity()*appToOffset);
													//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+appTo+"</td><td style=\"border-top-style: dashed;\">"+(appTo+appToOffset)+"</td><td style=\"border-top-style: dashed;\">appToOffset</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*appToOffset)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
												}
												
											}
											else
											{
												if(k == (appFrom+10) && appFromOffset > 0)
												{
													quantity = quantity + (qty.getQuantity()*appFromOffset);
													//rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(appFrom-appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+appFrom+"</td><td style=\"border-top-width: thick;\">1</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(qty.getQuantity()*appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(quantity)+"</td></tr>");
													quantity = quantity + (qty.getQuantity()*10);
													//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
												}
												else
												{
													quantity = quantity + (qty.getQuantity()*10);
													//rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(k-10)+"</td><td style=\"border-top-width: thick;\">"+k+"</td><td style=\"border-top-width: thick;\">10</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(quantity)+"</td></tr>");
												}
											}
											
											break;
										}
									}
									
									if(quantityFound == false)
									{
										quantity = null;
										if(k > (appFrom+10))
										{
											//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">1</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">-</td><td style=\"border-top-style: dashed;\">-</td></tr>");
											if(k==appTo && appToOffset > 0)
											{
												//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+appTo+"</td><td style=\"border-top-style: dashed;\">"+(appTo+appToOffset)+"</td><td style=\"border-top-style: dashed;\">appToOffset</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">-</td><td style=\"border-top-style: dashed;\">-</td></tr>");
											}
											
										}
										else
										{
											if(k == (appFrom+10) && appFromOffset > 0)
											{
												//rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(appFrom-appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+appFrom+"</td><td style=\"border-top-width: thick;\">1</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">-</td><td style=\"border-top-width: thick;\">-</td></tr>");
												//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
											}
											else
											{
												//rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(k-10)+"</td><td style=\"border-top-width: thick;\">"+k+"</td><td style=\"border-top-width: thick;\">1</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">-</td><td style=\"border-top-width: thick;\">-</td></tr>");
											}
										}
										break;
									}
								}
								expRfi2.setQuantity(quantity);
							}
							
							
							
							if(rfiNumberSet.contains(expRfi2.getRfiNumber()))
							{
								RFIBill rfiBill = new RFIBill();
								rfiBill.setBillFrom(expRfi2.getApprovedFrom());
								rfiBill.setBillLayer(expRfi2.getApprovedLayer());
								rfiBill.setBillSide(side);
								rfiBill.setBillTo(expRfi2.getApprovedTo());
								rfiBill.setBillActivity(expRfi2.getActivity());
								rfiBill.setItemDescription(expRfi2.getItemDescription());
								rfiBill.setQuantity(expRfi2.getQuantity());
								rfiBill.setRfiNumber(expRfi2.getRfiNumber());
								rfiBill.setIssueDate(expRfi2.getIssueDate());
								rfiBill.setRemarks(expRfi2.getRemarks());
								
								Double deductQuantity = null;
								StringBuffer deductString = new StringBuffer("");
								if(directQuantityFound == false && (expRfi2.getApprovedTo() > expRfi2.getApprovedFrom()) && expRfi2.getApprovedLayer().trim().equals("Top"))
								{
									//Find all rfi for which quantity needs to be deducted.
									
									
									String deductWhere = " where a.bill_number IS NOT NULL AND a.bill_number <> '' and a.bill_number <> 'Under Preparation' and a.bill_number <> 'Under Edition' AND activity = '"+activity+"' AND item_description = '"+item+"' and d.bill_side = '"+side+"' and ((d.bill_from >= "+expRfi2.getApprovedFrom()+" and d.bill_from < "+expRfi2.getApprovedTo()+" ) or (d.bill_to > "+expRfi2.getApprovedFrom()+" and d.bill_to <= "+expRfi2.getApprovedTo()+") or (d.bill_from <= "+expRfi2.getApprovedFrom()+" and d.bill_to >= "+expRfi2.getApprovedTo()+")) ";
									List<ExpandedRFI> deductRfiList = this.getDeductionRfiBillList(" order by d.bill_from, d.bill_to ", deductWhere);
									
									 
									//ExpandedRFI[] rfiArray  = (ExpandedRFI [])expandedRfiList.toArray(new ExpandedRFI[0]);
									 
									for (int l = 0; l < deductRfiList.size(); ++l)
									{
										boolean fromInBetween = false;
										boolean toInBetween = false;
										boolean totallyInclusive = false;
										int overlapLength = 0;
										ExpandedRFI deductRFI = deductRfiList.get(l);
										if(deductRFI.getQuantity() != null)
										{
											if(deductRFI.getApprovedFrom() >= expRfi2.getApprovedFrom() && deductRFI.getApprovedFrom() < expRfi2.getApprovedTo())
											{
												fromInBetween = true;
											}
											if(deductRFI.getApprovedTo() > expRfi2.getApprovedFrom() && deductRFI.getApprovedTo() <= expRfi2.getApprovedTo())
											{
												toInBetween = true;
											}
											if(deductRFI.getApprovedFrom() <= expRfi2.getApprovedFrom() && deductRFI.getApprovedTo() >= expRfi2.getApprovedTo())
											{
												totallyInclusive = true;
											}
											if(fromInBetween && !toInBetween)
											{
												overlapLength = expRfi2.getApprovedTo() - deductRFI.getApprovedFrom();
											}
											else if(fromInBetween && toInBetween)
											{
												overlapLength = deductRFI.getApprovedTo() - deductRFI.getApprovedFrom();
											}
											else if(!fromInBetween && toInBetween)
											{
												overlapLength = deductRFI.getApprovedTo() - expRfi2.getApprovedFrom();
											}
											else if(totallyInclusive)
											{
												overlapLength = expRfi2.getApprovedTo() - expRfi2.getApprovedFrom();
											}
											
											double qty = deductRFI.getQuantity();
											if((deductRFI.getApprovedTo() - deductRFI.getApprovedFrom()) > 0.0000001)
												qty = (overlapLength*deductRFI.getQuantity())/(deductRFI.getApprovedTo() - deductRFI.getApprovedFrom());
											if(deductQuantity == null)
											{	
												deductQuantity = qty;
											}
											else
											{
												deductQuantity += qty;
											}
											
											if(l > 0)
											{
												//deductString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getApprovedFrom()+"</td><td style=\"border-top-style: dashed;\">"+deductRFI.getApprovedTo()+"</td><td style=\"border-top-style: dashed;\">"+(deductRFI.getApprovedTo()-deductRFI.getApprovedFrom())+"</td><td style=\"border-top-style: dashed;\">"+overlapLength+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(deductRFI.getQuantity())+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(deductQuantity)+"</td></tr>");
											}
											else
											{
												//deductString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getApprovedFrom()+"</td><td style=\"border-top-width: thick;\">"+deductRFI.getApprovedTo()+"</td><td style=\"border-top-width: thick;\">"+(deductRFI.getApprovedTo()-deductRFI.getApprovedFrom())+"</td><td style=\"border-top-width: thick;\">"+overlapLength+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(deductRFI.getQuantity())+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(qty)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(deductQuantity)+"</td></tr>");
											}
											
											
										}
										
									}

								}
								
								rfiBill.setDeductionString("");
								if(deductQuantity != null)
								{
									rfiBill.setDeductionString(deductString.toString());
									rfiBill.setDeductQuantity(deductQuantity);
								}
								
								double g = rfiBill.getQuantity() == null? 0.0d:rfiBill.getQuantity();
								double d = deductQuantity == null? 0.0d:deductQuantity;
								
								if(!(rfiBill.getQuantity() == null && deductQuantity == null))
								{
									rfiBill.setPayableQuantity(g-d);
								}
								
								
								Double rate = this.getBoq(this.getWorkItem(expRfi2.getItemDescription()).getBoqNumber()).getRate();
								Double amount = null;
								if(rfiBill.getPayableQuantity() != null && rate != null)
									amount = rfiBill.getPayableQuantity() * rate;
								rfiBill.setRate (rate);
								rfiBill.setAmount(amount);
								
								layerEntry.getValue().add(rfiBill);
							}
							}
								
						}
				 
					}
				}
			}
		}
	}
	
	return itemMap;
	
}



@Override
public void createNewBill(Bill bill, LinkedHashMap itemMap) throws Exception
{
	rfiServiceDao.createNewBill(bill, itemMap);
}

@Override
public List<String> getPreviousBilledItems(String billNumber)
{
	return rfiServiceDao.getPreviousBilledItems(billNumber);
}

@Override
public List<Bill> getBills()
{
	return rfiServiceDao.getBills();
}

@Override
public List<RFIBill> getRfiBillList(String billNumber, String item)
{
	return rfiServiceDao.getRfiBillList(billNumber, item);
}

@Override
public Bill getBill(String billNumber)
{
	return rfiServiceDao.getBill(billNumber);
}

@Override
public void editPreviousBill(String billNumber, LinkedHashMap itemMap, List rfiNumberList) throws Exception
{
	rfiServiceDao.editPreviousBill(billNumber, itemMap, rfiNumberList);
}

@Override
public void deleteRFIFromBill(String[] rfiNumberList)
{
	rfiServiceDao.deleteRFIFromBill(rfiNumberList);
}

@Override
public List<ExpandedRFI> isOverlap(RFI rfi)
{
	return rfiServiceDao.isOverlap(rfi);
}

@Override
public List<UserRegistrationForm> getUsersList()
{
	return rfiServiceDao.getUsersList();
}

@Override
public void deleteUser(String username)
{
	rfiServiceDao.deleteUser(username);
}

@Override
public synchronized List<ExpandedRFI> getDeductionRfiBillList(String orderBy, String where)
{
	return rfiServiceDao.getDeductionRfiBillList(orderBy, where);
}


}

