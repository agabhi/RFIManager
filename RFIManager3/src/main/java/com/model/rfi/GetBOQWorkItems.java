package com.model.rfi;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.stringtree.json.JSONReader;
import org.stringtree.json.JSONValidatingReader;

import com.mysql.jdbc.StringUtils;

public class GetBOQWorkItems extends AbstractController
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
		 String activity = request.getParameter("activity");
		 String boqNumber = request.getParameter("boqNumber");
		 String noBoqNumber = request.getParameter("noBoqNumber");
		 String itemDesc = request.getParameter("itemDesc");
		 String noItemDesc = request.getParameter("noItemDesc");
		 
		 StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><data>");
		 
		 if(boqNumber != null || noBoqNumber != null)
		 {
			 List<String> items = null;
			 
			 String where = " where activity = '"+activity+"' ";
			 
			 if(boqNumber != null && !StringUtils.isEmptyOrWhitespaceOnly(boqNumber))
			 {
				 where += " AND boq_number = '"+boqNumber+"' ";
				 System.out.println("BOQ number is - "+boqNumber);
				   
			 }
			 items = rfiService.getActivityWorkItemsDescription(where);
			 String firstItem = "";
			 xml.append("<items>");
			 if (items != null)
			 {
				 Iterator<String> it = items.iterator();
				 int count = 0;
				 while (it.hasNext())
				 {
					 String item = it.next();
					 if(count == 0)
					 {
						 firstItem = item;
						 
					 }
					 if(item !=null)
					 {
						 xml.append("<item>"+item+"</item>");
					 }
					 
					 ++count;
				 }
				 xml.append("</items>");
				 if(firstItem != null && !StringUtils.isEmptyOrWhitespaceOnly(firstItem))
				 {
					 WorkItem wi = rfiService.getWorkItem(firstItem);
					 if ( wi != null)
					 {
						Set<String> sides = wi.getSides();
						
						if (sides != null && noItemDesc == null && noBoqNumber == null)
						{
							xml.append("<sides>");
							Iterator<String> itSide = sides.iterator();
							while(itSide.hasNext())
							{
								String side = itSide.next();
								if(side != null && !StringUtils.isEmptyOrWhitespaceOnly(side))
								{
									xml.append("<side>"+side+"</side>");
								}
							}
							xml.append("</sides>");
						}
						
						Set<String> layers = wi.getLayers();
						
						if (layers != null && noItemDesc == null && noBoqNumber == null)
						{
							xml.append("<layers>");
							Iterator<String> itlayer = layers.iterator();
							while(itlayer.hasNext())
							{
								String layer = itlayer.next();
								if(layer != null && !StringUtils.isEmptyOrWhitespaceOnly(layer))
								{
									xml.append("<layer>"+layer+"</layer>");
								}
							}
							xml.append("</layers>");
						}
					 }
				 }
			 }
		 }
		 
		 else if (itemDesc != null || noItemDesc != null)
		 {
			 String where = " where activity = '"+activity+"' ";
			 
			 if(itemDesc != null && !StringUtils.isEmptyOrWhitespaceOnly(itemDesc))
			 {
				 where += " AND item_description = '"+itemDesc+"' ";
				 
				 WorkItem wi = rfiService.getActivityWorkItem(itemDesc, activity);
				 if ( wi != null)
				 {
					Set<String> sides = wi.getSides();
					
					if (sides != null)
					{
						xml.append("<sides>");
						Iterator<String> itSide = sides.iterator();
						while(itSide.hasNext())
						{
							String side = itSide.next();
							if(side != null && !StringUtils.isEmptyOrWhitespaceOnly(side))
							{
								xml.append("<side>"+side+"</side>");
							}
						}
						xml.append("</sides>");
					}
					
					Set<String> layers = wi.getLayers();
					
					if (layers != null)
					{
						xml.append("<layers>");
						Iterator<String> itlayer = layers.iterator();
						while(itlayer.hasNext())
						{
							String layer = itlayer.next();
							if(layer != null && !StringUtils.isEmptyOrWhitespaceOnly(layer))
							{
								xml.append("<layer>"+layer+"</layer>");
							}
						}
						xml.append("</layers>");
					}
				 }
				 
			 }
			 List<String> boqNumbers = rfiService.getActivityBoqNumberList(where);
			 System.out.println(boqNumbers);
			 xml.append("<boqNumbers>");
			 if (boqNumbers != null)
			 {
				 Iterator<String> it = boqNumbers.iterator();
				 while (it.hasNext())
				 {
					 String boqNumber1 = it.next();
					 if(boqNumber1 !=null)
					 {
						 xml.append("<boqNumber>"+boqNumber1+"</boqNumber>");
					 }
					 
				 }
				 xml.append("</boqNumbers>");
				 
			 }
		 }
		 
		 xml.append("</data>");
		String xmlString = xml.toString().replaceAll("&","&amp;");
		System.out.println(xmlString);
		response.setContentType("text/xml");
	    PrintWriter out = response.getWriter();
	    out.println(xmlString);
	    out.close();
	    return null;
	}	

}
