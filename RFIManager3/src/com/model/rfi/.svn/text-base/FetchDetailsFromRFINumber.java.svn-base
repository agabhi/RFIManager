package com.model.rfi;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.stringtree.json.JSONReader;
import org.stringtree.json.JSONValidatingReader;

import com.mysql.jdbc.StringUtils;

public class FetchDetailsFromRFINumber extends AbstractController
{
	
	private RFIService rfiService;
	
	
	public void setRfiService(RFIService rfiService)
	{ 
		this.rfiService = rfiService; 
	}
	
	
	 @Override
	 protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 										HttpServletResponse response) throws Exception
	{
		 
		 String rfiNumber = request.getParameter("rfiNumber");
		 RFI rfi = null;
		 if(rfiNumber != null && !StringUtils.isEmptyOrWhitespaceOnly(rfiNumber))
		 {
			 System.out.println("RFI number is - "+rfiNumber);
			 rfi = rfiService.getRFI(rfiNumber);
			 String where = " where item_description = '"+rfi.getItemDescription()+"' ";
			 List<String> boqNumberList = rfiService.getBoqNumberList(where);
			 String boqNumber = boqNumberList.get(0);
			 rfi.setBoqNumber(boqNumber);
		 }
		 
		 
		JAXBContext context = JAXBContext.newInstance(RFI.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		StringWriter sw = new StringWriter();
		m.marshal(rfi, sw); 
		String xml = sw.toString();
		sw.close();
		/*
		String rows = ("<rows><page>"+page+"</page><total>"+totalPages+"</total><records>"+expandedRFIListSize+"</records>");
		if(xml.indexOf("<row>") != -1)
		{
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +rows+ xml.substring(xml.indexOf("<row>"),xml.indexOf("</rows>"))+"</rows>";
		}
		else
		{
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +rows+"</rows>";
		}
		*/
		xml = xml.replaceAll("<cell xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>", "<cell></cell>");
		//FileWriter fw = new FileWriter("D:\\check.txt");
		//fw.write(xml);
		//fw.close();
		xml = xml.replaceAll("&","&amp;");
		System.out.println(xml);
		response.setContentType("text/xml");
	    PrintWriter out = response.getWriter();
	    out.println(xml);
	    out.close();
	    return null;
	}	

}
