package com.model.rfi;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.dhtmlx.xml2excel.RGBColor;

public class ExportDeductionSheetController extends AbstractController {
	private RFIService rfiService;

	private static HashMap<String,String> operatorMap = new HashMap<String,String>();
	private static HashMap<String,String> columnTypeMap = new HashMap<String,String>();
	
	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	
	private String bgColor = "D1E5FE";
	private String lineColor = "A4BED4";
	private String headerTextColor = "000000";
	private String scaleOneColor = "FFFFFF";
	private String scaleTwoColor = "E3EFFF";
	private String gridTextColor = "000000";
	private String watermarkTextColor = "8b8b8b";
	
	RGBColor colors;
	
	private WritableWorkbook wb;
	
	private String sep = ":::";
	
	public void setRfiService(RFIService rfiService)
	{ 
		this.rfiService = rfiService; 
	}
	
	
	 @Override
	 protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 										HttpServletResponse response) throws Exception
	{
		 
		
	
		colors = new RGBColor();
		
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream(), wbSettings);
		
		wb = workbook;
		LinkedHashMap itemMap = prepareBill(workbook);
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=deductionSheetExport.xls");
		response.setHeader("Cache-Control", "max-age=0");
		
		workbook.write();
		workbook.close();
		
		return null;
	}
	 
	 
	 private LinkedHashMap prepareBill(WritableWorkbook workbook) throws Exception
	 {
		 synchronized (RFIBill.class) {
			DecimalFormat twoDForm = new DecimalFormat("#.##");
		 	List<Object[]> preparedItemsList = rfiService.getPreparedItems();
		 	 LinkedHashMap itemMap = rfiService.formItemMap(preparedItemsList);
		 	 
		 		
		 	
		 	Iterator itemMapIterator = itemMap.entrySet().iterator();
		 	int counter = 0;
		 	while (itemMapIterator.hasNext())
		 	{
		 		int rowNumber =0;
		 		Map.Entry<String, LinkedHashMap> itemEntry = (Map.Entry<String, LinkedHashMap>)itemMapIterator.next();
		 		String item = itemEntry.getKey();
		 		
		 		workbook.createSheet(item, counter);
		 		WritableSheet excelSheet = workbook.getSheet(counter);
		 		++counter;
		 		createLabel(excelSheet);
		 		
		 		
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
		 					List<ExpandedRFI> expandedRfiList = rfiService.getExpandedRfiBillList(0, 0, " order by approved_from, approved_to ", where);
		 			 
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
									
									/*
									else if(expRfi2 != null && (!(expRfi2.getQuantity() != null || expRfi2.getArea() != null || (expRfi2.getBreadth() !=null && expRfi2.getDepth() != null)))  && expRfi2.getApprovedFrom() != null && expRfi2.getApprovedTo() != null)
									{
										//find quantity
										String whereC = " where activity = '"+activity+"' AND item_description = '"+item+"' and side='"+side+"' and layer = '"+layer+"' and ((from_chainage >= "+expRfi2.getApprovedFrom()+" and from_chainage < "+expRfi2.getApprovedTo()+" ) or (to_chainage > "+expRfi2.getApprovedFrom()+" and to_chainage <= "+expRfi2.getApprovedTo()+") or (from_chainage <= "+expRfi2.getApprovedFrom()+" and to_chainage >= "+expRfi2.getApprovedTo()+")) ";  
										List<Quantity> qtyList = rfiService.getQuantityList(0, 0, " order by from_chainage, to_chainage ", whereC);
										Double quantity = null;
										int appFromOffset =  10 - expRfi2.getApprovedFrom()%10;
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
														rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">1</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
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
															rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(appFrom-appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+appFrom+"</td><td style=\"border-top-width: thick;\">1</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(qty.getQuantity()*appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(quantity)+"</td></tr>");
															quantity = quantity + (qty.getQuantity()*10);
															rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
														}
														else
														{
															quantity = quantity + (qty.getQuantity()*10);
															rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(k-10)+"</td><td style=\"border-top-width: thick;\">"+k+"</td><td style=\"border-top-width: thick;\">1</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-width: thick;\">"+twoDForm.format(quantity)+"</td></tr>");
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
													rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">1</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">-</td><td style=\"border-top-style: dashed;\">-</td></tr>");
													if(k==appTo && appToOffset > 0)
													{
														rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+appTo+"</td><td style=\"border-top-style: dashed;\">"+(appTo+appToOffset)+"</td><td style=\"border-top-style: dashed;\">appToOffset</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">-</td><td style=\"border-top-style: dashed;\">-</td></tr>");
													}
													
												}
												else
												{
													if(k == (appFrom+10) && appFromOffset > 0)
													{
														rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(appFrom-appFromOffset)+"</td><td style=\"border-top-width: thick;\">"+appFrom+"</td><td style=\"border-top-width: thick;\">1</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">-</td><td style=\"border-top-width: thick;\">-</td></tr>");
														//rfiCalculationString.append("<tr><td style=\"border-top-style: dashed;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-style: dashed;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-style: dashed;\">"+(k-10)+"</td><td style=\"border-top-style: dashed;\">"+k+"</td><td style=\"border-top-style: dashed;\">10</td><td style=\"border-top-style: dashed;\">"+side+"</td><td style=\"border-top-style: dashed;\">"+layer+"</td><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\"/><td style=\"border-top-style: dashed;\">"+twoDForm.format(qty.getQuantity()*10)+"</td><td style=\"border-top-style: dashed;\">"+twoDForm.format(quantity)+"</td></tr>");
													}
													else
													{
														rfiCalculationString.append("<tr><td style=\"border-top-width: thick;\">"+expRfi2.getRfiNumber()+"</td><td style=\"border-top-width: thick;\">"+expRfi2.getActivity()+"</td><td style=\"border-top-width: thick;\">"+(k-10)+"</td><td style=\"border-top-width: thick;\">"+k+"</td><td style=\"border-top-width: thick;\">1</td><td style=\"border-top-width: thick;\">"+side+"</td><td style=\"border-top-width: thick;\">"+layer+"</td><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\"/><td style=\"border-top-width: thick;\">-</td><td style=\"border-top-width: thick;\">-</td></tr>");
													}
												}
												break;
											}
										}
										expRfi2.setQuantity(quantity);
									}*/
		 							
		 							
		 							
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
		 								rfiBill.setWiRemarks(expRfi2.getWiRemarks());
		 								rfiBill.setRfiCalculationString(rfiCalculationString.toString());
		 								
		 								Double deductQuantity = null;
		 								String deductString = "";
		 								if(directQuantityFound == false && (expRfi2.getApprovedTo() > expRfi2.getApprovedFrom()) && expRfi2.getApprovedLayer().trim().equals("Top"))
		 								{
		 									//Find all rfi for which quantity needs to be deducted.
		 									String deductWhere = " where a.bill_number IS NOT NULL AND a.bill_number <> '' and a.bill_number <> 'Under Preparation' AND activity = '"+activity+"' AND item_description = '"+item+"' and d.bill_side = '"+side+"' and ((d.bill_from >= "+expRfi2.getApprovedFrom()+" and d.bill_from < "+expRfi2.getApprovedTo()+" ) or (d.bill_to > "+expRfi2.getApprovedFrom()+" and d.bill_to <= "+expRfi2.getApprovedTo()+") or (d.bill_from <= "+expRfi2.getApprovedFrom()+" and d.bill_to >= "+expRfi2.getApprovedTo()+")) ";
											List<ExpandedRFI> deductRfiList = rfiService.getDeductionRfiBillList(" order by d.bill_from, d.bill_to ", deductWhere);
		 									
		 									 
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
		 												deductString = expRfi2.getRfiNumber()+sep+deductRFI.getRfiNumber()+sep+deductRFI.getBillNumber()+sep+deductRFI.getActivity()+sep+deductRFI.getApprovedFrom()+sep+deductRFI.getApprovedTo()+sep+deductRFI.getApprovedSide()+sep+deductRFI.getApprovedLayer()+sep+(deductRFI.getApprovedTo()-deductRFI.getApprovedFrom())+sep+overlapLength+sep+twoDForm.format(deductRFI.getQuantity())+sep+twoDForm.format(qty)+sep+twoDForm.format(deductQuantity);
		 												createContent(excelSheet, deductString, rowNumber);
														++rowNumber;
		 											}
		 											else
		 											{
		 												++rowNumber;
		 												deductString = expRfi2.getRfiNumber()+sep+deductRFI.getRfiNumber()+sep+deductRFI.getBillNumber()+sep+deductRFI.getActivity()+sep+deductRFI.getApprovedFrom()+sep+deductRFI.getApprovedTo()+sep+deductRFI.getApprovedSide()+sep+deductRFI.getApprovedLayer()+sep+(deductRFI.getApprovedTo()-deductRFI.getApprovedFrom())+sep+overlapLength+sep+twoDForm.format(deductRFI.getQuantity())+sep+twoDForm.format(qty)+sep+twoDForm.format(deductQuantity);
		 												createContent(excelSheet, deductString, rowNumber);
														++rowNumber;
		 											}
		 											
		 											
		 										}
		 										
		 									}
	
		 								}
		 								/*
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
		 								
		 								
		 								Double rate = rfiService.getBoq(rfiService.getWorkItem(expRfi2.getItemDescription()).getBoqNumber()).getRate();
		 								Double amount = null;
		 								if(rfiBill.getPayableQuantity() != null && rate != null)
		 									amount = rfiBill.getPayableQuantity() * rate;
		 								rfiBill.setRate (rate);
		 								rfiBill.setAmount(amount);
		 								
		 								layerEntry.getValue().add(rfiBill);
		 								*/
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
	 
	 
		private void createLabel(WritableSheet sheet)
				throws WriteException {
			// Lets create a times font
			WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
			// Define the cell format
			times = new WritableCellFormat(times10pt);
			// Lets automatically wrap the cells
			times.setWrap(true);

			// Create create a bold font with unterlines
			WritableFont times10ptBoldUnderline = new WritableFont(
					WritableFont.TIMES, 10, WritableFont.BOLD, false,
					UnderlineStyle.SINGLE);
			timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
			// Lets automatically wrap the cells
			timesBoldUnderline.setWrap(true);

			CellView cv = new CellView();
			cv.setFormat(times);
			cv.setFormat(timesBoldUnderline);
			cv.setAutosize(true);
			

			// Write a few headers
			addLabel(sheet, 0, 0, "RFI Number");
			addLabel(sheet, 1, 0, "Deduct RFI No.");
			addLabel(sheet, 2, 0, "Bill No.");
			addLabel(sheet, 3, 0, "Activity");
			addLabel(sheet, 4, 0, "From");
			addLabel(sheet, 5, 0, "To");
			addLabel(sheet, 6, 0, "Side");
			addLabel(sheet, 7, 0, "Layer");
			addLabel(sheet, 8, 0, "Length");
			addLabel(sheet, 9, 0, "Deductible Length");
			addLabel(sheet, 10, 0, "Actual Quantity");
			addLabel(sheet, 11, 0, "Deductible Quantity");
			addLabel(sheet, 12, 0, "Cumulative Quantity");
		}

		private void createContent(WritableSheet sheet, String entry, int rowNumber) throws WriteException,
				RowsExceededException {
			String[] tokens = entry.split(sep);
			for (int i=0; i<tokens.length; ++i)
			{
				addString(sheet,i,rowNumber,tokens[i]);
			}
			for(int x=0;x<9;x++)
			{
			    CellView cell = sheet.getColumnView(x);
			    cell.setAutosize(true);
			    sheet.setColumnView(x, cell);
			}
		}
		
		private void addCaption(WritableSheet sheet, int column, int row, String s)
				throws RowsExceededException, WriteException {
			Label label;
			label = new Label(column, row, s, timesBoldUnderline);
			sheet.addCell(label);
		}

		private void addNumber(WritableSheet sheet, int column, int row,
				Integer integer) throws WriteException, RowsExceededException {
			jxl.write.Number number = null;
			if (integer != null)
			{
				number = new jxl.write.Number(column, row, integer, times);
				sheet.addCell(number);
			}
			
		}
		
		private void addNumber(WritableSheet sheet, int column, int row,
				Double dNumber) throws WriteException, RowsExceededException {
			jxl.write.Number number = null;
			if (dNumber != null)
			{
				number = new jxl.write.Number(column, row, dNumber, times);
				sheet.addCell(number);
			}
			
		}
		
		private void addDate(WritableSheet sheet, int column, int row,
				Date date) throws WriteException, RowsExceededException {
			if (date != null)
			{
				jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat ("dd-MM-yyyy"); 
				WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat); 
				DateTime dateCell = new DateTime(column, row, date, dateFormat); 
				sheet.addCell(dateCell);
			}
		}
		
		private void addLongDate(WritableSheet sheet, int column, int row,
				Date date) throws WriteException, RowsExceededException {
			if (date != null)
			{
				jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat ("dd-MM-yyyy hh:mm"); 
				WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat); 
				DateTime dateCell = new DateTime(column, row, date, dateFormat); 
				sheet.addCell(dateCell);
			}
		}
		
		private void addString(WritableSheet sheet, int column, int row,
				String text) throws WriteException, RowsExceededException {
			if(text != null)
			{
				Label label = new Label(column, row, text); 
				sheet.addCell(label);
			}
		}

		private void addLabel(WritableSheet sheet, int column, int row, String s)
				throws WriteException, RowsExceededException {
			
			if (s != null)
			{
				WritableFont font = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD);
				font.setColour(colors.getColor(headerTextColor, wb));
				WritableCellFormat f = new WritableCellFormat (font);
				f.setBackground(colors.getColor(bgColor, wb));
				f.setBorder(Border.ALL, BorderLineStyle.THIN, colors.getColor(lineColor, wb));
				f.setVerticalAlignment(VerticalAlignment.CENTRE);

				f.setAlignment(Alignment.CENTRE);
				
				Label label;
				label = new Label(column, row, s, f);
				sheet.addCell(label);
			}
		}

}
