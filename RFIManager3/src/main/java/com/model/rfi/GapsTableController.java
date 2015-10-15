package com.model.rfi;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.mysql.jdbc.StringUtils;

public class GapsTableController extends AbstractController
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
		 String barChartName = request.getParameter("saveBarChartName");
		 
		 if( barChartName != null && !StringUtils.isEmptyOrWhitespaceOnly(barChartName))
		 {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			String[] items = request.getParameterValues("items");
			 String[] sides = request.getParameterValues("sides");
			 String[] statuses = request.getParameterValues("statuses");
			 
			 BarChart barChart = new BarChart();
			 barChart.setBarChartName(barChartName);
			 barChart.setBarChartType("statusBarChart");
			 barChart.setUserName(username);
			 barChart.setItems(new HashSet<String>(Arrays.asList(items)));
			 barChart.setSides(new HashSet<String>(Arrays.asList(sides)));
			 barChart.setStatuses(new HashSet<String>(Arrays.asList(statuses)));
			 
			 rfiService.addBarChart(barChart);
			 
			 response.setContentType("text");
		     PrintWriter out = response.getWriter();
		     out.println("Bar chart saved successfully!");
		     out.close();
		     return null;
			
		 }
		 
		 int fromCh = 0;
		 int toCh = 10000;
		 int length = 0;
		 
		 //fromCh = Integer.parseInt(request.getParameter("from"));
		 //toCh = Integer.parseInt(request.getParameter("to"));
		 length = toCh - fromCh;
		 
		 String[] items = null;
		 String[] sides = null;
		 String[] statuses = null;
		 
		 String savedBarChartName = request.getParameter("savedBarChartName");
		 if( savedBarChartName != null && !StringUtils.isEmptyOrWhitespaceOnly(savedBarChartName))
		 {
			 BarChart barChart = rfiService.getStatusBarChart(savedBarChartName);
			 items = (String [])barChart.getItems().toArray(new String[0]);
			 sides = (String [])barChart.getSides().toArray(new String[0]);
			 statuses = (String [])barChart.getStatuses().toArray(new String[0]);
		 }
		 
		 else
		 {
			 items = request.getParameterValues("items");
			 sides = request.getParameterValues("sides");
			 statuses = request.getParameterValues("statuses");
			 
			 
		 }
		 
		 StringBuffer statusQuery = new StringBuffer("");
		 
		 for (int i=0; i<statuses.length; ++i)
		 {
			 System.out.println("Creating status");
			 String status  = statuses[i];
			 if(i == 0)
			 {
				 statusQuery.append("'"+status+"'");
			 }
			 else
			 {
				 statusQuery.append(",'"+status+"'");
			 }
		 }
		 
		 StringBuffer sideQuery = new StringBuffer("");
		 StringBuffer itemQuery = new StringBuffer("");
		 
		 List<String> itemList = rfiService.getItemsInSequence(items);
		 
		 int layerShowCount = 0;
		 
		 
		LinkedHashMap sideMap = new LinkedHashMap();
		 if(sides != null)
		 {
		 
			 for (int i=0; i<sides.length; ++i)
			 {
				 System.out.println("Creating side");
				 String side  = sides[i];
				 if(i == 0)
				 {
					 if("LHS".equals(side.trim()))
					 {
						 sideQuery.append("'"+side+"'");
						 sideQuery.append(",'"+"BS"+"'");
					}
					 else
					 {
						 sideQuery.append("'"+side+"'");
					 }
						 
				 }
				 else
				 {
					 if("RHS".equals(side.trim()))
					 {
						 sideQuery.append(",'"+side+"'");
						 sideQuery.append(",'"+"BS"+"'");
					}
					 else
					 {
						 sideQuery.append(",'"+side+"'");
					 }
				 }
				 
				 LinkedHashMap itemMap = new LinkedHashMap();
				 if (itemList != null)
				 	{
				 		
				 		System.out.println(""+items);
				 		System.out.println(items.length);
				 		Iterator<String> itItem = itemList.iterator();
				 		int j = 0;
					 	while (itItem.hasNext())
					 	{
				 			 String itemDescription = itItem.next();
				
				 			if(i ==0)
				 			{
					 			if(j == 0)
								 {
									 itemQuery.append("'"+itemDescription+"'");
								 }
								 else
								 {
									 itemQuery.append(",'"+itemDescription+"'");
								 }
				 			}
				 			
				 			++j;
				 			
				 			System.out.println("Creating workItem");
				 			List<String> layers = rfiService.getLayersForWorkItem(itemDescription);
						 	
						 	LinkedHashMap layerMap = new LinkedHashMap();
							if (layers != null)
							{
								Iterator<String> layerIt = layers.iterator();
								
								String[] beneathTopArray = new String[length/10];
								
								List<Object[]> topLayerList = rfiService.getTopLayerTableEntry(itemDescription, fromCh, toCh, side);
								
								if (topLayerList != null && topLayerList.size() > 0)
								{
									Iterator<Object[]> it = topLayerList.iterator();
									while (it.hasNext())
									{
										Object[] row = it.next();
										Integer from = (Integer)row[0];
										Integer to = (Integer)row[1];
										String beneathTopLayer = (String)row[2];
										
										for (int l=from+10; l <= to ; l=l+10)
										{
											if(l>= (fromCh+10) && l<= toCh)
											 {
												 int index = (l - fromCh)/10;
												 beneathTopArray[index-1]= beneathTopLayer;
											 }
										
										}
									}
								}
								
								while(layerIt.hasNext())
								{
									System.out.println("Creating layer");
									String layer = layerIt.next();
									
									int layerint  = 0;
									
									if( !"Top".equals(layer) && !"-".equals(layer))
									{
										layerint = Integer.parseInt(layer);
									}
									StatusBarChartCell[] chArray = new StatusBarChartCell[length/10]; //first element is value, second element is popup text
									
									
									int skipCount = 0;
									for (int k =0; k < beneathTopArray.length; ++k)
									{
										if(beneathTopArray[k] != null)
										{
											//entry present
											if( "No Layer".equals(beneathTopArray[k]))
											{
												//no layer at this point
												++skipCount;
												continue;
												
											}
											
											//
											else if("Top".equalsIgnoreCase(beneathTopArray[k]))
											{
												if(!"Top".equalsIgnoreCase(layer))
												{
													//no layer at this point
													++skipCount;
													continue;
												}
												
												else
												{
													chArray[k] = new StatusBarChartCell();
												}
												
											}
											
											else if("-".equalsIgnoreCase(beneathTopArray[k]))
											{
												if(!"-".equalsIgnoreCase(layer))
												{
													//no layer at this point
													++skipCount;
													continue;
												}
												
												else
												{
													chArray[k] = new StatusBarChartCell();
												}
												
											}
											
											else if(Integer.parseInt(beneathTopArray[k]) > 0)
											{
												if(layerint > Integer.parseInt(beneathTopArray[k]))
												{
													//should not show
													++skipCount;
													continue;
												}
												
												else
												{
													chArray[k] = new StatusBarChartCell();
												}
											}
										}
										else
										{
											chArray[k] = new StatusBarChartCell();
										}
									}
									 
									 System.out.println("Chainage Array Length is "+chArray.length);
									 if(skipCount < (length/10))
									 {
										 layerMap.put(layer, chArray);
										 ++layerShowCount;
									 }
								}
									 
							}
							itemMap.put(itemDescription, layerMap);
				 		}
					 
				 }
				 sideMap.put(side, itemMap);
			 }
		 }
						 
							 
								 
		
		System.out.println(sideMap);
		List<ExpandedRFI>  expandedRFI = rfiService.getExpandedRfiList(0, 0, "", " where item_description in ("+itemQuery.toString()+") and status in ("+statusQuery.toString()+") and side in ("+sideQuery.toString()+") and ((from_chainage >= "+fromCh+" and from_chainage < "+toCh+") or (to_chainage > "+fromCh+" and to_chainage <= "+toCh+"))");
		 
		 Iterator<ExpandedRFI> expandedRFIIt = expandedRFI.iterator();
		 
		 Map<String,String> statusColorMap = null;
		 
		 if(request.getSession().getAttribute("statusColorMap") != null)
		 {
			 statusColorMap = (HashMap<String, String>)request.getSession().getAttribute("statusColorMap");
		 }
		 else
		 {
			 statusColorMap = new HashMap<String, String>();
		 }
		 int colorCount = 0;
		 String[] seriesColors = {"#4bb2c5", "#EAA228", "#c5b47f", "#579575", "#839557", "#958c12", "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc", "#c747a3", "#cddf54", "#FBD178", "#26B4E3", "#bd70c7"};
		 
		 while (expandedRFIIt.hasNext())
		 {
			 ExpandedRFI expandedRfi =  expandedRFIIt.next(); //from and too should be there, side and item condition, only approved ones.
			 String item = expandedRfi.getItemDescription();
			 String status = expandedRfi.getStatus();
			 if (!statusColorMap.containsKey(status.trim()))
			 {
				 statusColorMap.put(status.trim(),seriesColors[colorCount%seriesColors.length]);
				 ++colorCount;
			 }
			 String rfiNumber = expandedRfi.getRfiNumber();
			 String issueDateAsText = expandedRfi.getIssueDateAsText();
			 Integer from = null;
			 Integer to = null;
			 String side = null;
			 String layer = null;
			 if(status != null && "Approved".equals(status.trim()))
			 {
				 from = expandedRfi.getApprovedFrom();
				 to = expandedRfi.getApprovedTo();
				 side = expandedRfi.getApprovedSide();
				 layer = expandedRfi.getApprovedLayer();
				 
			 }
			 else
			 {
				 from = expandedRfi.getFromChainage();
				 to = expandedRfi.getToChainage();
				 side = expandedRfi.getSide();
				 layer = expandedRfi.getLayer();
			}
			 
			int sideCount = 1;
			if ("BS".equals(side))
			{
				sideCount = 2;
			}
			
			for (int z=0; z < sideCount; ++z)
			{
				if(sideCount == 2 && z == 0)
				{
					side="LHS";
				}
				else if (sideCount == 2 && z == 0)
				{
					side="RHS";
				}
				if( from != null && to != null)
				if(side != null && layer != null)
				{
					Map itemMap = (Map)sideMap.get(side);
					if(itemMap != null)
					{
						 Map layerMap = (LinkedHashMap)itemMap.get(item);
						 if (layerMap != null)
						 {
							 StatusBarChartCell[] chArray = (StatusBarChartCell[])layerMap.get(layer);
							 if (chArray != null)
							 {
								 for (Integer i = from+10;i<=to; i=i+10)
								 {
									 if(i>= (fromCh+10) && i<= toCh)
									 {
										 int index = (i - fromCh)/10;
										 
										 if (chArray[index-1] == null)
										 {
											 chArray[index-1] = new StatusBarChartCell();
										 }
										 if(!(chArray[index-1].getStatus() != null && "Approved".equals(chArray[index-1].getStatus().trim())))
										 {
											 chArray[index-1].setStatus(status);
											 chArray[index-1].setColor(statusColorMap.get(status.trim()));
											 chArray[index-1].setPopup("Status = "+status+", RFI Number = "+rfiNumber+", From Chainage = "+from+", To Chainage = "+to+", Issue Date = "+issueDateAsText);
										 }
									 }
								 }
							 }
						 }
					}
				}
			}
		}
		 
		 request.setAttribute("layerShowCount", layerShowCount+1);
		 request.setAttribute("statusColorMap", statusColorMap);
		 statusColorMap.put("Gap","red");
		 request.setAttribute("sideMap", sideMap);
		request.getRequestDispatcher("/jsp/gapsTableLayout.jsp").forward(request, response);
		return null;
	}	

}
