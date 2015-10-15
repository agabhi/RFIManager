package com.model.rfi;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

public class ImportRFI {
	
	public static void main(String args[])
	{
		try {
			Workbook workbook = Workbook.getWorkbook(new File("C:\\rfiData1.xls"));
			Sheet sheet = workbook.getSheet(0);
			RFIServiceDAOImpl rfiService = new RFIServiceDAOImpl();
			System.out.println("*********Outside the loop************");
				for (int i=1;true;++i)
				{
					System.out.println("*********Inside the loop1************");
					RFI rfi = new RFI();
					Set<RFIApproval> rfiApprovals = new HashSet<RFIApproval>();
					RFIApproval rfiApproval = new RFIApproval();
					
					String rfiCode = sheet.getCell(0,i).getContents(); 
					if(rfiCode == null || rfiCode.equals(""))
					{
						break;
					}
					else
					{
						rfi.setRfiCode(rfiCode);
					}
					System.out.println("*********Inside the loop2************");
					String rfiNumber = sheet.getCell(1,i).getContents(); 
					if(rfiNumber == null || rfiNumber.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setRfiNumber(rfiNumber);
					}
					System.out.println("*********Inside the loop3************");
					Cell issueDateCell = sheet.getCell(2,i); 
					if (issueDateCell.getType() == CellType.DATE) 
					{ 
					  DateCell dc = (DateCell) issueDateCell; 
					  Date issueDate = dc.getDate(); 
					
					  if(issueDate == null)
					  {
						//Write in Log for the row details not saved
						continue;
					  }
					  else
					  {
						rfi.setIssueDate(issueDate);
					  }
					}
					else
					{
						//Issue date is not in the date Format
						System.out.println("Record no. " + i + ", Issued date is not in date format");;
					}
					System.out.println("*********Inside the loop4************");
					String itemDescription = sheet.getCell(3,i).getContents(); 
					if(itemDescription == null || itemDescription.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setItemDescription(itemDescription);
					}
					
					String wiRemarks = sheet.getCell(4,i).getContents(); 
					if(wiRemarks == null || wiRemarks.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setWiRemarks(wiRemarks);
					}
					
					Cell fromChainageCell = sheet.getCell(5,i); 
					if (fromChainageCell.getType() == CellType.NUMBER) 
					{ 
						NumberCell nc = (NumberCell) fromChainageCell; 
						int fromChainage = (int)nc.getValue();
						rfi.setFromChainage(fromChainage);
					}
					else
					{
						//From Chainage is not the number format
						continue;
					}
					
					Cell toChainageCell = sheet.getCell(6,i); 
					if (toChainageCell.getType() == CellType.NUMBER) 
					{ 
						NumberCell nc = (NumberCell) toChainageCell; 
						int toChainage = (int)nc.getValue();
						rfi.setToChainage(toChainage);
					}
					else
					{
						//To Chainage is not the number format
						continue;
					}
					
					String side = sheet.getCell(7,i).getContents(); 
					if(side == null || side.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setSide(side);
					}
					
					String layer = sheet.getCell(8,i).getContents(); 
					if(layer == null || layer.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setLayer(layer);
					}
					
					String status = sheet.getCell(9,i).getContents(); 
					if(status == null || status.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setStatus(status);
					}
		
					String grade = sheet.getCell(10,i).getContents(); 
					if(grade == null || grade.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setGrade(grade);
					}
					System.out.println("*********Inside the loop5************");
					Cell inspectionDateCell = sheet.getCell(11,i); 
					if (inspectionDateCell.getType() == CellType.DATE) 
					{ 
					  DateCell dc = (DateCell) inspectionDateCell; 
					  Date inspectionDate = dc.getDate(); 
					
					  if(inspectionDate == null)
					  {
						//Write in Log for the row details not saved
						continue;
					  }
					  else
					  {
						rfi.setInspectionDate(inspectionDate);
					  }
					}
					else
					{
						//Inspection date is not in the date Format
						continue;
					}
					
					String createdByUsername = sheet.getCell(12,i).getContents(); 
					if(createdByUsername == null || createdByUsername.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setCreatedByUserName(createdByUsername);
					}
					
					String lastUpdatedByUsername = sheet.getCell(13,i).getContents(); 
					if(lastUpdatedByUsername == null || lastUpdatedByUsername.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setLastEditedByUserName(lastUpdatedByUsername);
					}
					
					
					Cell approvedFromCell = sheet.getCell(14,i); 
					if (approvedFromCell.getType() == CellType.NUMBER) 
					{ 
						NumberCell nc = (NumberCell) approvedFromCell; 
						int approvedFrom = (int)nc.getValue();
						rfiApproval.setApprovedFrom(approvedFrom);
					}
					else
					{
						//approvedFrom Chainage is not the number format
						continue;
					}
					
					Cell approvedToCell = sheet.getCell(15,i); 
					if (approvedToCell.getType() == CellType.NUMBER) 
					{ 
						NumberCell nc = (NumberCell) approvedToCell; 
						int approvedTo = (int)nc.getValue();
						rfiApproval.setApprovedTo(approvedTo);
					}
					else
					{
						//approvedTo Chainage is not the number format
						continue;
					}
					
					String approvedSide = sheet.getCell(16,i).getContents(); 
					if(approvedSide == null || approvedSide.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfiApproval.setApprovedSide(approvedSide);
					}
					
					String approvedLayer = sheet.getCell(17,i).getContents(); 
					if(approvedLayer == null || approvedLayer.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfiApproval.setApprovedLayer(approvedLayer);
					}
					
					Cell approvalDateCell = sheet.getCell(18,i); 
					if (approvalDateCell.getType() == CellType.DATE) 
					{ 
					  DateCell dc = (DateCell) approvalDateCell; 
					  Date approvalDate = dc.getDate(); 
					
					  if(approvalDate == null)
					  {
						//Write in Log for the row details not saved
						continue;
					  }
					  else
					  {
						rfiApproval.setApprovalDate(approvalDate);
					  }
					}
					else
					{
						//Approval date is not in the date Format
						continue;
					}
					
					String billNumber = sheet.getCell(19,i).getContents(); 
					if(billNumber == null || billNumber.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setBillNumber(billNumber);
					}
					
					String remarks = sheet.getCell(20,i).getContents(); 
					if(remarks == null || remarks.equals(""))
					{
						//Write in Log for the row details not saved
						continue;
					}
					else
					{
						rfi.setRemarks(remarks);
					}
					
					rfi.setRfiApprovals(rfiApprovals);
					rfiService.add(rfi);
					System.out.println("*********End of the Loop************");
		//			i++;
				}
			
			System.out.println("*********Program Finished************");
		}
		
		catch (Exception ex)
		{
			System.out.println("Insied the catch");
		}
	}

}
