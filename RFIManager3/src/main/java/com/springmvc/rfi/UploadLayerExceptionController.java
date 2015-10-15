package com.springmvc.rfi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.Activity;
import com.model.rfi.BOQ;
import com.model.rfi.LayerException;
import com.model.rfi.Quantity;
import com.model.rfi.WorkItem;
import com.model.rfi.Grade;
import com.model.rfi.Layer;
import com.model.rfi.RFI;
import com.model.rfi.RFIApproval;
import com.model.rfi.RFICode;
import com.model.rfi.RFIService;
import com.model.rfi.Side;
import com.model.rfi.Status;
import com.model.rfi.UploadForm;
import com.mysql.jdbc.StringUtils;

public class UploadLayerExceptionController extends SimpleFormController{
	
	private RFIService rfiService;
	
	private List<LayerException> existingLayerExceptionList;
	private List<Side> existingSideList;
	private List<WorkItem> existingItemList;
	private List<Layer> existingLayerList;
	private List<Activity> existingActivityList;
	
	
	public void setRfiService(RFIService rfiService) { 
		this.rfiService = rfiService; 
		}
		
		@Override
	    protected ModelAndView onSubmit(HttpServletRequest req, 
	                                    HttpServletResponse res, 
	                                    Object command, 
	                                    BindException errors) throws Exception {
//	        res.setContentType("text/plain");
	        if (!(req instanceof MultipartHttpServletRequest)) {
	            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected multipart request");
	            return null;
	        }
	        UploadForm bean = (UploadForm) command;
	        MultipartFile file = bean.getFile();
	        File destination = new File("C:\\temp\\" + file.getOriginalFilename());
	        file.transferTo(destination);
	        File errorsFile = uploadQuantityFile(destination);
//	        res.getWriter().write("Success, wrote to " + destination.getAbsolutePath());        
//	        res.flushBuffer();
	        //return new ModelAndView(getSuccessView(),"destination",destination.getAbsolutePath());
	        ServletOutputStream stream = null;
	        BufferedInputStream buf = null;
	        
	        stream = res.getOutputStream();

	        //set response headers
	        res.setContentType("application/txt");
	        res.addHeader("Content-Disposition", "attachment; filename=errors.txt");

	        

	        FileInputStream fis = new FileInputStream(errorsFile);
	        buf = new BufferedInputStream(fis);
	        int readBytes = 0;

	        //read from the file; write to the ServletOutputStream
	        while ((readBytes = buf.read()) != -1)
	          stream.write(readBytes);
	        
	        stream.close();
	        buf.close();
	        fis.close();
	        
	        return new ModelAndView("redirect:layerExceptions.htm");
	        

	        
	    }



public File uploadQuantityFile(File file) {
	
	File errorsFile = new File("C:\\temp\\errors_123"+".txt");
	FileWriter fos = null;
	try
	{
		
		fos = new FileWriter(errorsFile);
		try
		{
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			HashSet<LayerException> layerExceptionHash = new HashSet<LayerException>();
			
			existingLayerExceptionList = rfiService.getLayerExceptionsList(0, 0, "", "");
			existingSideList = rfiService.getSideObjectsList();
			existingActivityList = rfiService.getActivityObjectsList();
			existingLayerList = rfiService.getLayerObjectsList();
			Layer noLayer = new Layer();
			noLayer.setLayer("No Layer");
			existingLayerList.add(noLayer);
			existingItemList = rfiService.getWorkItemsList();
			
			System.out.println("*********Outside the loop************");
			System.out.println(sheet.getRows());
			
			int errorFound = 0;
				for (int i=1;i < sheet.getRows();++i)
				{
					try
					{
						int result = readAndAddRFI(sheet, layerExceptionHash, i, fos);
						if( result == 2)
						{
							continue;
						}
						
						if( result == 0)
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
						ex.printStackTrace();
						errorFound = 1;
						fos.write("*********Error reading record "+i+"************\n");
					}
				}
				if(errorFound == 0)
				{
					rfiService.addLayerExceptionList(layerExceptionHash);
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


private int readAndAddRFI(Sheet sheet, Set<LayerException> layerExceptionHash, int i, FileWriter fos) throws Exception
{

	LayerException layerException = new LayerException();
	
	 
	String itemCellContents = sheet.getCell(0,i).getContents();
	if(itemCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(itemCellContents))
	{
		return 1;
	}
	
	else
	{
		WorkItem wi = new WorkItem();
		wi.setItemDescription(itemCellContents.trim());
		if(!existingItemList.contains(wi))
		{
			fos.write("Record No. "+i+" - is not imported because Item '"+itemCellContents+"' is not present in the database.\n");
			return 0;
		}
		
		layerException.setItemDescription(itemCellContents.trim());
	}
	
	
	String activity = sheet.getCell(1,i).getContents(); 
	if(activity == null || StringUtils.isEmptyOrWhitespaceOnly(activity))
	{
		fos.write("Record No. "+i+" - is not imported because Activity cannot be blank.\n");
		return 0;
	}
	else
	{
		Activity a = new Activity();
		a.setActivity(activity);
		if(!existingActivityList.contains(a))
		{
			fos.write("Record No. "+i+" - is not imported because Activity '"+activity+"' is not present in the database.\n");
			return 0;
		}
		layerException.setActivity(activity.trim());
	}
	

	Cell fromCell = sheet.getCell(2,i); 
	String fromCellContents = sheet.getCell(2,i).getContents();
	if(fromCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(fromCellContents))
	{
		fos.write("Record No. "+i+" - is not imported because From Chainage should not be blank.\n");
		return 0;
	}
	if(!StringUtils.isEmptyOrWhitespaceOnly(fromCellContents))
	{
		if (fromCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) fromCell; 
			int fromChainage = (int)nc.getValue();
			layerException.setFromChainage(fromChainage);
		}
		else
		{
			//approveFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because From Chainage should be in the number format.\n");
			return 0;
		}
	}
	
	Cell toCell = sheet.getCell(3,i); 
	String toCellContents = sheet.getCell(3,i).getContents();
	if(toCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(toCellContents))
	{
		fos.write("Record No. "+i+" - is not imported because To Chainage should not be blank.\n");
		return 0;
	}
	if(!StringUtils.isEmptyOrWhitespaceOnly(toCellContents))
	{
		if (toCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) toCell; 
			int toChainage = (int)nc.getValue();
			layerException.setToChainage(toChainage);
		}
		else
		{
			//approveFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because To Chainage should be in the number format.\n");
			return 0;
		}
	}
	
	if(layerException.getFromChainage() >= layerException.getToChainage())
	{
		fos.write("Record No. "+i+" - is not imported because To Chainage should strictly greater than From Chainage.\n");
		return 0;
	}
	
	
	String side = sheet.getCell(4,i).getContents(); 
	if(side == null || StringUtils.isEmptyOrWhitespaceOnly(side))
	{
		fos.write("Record No. "+i+" - is not imported because Side cannot be blank.\n");
		return 0;
	}
	else
	{
		Side s = new Side();
		s.setSide(side);
		if(!existingSideList.contains(s))
		{
			fos.write("Record No. "+i+" - is not imported because Side '"+side+"' is not present in the database.\n");
			return 0;
		}
		if(side.trim().equalsIgnoreCase("BS"))
		{
			fos.write("Record No. "+i+" - is not imported because Side '"+side+"' is not allowed. Use'LHS' and 'RHS'.\n");
			return 0;
		}
		layerException.setSide(side.trim());
	}
	
	String layer = sheet.getCell(5,i).getContents(); 
	if(layer == null || StringUtils.isEmptyOrWhitespaceOnly(layer))
	{
		fos.write("Record No. "+i+" - is not imported because Layer cannot be blank.\n");
		return 0;
	}
	else
	{
		Layer l = new Layer();
		l.setLayer(layer);
		if(!existingLayerList.contains(l))
		{
			fos.write("Record No. "+i+" - is not imported because Layer '"+layer+"' is not present in the database.\n");
			return 0;
		}
		layerException.setLayer(layer.trim());
	}
	
	
	if(existingLayerExceptionList.contains(layerException))
	{
		fos.write("Record No. "+i+" - is not imported because there is an overlapping entry in the database already.\n");
		return 0;
	}
	
	if(layerExceptionHash.contains(layerException))
	{
		fos.write("Record No. "+i+" - is not imported because there is an overlapping entry in the excel before this record.\n");
		return 0;
	}
	layerExceptionHash.add(layerException);
	System.out.println("*********End of the Loop************");
	return 2;
//		i++;
}
}