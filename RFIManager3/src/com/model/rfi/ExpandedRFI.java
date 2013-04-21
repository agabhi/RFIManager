package com.model.rfi;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "row")
@XmlType(propOrder = { "rfiCode", "rfiNumber", "issueDateAsText", "boqNumber", "activity", "itemDescription", "wiRemarks", "fromChainage", "toChainage", "side", "layer", "status", "grade", "inspectionDateAsText", "createdByUserName", "lastEditedByUserName", "approvedFrom", "approvedTo", "approvedSide", "approvedLayer", "approvalDateAsText", "billNumber", "breadth", "depth", "area", "quantity", "remarks"})
public class ExpandedRFI implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 5568545155214724909L;
	private Integer rfi_id;
	private Integer approval_id;
	private String rfiNumber;
	private String rfiCode;
	private String itemDescription;
	private Integer fromChainage;
	private Integer toChainage;
	private String side;
	private String activity;
	
	@XmlElement(name = "cell", nillable=true)
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
	
	
	@XmlElement(name = "cell", nillable=true)
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}



	private String grade;
	private String billNumber;
	
	private Integer approvedFrom;
	private Integer approvedTo;
	private String approvedSide;
	private String approvedLayer;
	private Date approvalDate;
	
	private Double breadth;
	private Double depth;
	private Double area;
	public Double getBreadth() {
		return breadth;
	}

	public void setBreadth(Double breadth) {
		this.breadth = breadth;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}



	private Double quantity;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((approval_id == null) ? 0 : approval_id.hashCode());
		result = prime * result + ((rfi_id == null) ? 0 : rfi_id.hashCode());
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
		ExpandedRFI other = (ExpandedRFI) obj;
		if (approval_id == null) {
			if (other.approval_id != null)
				return false;
		} else if (!approval_id.equals(other.approval_id))
			return false;
		if (rfi_id == null) {
			if (other.rfi_id != null)
				return false;
		} else if (!rfi_id.equals(other.rfi_id))
			return false;
		return true;
	}

	@XmlTransient
	public Integer getRfi_id() {
		return rfi_id;
	}

	public void setRfi_id(Integer rfi_id) {
		this.rfi_id = rfi_id;
	}

	@XmlTransient
	public Integer getApproval_id() {
		return approval_id;
	}

	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}

	
	
	private String boqNumber;
	
	@XmlElement(name = "cell", nillable=true)
	public String getRfiNumber() {
		return rfiNumber;
	}

	public void setRfiNumber(String rfiNumber) {
		this.rfiNumber = rfiNumber;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getRfiCode() {
		return rfiCode;
	}

	public void setRfiCode(String rfiCode) {
		this.rfiCode = rfiCode;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@XmlElement(name = "cell", nillable=true)
	public Integer getFromChainage() {
		return fromChainage;
	}

	public void setFromChainage(Integer fromChainage) {
		this.fromChainage = fromChainage;
	}

	@XmlElement(name = "cell", nillable=true)
	public Integer getToChainage() {
		return toChainage;
	}

	public void setToChainage(Integer toChainage) {
		this.toChainage = toChainage;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@XmlElement(name = "cell", nillable=true)
	public String getWiRemarks() {
		return wiRemarks;
	}

	public void setWiRemarks(String wiRemarks) {
		this.wiRemarks = wiRemarks;
	}

	@XmlTransient
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@XmlTransient
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getCreatedByUserName() {
		return createdByUserName;
	}

	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getLastEditedByUserName() {
		return lastEditedByUserName;
	}

	public void setLastEditedByUserName(String lastEditedByUserName) {
		this.lastEditedByUserName = lastEditedByUserName;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	@XmlElement(name = "cell", nillable=true)
	public Integer getApprovedFrom() {
		return approvedFrom;
	}

	public void setApprovedFrom(Integer approvedFrom) {
		this.approvedFrom = approvedFrom;
	}

	@XmlElement(name = "cell", nillable=true)
	public Integer getApprovedTo() {
		return approvedTo;
	}

	public void setApprovedTo(Integer approvedTo) {
		this.approvedTo = approvedTo;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getApprovedSide() {
		return approvedSide;
	}

	public void setApprovedSide(String approvedSide) {
		this.approvedSide = approvedSide;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getApprovedLayer() {
		return approvedLayer;
	}

	public void setApprovedLayer(String approvedLayer) {
		this.approvedLayer = approvedLayer;
	}

	@XmlTransient
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@XmlElement(name = "cell", nillable=true)
	public String getBoqNumber() {
		return boqNumber;
	}

	public void setBoqNumber(String boqNumber) {
		this.boqNumber = boqNumber;
	}
	
	/**
	 * @return the issueDate
	 */
	@XmlElement(name = "cell", nillable=true)
	public String getIssueDateAsText() {
		return RFIServiceImpl.getDateAsSimpleText(issueDate);
	}
	
	/**
	 * @return the inspectionDate
	 */
	@XmlElement(name = "cell", nillable=true)
	public String getInspectionDateAsText() {
		return RFIServiceImpl.getInspectionDateAsText(inspectionDate);
	}
	
	/**
	 * @return the issueDate
	 */
	@XmlElement(name = "cell", nillable=true)
	public String getApprovalDateAsText() {
		if(approvalDate != null) {
			return RFIServiceImpl.getDateAsSimpleText(approvalDate);
		}
		return null;
	}
	
	public ExpandedRFI clone() throws CloneNotSupportedException
	{
		return (ExpandedRFI) super.clone();
	}


}
