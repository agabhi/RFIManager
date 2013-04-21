package com.model.rfi;

import java.util.Date;

public class Bill {
	
	private String billNumber;
	private Date billFromDate;
	private Date billToDate;
	private Date billDate;
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public Date getBillFromDate() {
		return billFromDate;
	}
	public void setBillFromDate(Date billFromDate) {
		this.billFromDate = billFromDate;
	}
	public Date getBillToDate() {
		return billToDate;
	}
	public void setBillToDate(Date billToDate) {
		this.billToDate = billToDate;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	public String getBillDateAsText() {
		return RFIServiceImpl.getDateAsSimpleText(billDate);
	}
	
	public String getBillFromDateAsText() {
		return RFIServiceImpl.getDateAsSimpleText(billFromDate);
	}
	
	public String getBillToDateAsText() {
		return RFIServiceImpl.getDateAsSimpleText(billToDate);
	}
	
	


}
