package com.model.rfi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import jxl.CellType;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.stringtree.json.JSONReader;
import org.stringtree.json.JSONValidatingReader;

import com.dhtmlx.xml2excel.ExcelWriter;
import com.dhtmlx.xml2excel.RGBColor;
import com.mysql.jdbc.StringUtils;

public class PrepareRfiXmlController1 extends AbstractController
{
	
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
	
	static
	{
		operatorMap.put("eq","=");
		operatorMap.put("ne","<>");
		operatorMap.put("lt","<");
		operatorMap.put("gt",">");
		operatorMap.put("le","<=");
		operatorMap.put("ge",">=");
		
		columnTypeMap.put("rfi_code","string");
		columnTypeMap.put("rfi_number","convertNumeric");
		columnTypeMap.put("issue_date","date");
		columnTypeMap.put("boq_number","string");
		columnTypeMap.put("item_description","string");
		columnTypeMap.put("wi_remarks","string");
		columnTypeMap.put("from_chainage","numeric");
		columnTypeMap.put("to_chainage","numeric");
		columnTypeMap.put("side","string");
		columnTypeMap.put("layer","convertNumeric");
		columnTypeMap.put("status","string");
		columnTypeMap.put("grade","string");
		columnTypeMap.put("inspection_date","date");
		columnTypeMap.put("created_by_username","string");
		columnTypeMap.put("last_edited_by_username","string");
		columnTypeMap.put("approved_from","numeric");
		columnTypeMap.put("approved_to","numeric");
		columnTypeMap.put("approved_side","string");
		columnTypeMap.put("approved_layer","string");
		columnTypeMap.put("approval_Date","date");
		columnTypeMap.put("billNumber","string");
	}
	
	
	public void setRfiService(RFIService rfiService)
	{ 
		this.rfiService = rfiService; 
	}
	
	
	 @Override
	 protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 										HttpServletResponse response) throws Exception
	{
		
			 String pageString = request.getParameter("page");
			 int page = 1;
			 if(pageString != null && !StringUtils.isEmptyOrWhitespaceOnly(pageString))
			 {
				 page = Integer.parseInt(pageString);
			 }
			 String limitString = request.getParameter("rows");
			 int limit = 50;
			 if(limitString != null && !StringUtils.isEmptyOrWhitespaceOnly(limitString))
			 {
				 limit = Integer.parseInt(limitString);
			 }
			 String sidx = request.getParameter("sidx");
			 String sord = request.getParameter("sord");
			 
			 if ( sord == null)
			 {
				 sord = "desc";
			 }
			 
			 int totalPages = 0;
			 if(sidx == null || StringUtils.isEmptyOrWhitespaceOnly(sidx))
			 {
				 sidx = "rfi_number";
			 }
			 if("convertNumeric".equals(columnTypeMap.get(sidx)))
			 {
				 sidx = "convert("+sidx+",SIGNED) "+sord+","+ sidx+" "+sord;
			 }
			 else
			 {
				 sidx = sidx+" "+sord;
			 }
			 
			 sidx = " order by "+sidx+ " ";
			 
			StringBuffer where = new StringBuffer();
			Date todayDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = dateFormat.format(todayDate);
			if(request.getParameter("prepared") != null && "approvedOnly".equals(request.getParameter("prepared") ))
			{
				where.append(" WHERE status='Approved' and (bill_number = '' or bill_number IS NULL) ");
			}
			else if((request.getParameter("billed") != null && "billed".equals(request.getParameter("billed") )))
			{
				String billNumber = request.getParameter("billNumber");
				where.append(" WHERE bill_number = '"+billNumber+"' ");
			}
			else
			{
				where.append(" WHERE status='Approved' and bill_number = 'Under Preparation' ");
			}
			String searchOn = request.getParameter("_search");
			if("true".equals(searchOn))
			{
				String filterString = request.getParameter("filters");
				if(filterString != null && !StringUtils.isEmptyOrWhitespaceOnly(filterString))
				{
					JSONReader filterReader = new JSONValidatingReader();
					HashMap groupElement = (HashMap)filterReader.read(filterString);
					String condition = addRuletoWhere(groupElement);	
					if(condition != null && !StringUtils.isEmptyOrWhitespaceOnly(condition))
					{
						where.append(" AND ").append(condition);
					}
				}
			 	Enumeration en = request.getParameterNames();
			 	while (en.hasMoreElements())
			 	{
			 		String parameterName = (String) en.nextElement();
			 		if(columnTypeMap.containsKey(parameterName))
			 		{
			 			String parameterValue = request.getParameter(parameterName);
			 			if("numeric".equals(columnTypeMap.get(parameterName)))
			 			{
			 				String data = "";
			 				String op = "";
			 				if (parameterValue.startsWith("<") || parameterValue.startsWith(">") || parameterValue.startsWith("="))
			 				{
			 					data = parameterValue.substring(1);
			 					op = parameterValue.substring(0, 1);
			 				}
			 				else if (parameterValue.startsWith("<=") || parameterValue.startsWith(">="))
			 				{
			 					data = parameterValue.substring(2);
			 					op = parameterValue.substring(0, 2);
			 				}
			 				else
			 				{
			 					data = parameterValue+"%";
			 					op = " like ";
			 				}
			 				where.append(" AND "+parameterName+op+" '"+data+"' ");
			 			}
			 			else if("convertNumeric".equals(columnTypeMap.get(parameterName)))
			 			{
			 				String data = "";
			 				String op = "";
			 				if (parameterValue.startsWith("<") || parameterValue.startsWith(">") || parameterValue.startsWith("="))
			 				{
			 					data = parameterValue.substring(1);
			 					op = parameterValue.substring(0, 1);
			 				}
			 				else if (parameterValue.startsWith("<=") || parameterValue.startsWith(">="))
			 				{
			 					data = parameterValue.substring(2);
			 					op = parameterValue.substring(0, 2);
			 				}
			 				else
			 				{
			 					data = parameterValue+"%";
			 					op = " like ";
			 				}
			 				where.append(" AND convert("+parameterName+",SIGNED) "+op+"'"+data+"' ");
			 			}
			 			else if("string".equals(columnTypeMap.get(parameterName)))
			 			{
			 				where.append(" AND "+parameterName+" LIKE '"+parameterValue+"%' ");
			 			}
			 			else if("date".equals(columnTypeMap.get(parameterName)))
			 			{
			 				String data = "";
			 				String op = "";
			 				if (parameterValue.startsWith("<") || parameterValue.startsWith(">") || parameterValue.startsWith("="))
			 				{
			 					String dateTokens[] = parameterValue.substring(1).split("-");
			 					op = parameterValue.substring(0, 1);
				 				if(dateTokens.length == 3)
				 				{
				 					String dd = dateTokens[0];
				 					String mm = dateTokens[1];
				 					String yyyy = dateTokens[2];
				 					data = yyyy+"-"+mm+"-"+dd;
				 					
				 				}
				 				where.append(" AND DATE_FORMAT("+parameterName+",'%Y-%m-%d') "+op+" '"+data+"' ");
			 				}
			 				else if (parameterValue.startsWith("<=") || parameterValue.startsWith(">="))
			 				{
			 					op = parameterValue.substring(0, 2);
			 					String dateTokens[] = parameterValue.substring(2).split("-");
				 				if(dateTokens.length == 3)
				 				{
				 					String dd = dateTokens[0];
				 					String mm = dateTokens[1];
				 					String yyyy = dateTokens[2];
				 					data = yyyy+"-"+mm+"-"+dd;
				 					
				 				}
				 				where.append(" AND DATE_FORMAT("+parameterName+",'%Y-%m-%d') "+op+" '"+data+"' ");
			 				}
			 				else
				 			{
			 					where.append(" AND DATE_FORMAT("+parameterName+",'%d-%m-%Y') LIKE '"+parameterValue+"%' ");
				 			}
			 			}
			 		}
		 		}
			 }
			 
			 Integer expandedRFIListSize = rfiService.getExpandedRfiListSize(where.toString());
			 
			 if(expandedRFIListSize > 0 && limit > 0)
			 {
				totalPages = (expandedRFIListSize%limit)>0?expandedRFIListSize/limit+1:expandedRFIListSize/limit;
			 }
			 
			 if(page > totalPages)
			 {
				 page = totalPages;
			 }
			 
			 int start = limit *(page-1);
			 
			 if(start < 0)
			 {
				 start = 0;
			 }
			 
			JAXBContext context = JAXBContext.newInstance(ExpandedRFIList.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			StringWriter sw = new StringWriter();
			List<ExpandedRFI> expandedRFIList = null; 
			if("excel".equals(request.getParameter("oper")))
			{
				expandedRFIList = rfiService.getExpandedRfiList(start,0, sidx, where.toString());
			}
			else
			{
				expandedRFIList = rfiService.getExpandedRfiList(start,limit, sidx, where.toString());
			}
			m.marshal(new ExpandedRFIList(expandedRFIList), sw); 
			String xml = sw.toString();
			sw.close();
			
			String rows = ("<rows><page>"+page+"</page><total>"+totalPages+"</total><records>"+expandedRFIListSize+"</records>");
			if(xml.indexOf("<row>") != -1)
			{
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +rows+ xml.substring(xml.indexOf("<row>"),xml.indexOf("</rows>"))+"</rows>";
			}
			else
			{
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +rows+"</rows>";
			}
			xml = xml.replaceAll("<cell xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>", "<cell></cell>");
			//FileWriter fw = new FileWriter("D:\\check.txt");
			//fw.write(xml);
			//fw.close();
			//System.out.println(xml);
			
			if("excel".equals(request.getParameter("oper")))
			{
				colors = new RGBColor();
				
				WorkbookSettings wbSettings = new WorkbookSettings();
				wbSettings.setLocale(new Locale("en", "EN"));
				WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream(), wbSettings);
				
				wb = workbook;
				write(workbook, expandedRFIList);
				
				response.setContentType("application/vnd.ms-excel");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Disposition", "attachment;filename=rfiExport.xls");
				response.setHeader("Cache-Control", "max-age=0");
				
				workbook.write();
				workbook.close();
				
			}
			else
			{
				xml = xml.replaceAll("&","&amp;");
				response.setContentType("text/xml");
			    PrintWriter out = response.getWriter();
			    out.println(xml);
			    out.close();
			}
		    return null;
		 
	}
	 
	 public String getOperator(String op)
	 {
		 return operatorMap.get(op);
		 
	 }
	 
	 private String addRuletoWhere(HashMap groupElement)
	 {
		 StringBuffer where = new StringBuffer("");
		 String groupOp = (String)groupElement.get("groupOp");
			ArrayList groups = (ArrayList)groupElement.get("groups");
			ArrayList rulesList = (ArrayList)groupElement.get("rules");
		 Iterator it = rulesList.iterator();
			int i = 0;
			while(it.hasNext())
			{
				HashMap rule = (HashMap)it.next();
				String field = (String)rule.get("field");
				String op = (String)rule.get("op");
				String data = (String)rule.get("data");
				String condition = "";
				if("string".equals(columnTypeMap.get(field)))
				{
					if(data != null && !StringUtils.isEmptyOrWhitespaceOnly(data))
					{
						condition = field + operatorMap.get(op) + "'"+data+"'";
					}
					else
					{
						if("eq".equals(op))
						{
							condition = " ("+field+" IS NULL OR "+field+"='') ";
						}
						else
						{
							condition = field + operatorMap.get(op) + "'"+data+"'";
						}
					}
				}
				else if("date".equals(columnTypeMap.get(field)))
				{
					
					String value = data;
					String dateTokens[] = data.split("-");
	 				if(dateTokens.length == 3)
	 				{
	 					String dd = dateTokens[0];
	 					String mm = dateTokens[1];
	 					String yyyy = dateTokens[2];
	 					value = yyyy+"-"+mm+"-"+dd;
	 				}
					if(data != null && !StringUtils.isEmptyOrWhitespaceOnly(data))
					{
						condition = "DATE_FORMAT("+field+",'%Y-%m-%d') " + operatorMap.get(op) + "'"+value+"'";
					}
					else
					{
						if("eq".equals(op))
						{
							condition = " "+field + " IS NULL ";
						}
						else
						{
							condition = "DATE_FORMAT("+field+",'%d-%m-%Y') " + operatorMap.get(op) + "'"+data+"'";
						}
					}
				}
				else if ("numeric".equals(columnTypeMap.get(field)))
				{
					if(data != null && !StringUtils.isEmptyOrWhitespaceOnly(data))
					{
						condition = field + operatorMap.get(op) + "'"+data+"'";
					}
					else
					{
						if("eq".equals(op))
						{
							condition = " ("+field+" IS NULL OR "+field+"='') ";
						}
						else
						{
							condition = field + operatorMap.get(op) + "'"+data+"'";
						}
					}
				}
				else if ("convertNumeric".equals(columnTypeMap.get(field)))
				{
					if(data != null && !StringUtils.isEmptyOrWhitespaceOnly(data))
					{
						if("lt".equals(op) || "gt".equals(op) || "le".equals(op) || "ge".equals(op))
						{
							condition = "convert("+field+",SIGNED)" + operatorMap.get(op) + data;
						}
						else
						{
							condition = field + operatorMap.get(op) + "'"+data+"'";
						}
					}
					else
					{
						if("eq".equals(op))
						{
							condition = " ("+field+" IS NULL OR "+field+"='') ";
						}
						else
						{
							condition = field + operatorMap.get(op) + "'"+data+"'";
						}
					}
				}
				if (i==0)
				{
					where.append(" (");
					where.append(condition);
				}
				else
				{
					where.append(" " +groupOp+" ");
					where.append(condition);
				}
				++i;
			}
			if(groups != null)
			{
				Iterator groupsIterator = groups.iterator();
				while (groupsIterator.hasNext())
				{
					HashMap subGroupElement = (HashMap) groupsIterator.next();
					String bgf = where.substring(0, 2);
					if (" (".equals(where.substring(0, 2)))
					{
						where.append(" " +groupOp+" ").append(addRuletoWhere(subGroupElement));
					}
					else
					{
						where.append(" (").append(addRuletoWhere(subGroupElement));
					}
				}
			}
			where.append(")"); 
			return where.toString();
	 }
	 
	 
	 
	 public void write(WritableWorkbook workbook, List<ExpandedRFI>expandedRFIList) throws IOException, WriteException {
		 	
		 	workbook.createSheet("Report", 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			createLabel(excelSheet);
			if (expandedRFIList != null)
			{
				createContent(excelSheet, expandedRFIList);
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
			addLabel(sheet, 0, 0, "RFI Code");
			addLabel(sheet, 1, 0, "RFI Number");
			addLabel(sheet, 2, 0, "Issue Date");
			addLabel(sheet, 3, 0, "Item");
			addLabel(sheet, 4, 0, "WI Remarks");
			addLabel(sheet, 5, 0, "From Chainage");
			addLabel(sheet, 6, 0, "To Chainage");
			addLabel(sheet, 7, 0, "Side");
			addLabel(sheet, 8, 0, "Layer");
			addLabel(sheet, 9, 0, "Status");
			addLabel(sheet, 10, 0, "Grade");
			addLabel(sheet, 11, 0, "Inspection date");
			addLabel(sheet, 12, 0, "Created By");
			addLabel(sheet, 13, 0, "Last Updated By");
			addLabel(sheet, 14, 0, "Appr From");
			addLabel(sheet, 15, 0, "Appr To");
			addLabel(sheet, 16, 0, "Appr Side");
			addLabel(sheet, 17, 0, "Appr Layer");
			addLabel(sheet, 18, 0, "Appr Date");
			addLabel(sheet, 19, 0, "Bill Number");
			addLabel(sheet, 20, 0, "Remarks");
			

		}

		private void createContent(WritableSheet sheet, List<ExpandedRFI> expandedRFIList) throws WriteException,
				RowsExceededException {
			Iterator<ExpandedRFI> it = expandedRFIList.iterator();
			int rowNumber = 1;
			while(it.hasNext())
			{
				ExpandedRFI expandedRFI = it.next();
				
				addString(sheet,0,rowNumber,expandedRFI.getRfiCode());
				addString(sheet,1,rowNumber,expandedRFI.getRfiNumber());
				addDate(sheet,2,rowNumber,expandedRFI.getIssueDate());
				addString(sheet,3,rowNumber,expandedRFI.getItemDescription());
				addString(sheet,4,rowNumber,expandedRFI.getWiRemarks());
				addNumber(sheet,5,rowNumber,expandedRFI.getFromChainage());
				addNumber(sheet,6,rowNumber,expandedRFI.getToChainage());
				addString(sheet,7,rowNumber,expandedRFI.getSide());
				addString(sheet,8,rowNumber,expandedRFI.getLayer());
				addString(sheet,9,rowNumber,expandedRFI.getStatus());
				addString(sheet,10,rowNumber,expandedRFI.getGrade());
				addLongDate(sheet,11,rowNumber,expandedRFI.getInspectionDate());
				addString(sheet,12,rowNumber,expandedRFI.getCreatedByUserName());
				addString(sheet,13,rowNumber,expandedRFI.getLastEditedByUserName());
				addNumber(sheet,14,rowNumber,expandedRFI.getApprovedFrom());
				addNumber(sheet,15,rowNumber,expandedRFI.getApprovedTo());
				addString(sheet,16,rowNumber,expandedRFI.getApprovedSide());
				addString(sheet,17,rowNumber,expandedRFI.getApprovedLayer());
				addDate(sheet,18,rowNumber,expandedRFI.getApprovalDate());
				addString(sheet,19,rowNumber,expandedRFI.getBillNumber());
				addString(sheet,20,rowNumber,expandedRFI.getRemarks());
				
				
				
				++rowNumber;
			}
			for(int x=0;x<20;x++)
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
