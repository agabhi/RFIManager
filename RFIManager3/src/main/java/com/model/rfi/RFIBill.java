package com.model.rfi;

import java.util.Date;

public class RFIBill {
	
	private Long rfiBillId;
	
	private Date issueDate;
	
	private String rfiCalculationString;
	private String deductionString;
	public String getDeductionString() {
		return deductionString;
	}
	public void setDeductionString(String deductionString) {
		this.deductionString = deductionString;
	}
	public String getRfiCalculationString() {
		return rfiCalculationString;
	}
	public void setRfiCalculationString(String rfiCalculationString) {
		this.rfiCalculationString = rfiCalculationString;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	public String getIssueDateAsText() {
		return RFIServiceImpl.getDateAsSimpleText(issueDate);
	}
	
	public Long getRfiBillId() {
		return rfiBillId;
	}
	public void setRfiBillId(Long rfiBillId) {
		this.rfiBillId = rfiBillId;
	}
	private String rfiNumber;
	private String billActivity;
	private String remarks;
	private String wiRemarks;
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBillActivity() {
		return billActivity;
	}
	
	
	public String getWiRemarks() {
		return wiRemarks;
	}
	
	public void setWiRemarks(String wiRemarks) {
		this.wiRemarks = wiRemarks;
	}
	
	public void setBillActivity(String billActivity) {
		this.billActivity = billActivity;
	}
	private Integer billFrom;
	private Integer billTo;
	private String billSide;
	private String billLayer;
	private Double quantity;
	private Double deductQuantity;
	private Double payableQuantity;
	public Double getPayableQuantity() {
		return payableQuantity;
	}
	public void setPayableQuantity(Double payableQuantity) {
		this.payableQuantity = payableQuantity;
	}
	public Double getDeductQuantity() {
		return deductQuantity;
	}
	public void setDeductQuantity(Double deductQuantity) {
		this.deductQuantity = deductQuantity;
	}
	private Double amount;
	private Double rate;
	private String billNumber;
	
	private String itemDescription;
	
	
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getRfiNumber() {
		return rfiNumber;
	}
	public void setRfiNumber(String rfiNumber) {
		this.rfiNumber = rfiNumber;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the billFrom
	 */
	public Integer getBillFrom() {
		return billFrom;
	}
	/**
	 * @param billFrom the billFrom to set
	 */
	public void setBillFrom(Integer billFrom) {
		this.billFrom = billFrom;
	}
	/**
	 * @return the billTo
	 */
	public Integer getBillTo() {
		return billTo;
	}
	/**
	 * @param billTo the billTo to set
	 */
	public void setBillTo(Integer billTo) {
		this.billTo = billTo;
	}
	/**
	 * @return the billSide
	 */
	public String getBillSide() {
		return billSide;
	}
	/**
	 * @param billSide the billSide to set
	 */
	public void setBillSide(String billSide) {
		this.billSide = billSide;
	}
	/**
	 * @return the billLayer
	 */
	public String getBillLayer() {
		return billLayer;
	}
	/**
	 * @param billLayer the billLayer to set
	 */
	public void setBillLayer(String billLayer) {
		this.billLayer = billLayer;
	}

}
