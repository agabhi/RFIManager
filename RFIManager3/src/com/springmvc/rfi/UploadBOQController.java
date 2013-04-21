package com.springmvc.rfi;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

import com.model.rfi.BOQ;
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

public class UploadBOQController extends SimpleFormController{
	
	private RFIService rfiService;
	
	
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
	        uploadBOQFile(destination);
//	        res.getWriter().write("Success, wrote to " + destination.getAbsolutePath());        
//	        res.flushBuffer();
	        return new ModelAndView(getSuccessView(),"destination",destination.getAbsolutePath());
	    }



public void uploadBOQFile(File file) {
	
	FileWriter fos = null;
	try
	{
		fos = new FileWriter("C:\\temp\\errors_123"+".txt");
		try
		{
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			HashSet<BOQ> boqHash = new HashSet< BOQ>();
			
			System.out.println("*********Outside the loop************");
			System.out.println(sheet.getRows());
			
				for (int i=1;i < sheet.getRows();++i)
				{
					try
					{
						if( readAndAddRFI(sheet, boqHash, i, fos) == 0)
						{
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
						System.out.println("*********Error reading record "+i+"************");
					}
				}
				
				rfiService.addBoqList(boqHash);
				

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
}


private int readAndAddRFI(Sheet sheet, Set<BOQ> boqHash, int i, FileWriter fos) throws Exception
{

	BOQ boq = new BOQ();
	
	 
	String categorySequenceCellContents = sheet.getCell(0,i).getContents();
	if(categorySequenceCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(categorySequenceCellContents))
	{
		return 1;
	}
	Cell categorySequenceCell = sheet.getCell(0,i);
	if(!StringUtils.isEmptyOrWhitespaceOnly(categorySequenceCellContents))
	{
		if (categorySequenceCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) categorySequenceCell; 
			int categorySequence = (int)nc.getValue();
			boq.setCategorySequence(categorySequence);
		}
		else
		{
			//approveFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because Category Sequence is not in the number format.");
			return 0;
		}
	}

	String category = sheet.getCell(1,i).getContents(); 
	
	if(category == null || StringUtils.isEmptyOrWhitespaceOnly(category))
	{
		fos.write("Record No. "+i+" - is not imported because Category cannot be blank.");
		return 0;
	}
	
	else
	{
		boq.setCategory(category.trim());
	}
	
	
	
	String boqNumber = sheet.getCell(2,i).getContents(); 
	if(boqNumber == null || StringUtils.isEmptyOrWhitespaceOnly(boqNumber))
	{
		fos.write("Record No. "+i+" - is not imported because BOQ Number cannot be blank.");
		return 0;
	}
	else
	{
		boq.setBoqNumber(boqNumber.trim());
		if(boqHash.contains(boq))
		{
			fos.write("Record No. "+i+" - is not imported because BOQ Number "+boqNumber+" is already present in the file.");
			return 0;
		}
	}
	
	String description = sheet.getCell(3,i).getContents(); 
	if(description == null || StringUtils.isEmptyOrWhitespaceOnly(description))
	{
		fos.write("Record No. "+i+" - is not imported because Description cannot be blank.");
		return 0;
	}
	else
	{
		boq.setDescription(description.trim());
	}
	
	String unit = sheet.getCell(4,i).getContents(); 
	if(unit == null || StringUtils.isEmptyOrWhitespaceOnly(unit))
	{
		//Write in Log for the row details not saved
		//return 0;
	}
	else
	{
		boq.setUnit(unit.trim());
	}
	
	Cell quantityCell = sheet.getCell(5,i); 
	String quantityCellContents = sheet.getCell(5,i).getContents();
	if(quantityCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(quantityCellContents))
	{
		//return 1;
	}
	if(!StringUtils.isEmptyOrWhitespaceOnly(quantityCellContents))
	{
		if (quantityCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) quantityCell; 
			double quantity = (double)nc.getValue();
			boq.setQuantity(quantity);
		}
		else
		{
			//approveFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because Quantity should either be blank or in the number format.");
			return 0;
		}
	}
	
	Cell rateCell = sheet.getCell(6,i); 
	String rateCellContents = sheet.getCell(6,i).getContents();
	if(rateCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(rateCellContents))
	{
		//return 1;
	}
	if(!StringUtils.isEmptyOrWhitespaceOnly(rateCellContents))
	{
		if (rateCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) rateCell; 
			double rate = (double)nc.getValue();
			boq.setRate(rate);
		}
		else
		{
			//approveFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because Rate should either be blank or in the number format.");
			return 0;
		}
	}
	
	
	Cell amountCell = sheet.getCell(7,i); 
	String amountCellContents = sheet.getCell(7,i).getContents();
	if(amountCellContents == null || StringUtils.isEmptyOrWhitespaceOnly(amountCellContents))
	{
		//return 1;
	}
	if(!StringUtils.isEmptyOrWhitespaceOnly(amountCellContents))
	{
		if (quantityCell.getType() == CellType.NUMBER)
		{ 
			NumberCell nc = (NumberCell) amountCell; 
			double amount = (double)nc.getValue();
			boq.setAmount(amount);
		}
		else
		{
			//approveFrom Chainage is not the number format
			fos.write("Record No. "+i+" - is not imported because Amount should either be blank or in the number format.");
			return 0;
		}
	}
	boqHash.add(boq);
	System.out.println("*********End of the Loop************");
	return 0;
//		i++;
}
}