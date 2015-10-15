package com.model.rfi;

import java.util.Date;

public class AddRFIForm {
	
	private String rfiNumber;
	private String rfiCode;
	private String itemDescription;
	private Integer fromChainage;
	private Integer toChainage;
	private String side;
	private String activity;
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	private String layer;
	private String remarks;
	private String wiRemarks;
	private Date issueDate;
	private Date inspectionDate;
	private String boqNumber;
	public String getBoqNumber() {
		return boqNumber;
	}
	public void setBoqNumber(String boqNumber) {
		this.boqNumber = boqNumber;
	}
	/**
	 * @return the rfiNumber
	 */
	public String getRfiNumber() {
		return rfiNumber;
	}
	/**
	 * @param rfiNumber the rfiNumber to set
	 */
	public void setRfiNumber(String rfiNumber) {
		this.rfiNumber = rfiNumber;
	}
	/**
	 * @return the rfiCode
	 */
	public String getRfiCode() {
		return rfiCode;
	}
	/**
	 * @param rfiCode the rfiCode to set
	 */
	public void setRfiCode(String rfiCode) {
		this.rfiCode = rfiCode;
	}
	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	/**
	 * @return the fromChainage
	 */
	public Integer getFromChainage() {
		return fromChainage;
	}
	/**
	 * @param fromChainage the fromChainage to set
	 */
	public void setFromChainage(Integer fromChainage) {
		this.fromChainage = fromChainage;
	}
	/**
	 * @return the toChainage
	 */
	public Integer getToChainage() {
		return toChainage;
	}
	/**
	 * @param toChainage the toChainage to set
	 */
	public void setToChainage(Integer toChainage) {
		this.toChainage = toChainage;
	}
	/**
	 * @return the side
	 */
	public String getSide() {
		return side;
	}
	/**
	 * @param side the side to set
	 */
	public void setSide(String side) {
		this.side = side;
	}
	/**
	 * @return the layer
	 */
	public String getLayer() {
		return layer;
	}
	/**
	 * @param layer the layer to set
	 */
	public void setLayer(String layer) {
		this.layer = layer;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * @return the remarks
	 */
	public String getWiRemarks() {
		return wiRemarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setWiRemarks(String wiRemarks) {
		this.wiRemarks = wiRemarks;
	}
	
	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}
	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	/**
	 * @return the inspectionDate
	 */
	public Date getInspectionDate() {
		return inspectionDate;
	}
	/**
	 * @param inspectionDate the inspectionDate to set
	 */
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

}
