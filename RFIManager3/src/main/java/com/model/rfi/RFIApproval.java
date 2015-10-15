package com.model.rfi;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class RFIApproval {

	private Integer id;
	private Integer rfiId;
	private String breakRfiString;
	private Integer approvedFrom;
	private Integer approvedTo;
	private String approvedSide;
	private String approvedLayer;
	private Date approvalDate;
	private Double breadth;
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

	private Double depth;
	private Double area;
	private Double quantity;
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@XmlTransient
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Override
	public boolean equals(Object obj) {
		if(id != null) {
			if(id.equals(obj)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if( id != null) {
		return id;
		}
		return 0;
	}
	/**
	 * @return the rfiId
	 */
	@XmlTransient
	public Integer getRfiId() {
		return rfiId;
	}
	/**
	 * @param rfiId the rfiId to set
	 */
	public void setRfiId(Integer rfiId) {
		this.rfiId = rfiId;
	}
	/**
	 * @return the approvedFrom
	 */
	@XmlElement
	public Integer getApprovedFrom() {
		return approvedFrom;
	}
	/**
	 * @param approvedFrom the approvedFrom to set
	 */
	public void setApprovedFrom(Integer approvedFrom) {
		this.approvedFrom = approvedFrom;
	}
	/**
	 * @return the approvedTo
	 */
	@XmlElement
	public Integer getApprovedTo() {
		return approvedTo;
	}
	/**
	 * @param approvedTo the approvedTo to set
	 */
	public void setApprovedTo(Integer approvedTo) {
		this.approvedTo = approvedTo;
	}
	/**
	 * @return the approvedSide
	 */
	@XmlElement
	public String getApprovedSide() {
		return approvedSide;
	}
	/**
	 * @param approvedSide the approvedSide to set
	 */
	public void setApprovedSide(String approvedSide) {
		this.approvedSide = approvedSide;
	}
	/**
	 * @return the approvedLayer
	 */
	@XmlElement
	public String getApprovedLayer() {
		return approvedLayer;
	}
	/**
	 * @param approvedLayer the approvedLayer to set
	 */
	public void setApprovedLayer(String approvedLayer) {
		this.approvedLayer = approvedLayer;
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
	 * @return the issueDate
	 */
	@XmlElement
	public String getApprovalDateAsText() {
		if(approvalDate != null) {
			return RFIServiceImpl.getDateAsSimpleText(approvalDate);
		}
		return null;
	}
}
