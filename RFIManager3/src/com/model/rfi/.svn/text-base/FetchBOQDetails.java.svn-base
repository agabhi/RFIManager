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

public class FetchBOQDetails extends AbstractController
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
		 
		 String boqNumber = request.getParameter("boqNumber");
		 BOQ boq = null;
		 if(boqNumber != null && !StringUtils.isEmptyOrWhitespaceOnly(boqNumber))
		 {
			 System.out.println("BOQ number is - "+boqNumber);
			 boq = rfiService.getBoq(boqNumber);
		 }
		 
		 
		JAXBContext context = JAXBContext.newInstance(BOQ.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		StringWriter sw = new StringWriter();
		m.marshal(boq, sw); 
		String xml = sw.toString();
		sw.close();
		
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
