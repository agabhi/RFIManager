package com.model.rfi;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "row")
public class RFI implements Serializable{

	private static final long serialVersionUID = 5949671625794110840L;
	private Integer id;
	private String rfiNumber;
	private String breakRfiString;
	private String rfiCode;
	private String boqNumber;
	
	@XmlElement
	public String getBoqNumber() {
		return boqNumber;
	}

	public void setBoqNumber(String boqNumber) {
		this.boqNumber = boqNumber;
	}

	private String itemDescription;
	private Integer fromChainage;
	private Integer toChainage;
	private String side;
	private String activity;
	
	@XmlElement
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
	private String createdByUserName;
	private String lastEditedByUserName;
	private String status;
	private String grade;
	private String billNumber;
	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	private Set<RFIApproval> rfiApprovals;
	private Set<RFIBill> rfiBills;
	
	public void addApproval(RFIApproval rfiApproval)
	{
		if(rfiApprovals == null)
		{
			rfiApprovals = new HashSet<RFIApproval>();
		}
		
		rfiApprovals.add(rfiApproval);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rfiNumber == null) ? 0 : rfiNumber.toLowerCase().trim().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RFI other = (RFI) obj;
		if (rfiNumber == null) {
			if (other.rfiNumber != null)
				return false;
		} else if (!rfiNumber.trim().equalsIgnoreCase(other.rfiNumber.trim()))
			return false;
		return true;
	}
	
	@XmlElement
	public String getRfiNumber() {
		return rfiNumber;
	}

	public void setRfiNumber(String rfiNumber) {
		this.rfiNumber = rfiNumber;
	}

	/**
	 * @return the breakRfiString
	 */
	@XmlTransient
	public String getBreakRfiString() {
		return breakRfiString;
	}

	/**
	 * @param breakRfiString the breakRfiString to set
	 */
	public void setBreakRfiString(String breakRfiString) {
		this.breakRfiString = breakRfiString;
	}

	/**
	 * @return the rfiCode
	 */
	@XmlElement
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
	 * @return the fromChainage
	 */
	@XmlElement
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
	@XmlElement
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
	@XmlElement
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
	@XmlElement
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
	@XmlElement
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
	@XmlElement
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
	@XmlTransient
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
	 * @return the issueDate
	 */
	@XmlElement
	public String getIssueDateAsText() {
		return RFIServiceImpl.getDateAsSimpleText(issueDate);
	}
	
	/**
	 * @return the inspectionDate
	 */
	@XmlTransient
	public Date getInspectionDate() {
		return inspectionDate;
	}

	/**
	 * @param inspectionDate the inspectionDate to set
	 */
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
	/**
	 * @return the inspectionDate
	 */
	@XmlElement
	public String getInspectionDateAsText() {
		return RFIServiceImpl.getInspectionDateAsText(inspectionDate);
	}

	/**
	 * @return the createdByUserName
	 */
	@XmlElement
	public String getCreatedByUserName() {
		return createdByUserName;
	}

	/**
	 * @param createdByUserName the createdByUserName to set
	 */
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}

	/**
	 * @return the lastEditedByUserName
	 */
	@XmlElement
	public String getLastEditedByUserName() {
		return lastEditedByUserName;
	}

	/**
	 * @param lastEditedByUserName the lastEditedByUserName to set
	 */
	public void setLastEditedByUserName(String lastEditedByUserName) {
		this.lastEditedByUserName = lastEditedByUserName;
	}

	/**
	 * @return the status
	 */
	@XmlElement
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the grade
	 */
	@XmlElement
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the rfiApprovals
	 */
	@XmlElementWrapper(name = "approvals")
	@XmlElement(name = "approval")
	public Set<RFIApproval> getRfiApprovals() {
		return rfiApprovals;
	}

	/**
	 * @param rfiApprovals the rfiApprovals to set
	 */
	public void setRfiApprovals(Set<RFIApproval> rfiApprovals) {
		this.rfiApprovals = rfiApprovals;
	}

	/**
	 * @return the itemDescription
	 */
	@XmlElement
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
	 * @return the id
	 */
	@XmlTransient
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the rfiBills
	 */
	@XmlTransient
	public Set<RFIBill> getRfiBills() {
		return rfiBills;
	}

	/**
	 * @param rfiBills the rfiBills to set
	 */
	public void setRfiBills(Set<RFIBill> rfiBills) {
		this.rfiBills = rfiBills;
	}
}
