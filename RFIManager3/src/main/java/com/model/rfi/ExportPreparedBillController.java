package com.model.rfi;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

public class ExportPreparedBillController extends AbstractController {
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
	
	public void setRfiService(RFIService rfiService)
	{ 
		this.rfiService = rfiService; 
	}
	
	
	 @Override
	 protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 										HttpServletResponse response) throws Exception
	{
		 
		LinkedHashMap itemMap = rfiService.prepareBill("false");
	
		colors = new RGBColor();
		
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream(), wbSettings);
		
		wb = workbook;
		write(workbook, itemMap);
		
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=billExport.xls");
		response.setHeader("Cache-Control", "max-age=0");
		
		workbook.write();
		workbook.close();
		
		return null;
	}
	 
	 
	 public void write(WritableWorkbook workbook, LinkedHashMap itemMap) throws IOException, WriteException
	 {
		 Iterator itemMapIterator = itemMap.entrySet().iterator();
		 int counter = 0;
			while (itemMapIterator.hasNext())
			{
				Map.Entry<String, LinkedHashMap> itemEntry = (Map.Entry<String, LinkedHashMap>)itemMapIterator.next();
				String item = itemEntry.getKey();
				LinkedHashMap activityMap = itemEntry.getValue();
			 	{
			 		workbook.createSheet(item, counter);
			 		WritableSheet excelSheet = workbook.getSheet(counter);
			 		createLabel(excelSheet);
					createContent(excelSheet, activityMap);
				}
			 	
			 	++counter;
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
			addLabel(sheet, 0, 0, "IssueDate");
			addLabel(sheet, 1, 0, "Activity");
			addLabel(sheet, 2, 0, "Item Description");
			addLabel(sheet, 3, 0, "WI Remarks");
			addLabel(sheet, 4, 0, "Side");
			addLabel(sheet, 5, 0, "Layer");
			addLabel(sheet, 6, 0, "RFI No.");
			addLabel(sheet, 7, 0, "From Chainage");
			addLabel(sheet, 8, 0, "To Chainage");
			addLabel(sheet, 9, 0, "Quantity");
			addLabel(sheet, 10, 0, "Deductible Quantity");
			addLabel(sheet, 11, 0, "Payable Quantity");
			addLabel(sheet, 12, 0, "Rate");
			addLabel(sheet, 13, 0, "Amount");
			addLabel(sheet, 14, 0, "Remarks");
		}

		private void createContent(WritableSheet sheet, LinkedHashMap activityMap) throws WriteException,
				RowsExceededException {
			int rowNumber = 1;
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
						Iterator<RFIBill> rfiBillListIt = rfiBillList.iterator();
						while (rfiBillListIt.hasNext())
						{
							RFIBill rfiBill = rfiBillListIt.next();
							addDate(sheet,0,rowNumber,rfiBill.getIssueDate());
							addString(sheet,1,rowNumber,rfiBill.getBillActivity());
							addString(sheet,2,rowNumber,rfiBill.getItemDescription());
							addString(sheet,3,rowNumber,rfiBill.getWiRemarks());
							addString(sheet,4,rowNumber,rfiBill.getBillSide());
							addString(sheet,5,rowNumber,rfiBill.getBillLayer());
							addString(sheet,6,rowNumber,rfiBill.getRfiNumber());
							addNumber(sheet,7,rowNumber,rfiBill.getBillFrom());
							addNumber(sheet,8,rowNumber,rfiBill.getBillTo());
							addNumber(sheet,9,rowNumber,rfiBill.getQuantity());
							addNumber(sheet,10,rowNumber,rfiBill.getDeductQuantity());
							addNumber(sheet,11,rowNumber,rfiBill.getPayableQuantity());
							addNumber(sheet,12,rowNumber,rfiBill.getRate());
							addNumber(sheet,13,rowNumber,rfiBill.getAmount());
							addString(sheet,14,rowNumber,rfiBill.getRemarks());
							
							++rowNumber;
						}
					}
					for(int x=0;x<20;x++)
					{
					    CellView cell = sheet.getColumnView(x);
					    cell.setAutosize(true);
					    sheet.setColumnView(x, cell);
					}
				}
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
