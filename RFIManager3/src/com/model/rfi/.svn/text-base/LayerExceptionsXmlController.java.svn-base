package com.model.rfi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
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

public class LayerExceptionsXmlController extends AbstractController
{
	
	private RFIService rfiService;

	private static HashMap<String,String> operatorMap = new HashMap<String,String>();
	private static HashMap<String,String> columnTypeMap = new HashMap<String,String>();
	
	static
	{
		operatorMap.put("eq","=");
		operatorMap.put("ne","<>");
		operatorMap.put("lt","<");
		operatorMap.put("gt",">");
		operatorMap.put("le","<=");
		operatorMap.put("ge",">=");
		
		columnTypeMap.put("activity","string");
		columnTypeMap.put("item_description","string");
		columnTypeMap.put("from_chainage","numeric");
		columnTypeMap.put("to_chainage","numeric");
		columnTypeMap.put("side","string");
		columnTypeMap.put("layer","convertNumeric");
		
	}
	
	
	public void setRfiService(RFIService rfiService)
	{ 
		this.rfiService = rfiService; 
	}
	
	
	 @Override
	 protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 										HttpServletResponse response) throws Exception
	{
		 
		 	String deleteException  =  request.getParameter("deleteException");
			if(deleteException != null && !StringUtils.isEmptyOrWhitespaceOnly(deleteException))
			{
				String[] exceptionId = request.getParameterValues("exceptionId");
				if( exceptionId != null && exceptionId.length > 0)
				{
					for (int i = 0; i<exceptionId.length; ++i)
					{
						rfiService.deleteLayerExceptions(Long.valueOf(exceptionId[i]));
					}
				}
				
				return null;
			}
		 
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
				 sidx = " rfi_number ";
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
			where.append(" WHERE 1=1 ");
			
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
			 
			 Integer layerExceptionsListSize = rfiService.getLayerExceptionsListSize(where.toString());
			 
			 if(layerExceptionsListSize > 0 && limit > 0)
			 {
				totalPages = (layerExceptionsListSize%limit)>0?layerExceptionsListSize/limit+1:layerExceptionsListSize/limit;
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
			 
			JAXBContext context = JAXBContext.newInstance(LayerExceptionsList.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			StringWriter sw = new StringWriter();
			List<LayerException> layerExceptionsList = rfiService.getLayerExceptionsList(start,limit, sidx, where.toString());
			
			m.marshal(new LayerExceptionsList(layerExceptionsList), sw); 
			String xml = sw.toString();
			sw.close();
			
			String rows = ("<rows><page>"+page+"</page><total>"+totalPages+"</total><records>"+layerExceptionsListSize+"</records>");
			if(xml.indexOf("<row>") != -1)
			{
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +rows+ xml.substring(xml.indexOf("<row>"),xml.indexOf("</rows>"))+"</rows>";
			}
			else
			{
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +rows+"</rows>";
			}
			xml = xml.replaceAll("<cell xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>", "<cell></cell>");
			
			xml = xml.replaceAll("&","&amp;");
			response.setContentType("text/xml");
			PrintWriter out = response.getWriter();
			out.println(xml);
			out.close();
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
	 
}