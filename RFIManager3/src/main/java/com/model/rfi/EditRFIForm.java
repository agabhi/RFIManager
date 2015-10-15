package com.model.rfi;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class EditRFIForm {
	
	private String rfiNumber;
	private Boolean breakIndicator;
	private String boqNumber;
	public String getBoqNumber() {
		return boqNumber;
	}
	public void setBoqNumber(String boqNumber) {
		this.boqNumber = boqNumber;
	}
	public Boolean getBreakIndicator() {
		return breakIndicator;
	}
	public void setBreakIndicator(Boolean breakIndicator) {
		this.breakIndicator = breakIndicator;
	}
	private String breakRfiString;
	public String getBreakRfiString() {
		return breakRfiString;
	}
	public void setBreakRfiString(String breakRfiString) {
		this.breakRfiString = breakRfiString;
	}
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
	private String billNumber;
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	private String remarks;
	private String wiRemarks;
	private Date issueDate;
	private Date inspectionDate;
	private String status;
	private String grade;
	
	private Integer approvalFrom1;
	private Integer approvalTo1;
	private String approvalSide1;
	private String approvalLayer1;
	private Date approvalDate1;
	private Double breadth1;
	private Double depth1;
	private Double area1;
	private Double quantity1;
	
	private Integer approvalFrom2;
	private Integer approvalTo2;
	private String approvalSide2;
	private String approvalLayer2;
	private Date approvalDate2;
	private Double breadth2;
	private Double depth2;
	private Double area2;
	private Double quantity2;
	
	private Integer approvalFrom3;
	private Integer approvalTo3;
	private String approvalSide3;
	private String approvalLayer3;
	private Date approvalDate3;
	private Double breadth3;
	private Double depth3;
	private Double area3;
	private Double quantity3;
	
	private Integer approvalFrom4;
	private Integer approvalTo4;
	private String approvalSide4;
	private String approvalLayer4;
	private Date approvalDate4;
	private Double breadth4;
	private Double depth4;
	private Double area4;
	private Double quantity4;
	
	private Integer approvalFrom5;
	private Integer approvalTo5;
	private String approvalSide5;
	private String approvalLayer5;
	private Date approvalDate5;
	private Double breadth5;
	private Double depth5;
	private Double area5;
	private Double quantity5;
	
	private Integer approvalFrom6;
	private Integer approvalTo6;
	private String approvalSide6;
	private String approvalLayer6;
	private Date approvalDate6;
	private Double breadth6;
	private Double depth6;
	private Double area6;
	private Double quantity6;
	
	private Integer approvalFrom7;
	private Integer approvalTo7;
	private String approvalSide7;
	private String approvalLayer7;
	private Date approvalDate7;
	private Double breadth7;
	private Double depth7;
	private Double area7;
	private Double quantity7;
	
	private Integer approvalFrom8;
	private Integer approvalTo8;
	private String approvalSide8;
	private String approvalLayer8;
	private Date approvalDate8;
	private Double breadth8;
	private Double depth8;
	private Double area8;
	private Double quantity8;
	
	public Date getApprovalDate1() {
		return approvalDate1;
	}
	public void setApprovalDate1(Date approvalDate1) {
		this.approvalDate1 = approvalDate1;
	}
	public Date getApprovalDate2() {
		return approvalDate2;
	}
	public void setApprovalDate2(Date approvalDate2) {
		this.approvalDate2 = approvalDate2;
	}
	public Date getApprovalDate3() {
		return approvalDate3;
	}
	public void setApprovalDate3(Date approvalDate3) {
		this.approvalDate3 = approvalDate3;
	}
	public Date getApprovalDate4() {
		return approvalDate4;
	}
	public void setApprovalDate4(Date approvalDate4) {
		this.approvalDate4 = approvalDate4;
	}
	public Date getApprovalDate5() {
		return approvalDate5;
	}
	public void setApprovalDate5(Date approvalDate5) {
		this.approvalDate5 = approvalDate5;
	}
	public Date getApprovalDate6() {
		return approvalDate6;
	}
	public void setApprovalDate6(Date approvalDate6) {
		this.approvalDate6 = approvalDate6;
	}
	public Date getApprovalDate7() {
		return approvalDate7;
	}
	public void setApprovalDate7(Date approvalDate7) {
		this.approvalDate7 = approvalDate7;
	}
	public Date getApprovalDate8() {
		return approvalDate8;
	}
	public void setApprovalDate8(Date approvalDate8) {
		this.approvalDate8 = approvalDate8;
	}
	private Integer approvalFrom9;
	private Integer approvalTo9;
	private String approvalSide9;
	private String approvalLayer9;
	private Date approvalDate9;
	private Double breadth9;
	private Double depth9;
	private Double area9;
	private Double quantity9;
	
	private Integer approvalFrom10;
	private Integer approvalTo10;
	private String approvalSide10;
	private String approvalLayer10;
	private Date approvalDate10;
	private Double breadth10;
	private Double depth10;
	private Double area10;
	private Double quantity10;
	
	public Double getBreadth1() {
		return breadth1;
	}
	public void setBreadth1(Double breadth1) {
		this.breadth1 = breadth1;
	}
	public Double getDepth1() {
		return depth1;
	}
	public void setDepth1(Double depth1) {
		this.depth1 = depth1;
	}
	public Double getArea1() {
		return area1;
	}
	public void setArea1(Double area1) {
		this.area1 = area1;
	}
	public Double getBreadth2() {
		return breadth2;
	}
	public void setBreadth2(Double breadth2) {
		this.breadth2 = breadth2;
	}
	public Double getDepth2() {
		return depth2;
	}
	public void setDepth2(Double depth2) {
		this.depth2 = depth2;
	}
	public Double getArea2() {
		return area2;
	}
	public void setArea2(Double area2) {
		this.area2 = area2;
	}
	public Double getBreadth3() {
		return breadth3;
	}
	public void setBreadth3(Double breadth3) {
		this.breadth3 = breadth3;
	}
	public Double getDepth3() {
		return depth3;
	}
	public void setDepth3(Double depth3) {
		this.depth3 = depth3;
	}
	public Double getArea3() {
		return area3;
	}
	public void setArea3(Double area3) {
		this.area3 = area3;
	}
	public Double getBreadth4() {
		return breadth4;
	}
	public void setBreadth4(Double breadth4) {
		this.breadth4 = breadth4;
	}
	public Double getDepth4() {
		return depth4;
	}
	public void setDepth4(Double depth4) {
		this.depth4 = depth4;
	}
	public Double getArea4() {
		return area4;
	}
	public void setArea4(Double area4) {
		this.area4 = area4;
	}
	public Double getBreadth5() {
		return breadth5;
	}
	public void setBreadth5(Double breadth5) {
		this.breadth5 = breadth5;
	}
	public Double getDepth5() {
		return depth5;
	}
	public void setDepth5(Double depth5) {
		this.depth5 = depth5;
	}
	public Double getArea5() {
		return area5;
	}
	public void setArea5(Double area5) {
		this.area5 = area5;
	}
	public Double getBreadth6() {
		return breadth6;
	}
	public void setBreadth6(Double breadth6) {
		this.breadth6 = breadth6;
	}
	public Double getDepth6() {
		return depth6;
	}
	public void setDepth6(Double depth6) {
		this.depth6 = depth6;
	}
	public Double getArea6() {
		return area6;
	}
	public void setArea6(Double area6) {
		this.area6 = area6;
	}
	public Double getBreadth7() {
		return breadth7;
	}
	public void setBreadth7(Double breadth7) {
		this.breadth7 = breadth7;
	}
	public Double getDepth7() {
		return depth7;
	}
	public void setDepth7(Double depth7) {
		this.depth7 = depth7;
	}
	public Double getArea7() {
		return area7;
	}
	public void setArea7(Double area7) {
		this.area7 = area7;
	}
	public Double getBreadth8() {
		return breadth8;
	}
	public void setBreadth8(Double breadth8) {
		this.breadth8 = breadth8;
	}
	public Double getDepth8() {
		return depth8;
	}
	public void setDepth8(Double depth8) {
		this.depth8 = depth8;
	}
	public Double getArea8() {
		return area8;
	}
	public void setArea8(Double area8) {
		this.area8 = area8;
	}
	public Double getBreadth9() {
		return breadth9;
	}
	public void setBreadth9(Double breadth9) {
		this.breadth9 = breadth9;
	}
	public Double getDepth9() {
		return depth9;
	}
	public void setDepth9(Double depth9) {
		this.depth9 = depth9;
	}
	public Double getArea9() {
		return area9;
	}
	public void setArea9(Double area9) {
		this.area9 = area9;
	}
	public Double getBreadth10() {
		return breadth10;
	}
	public void setBreadth10(Double breadth10) {
		this.breadth10 = breadth10;
	}
	public Double getDepth10() {
		return depth10;
	}
	public void setDepth10(Double depth10) {
		this.depth10 = depth10;
	}
	public Double getArea10() {
		return area10;
	}
	public void setArea10(Double area10) {
		this.area10 = area10;
	}
	public Double getQuantity1() {
		return quantity1;
	}
	public void setQuantity1(Double quantity1) {
		this.quantity1 = quantity1;
	}
	public Double getQuantity2() {
		return quantity2;
	}
	public void setQuantity2(Double quantity2) {
		this.quantity2 = quantity2;
	}
	public Double getQuantity3() {
		return quantity3;
	}
	public void setQuantity3(Double quantity3) {
		this.quantity3 = quantity3;
	}
	public Double getQuantity4() {
		return quantity4;
	}
	public void setQuantity4(Double quantity4) {
		this.quantity4 = quantity4;
	}
	public Double getQuantity5() {
		return quantity5;
	}
	public void setQuantity5(Double quantity5) {
		this.quantity5 = quantity5;
	}
	public Double getQuantity6() {
		return quantity6;
	}
	public void setQuantity6(Double quantity6) {
		this.quantity6 = quantity6;
	}
	public Double getQuantity7() {
		return quantity7;
	}
	public void setQuantity7(Double quantity7) {
		this.quantity7 = quantity7;
	}
	public Double getQuantity8() {
		return quantity8;
	}
	public void setQuantity8(Double quantity8) {
		this.quantity8 = quantity8;
	}
	public Double getQuantity9() {
		return quantity9;
	}
	public void setQuantity9(Double quantity9) {
		this.quantity9 = quantity9;
	}
	public Double getQuantity10() {
		return quantity10;
	}
	public void setQuantity10(Double quantity10) {
		this.quantity10 = quantity10;
	}
	private Integer billNumber1;
	private Integer billFrom1;
	private Integer billTo1;
	private String billSide1;
	private String billLayer1;
	
	private Integer billNumber2;
	private Integer billFrom2;
	private Integer billTo2;
	private String billSide2;
	private String billLayer2;
	
	private Integer billNumber3;
	private Integer billFrom3;
	private Integer billTo3;
	private String billSide3;
	private String billLayer3;
	
	private Integer billNumber4;
	private Integer billFrom4;
	private Integer billTo4;
	private String billSide4;
	private String billLayer4;
	
	private Integer billNumber5;
	private Integer billFrom5;
	private Integer billTo5;
	private String billSide5;
	private String billLayer5;
	
	private Integer billNumber6;
	private Integer billFrom6;
	private Integer billTo6;
	private String billSide6;
	private String billLayer6;
	
	private Integer billNumber7;
	private Integer billFrom7;
	private Integer billTo7;
	private String billSide7;
	private String billLayer7;
	
	private Integer billNumber8;
	private Integer billFrom8;
	private Integer billTo8;
	private String billSide8;
	private String billLayer8;
	
	private Integer billNumber9;
	private Integer billFrom9;
	private Integer billTo9;
	private String billSide9;
	private String billLayer9;
	
	private Integer billNumber10;
	private Integer billFrom10;
	private Integer billTo10;
	private String billSide10;
	private String billLayer10;
	
	private CommonsMultipartFile fileData;
	
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
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
	/**
	 * @return the status
	 */
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
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Integer getApprovalFrom1() {
		return approvalFrom1;
	}
	public void setApprovalFrom1(Integer approvalFrom1) {
		this.approvalFrom1 = approvalFrom1;
	}
	public Integer getApprovalTo1() {
		return approvalTo1;
	}
	public void setApprovalTo1(Integer approvalTo1) {
		this.approvalTo1 = approvalTo1;
	}
	public String getApprovalSide1() {
		return approvalSide1;
	}
	public void setApprovalSide1(String approvalSide1) {
		this.approvalSide1 = approvalSide1;
	}
	public String getApprovalLayer1() {
		return approvalLayer1;
	}
	public void setApprovalLayer1(String approvalLayer1) {
		this.approvalLayer1 = approvalLayer1;
	}
	public Integer getApprovalFrom2() {
		return approvalFrom2;
	}
	public void setApprovalFrom2(Integer approvalFrom2) {
		this.approvalFrom2 = approvalFrom2;
	}
	public Integer getApprovalTo2() {
		return approvalTo2;
	}
	public void setApprovalTo2(Integer approvalTo2) {
		this.approvalTo2 = approvalTo2;
	}
	public String getApprovalSide2() {
		return approvalSide2;
	}
	public void setApprovalSide2(String approvalSide2) {
		this.approvalSide2 = approvalSide2;
	}
	public String getApprovalLayer2() {
		return approvalLayer2;
	}
	public void setApprovalLayer2(String approvalLayer2) {
		this.approvalLayer2 = approvalLayer2;
	}
	public Integer getApprovalFrom3() {
		return approvalFrom3;
	}
	public void setApprovalFrom3(Integer approvalFrom3) {
		this.approvalFrom3 = approvalFrom3;
	}
	public Integer getApprovalTo3() {
		return approvalTo3;
	}
	public void setApprovalTo3(Integer approvalTo3) {
		this.approvalTo3 = approvalTo3;
	}
	public String getApprovalSide3() {
		return approvalSide3;
	}
	public void setApprovalSide3(String approvalSide3) {
		this.approvalSide3 = approvalSide3;
	}
	public String getApprovalLayer3() {
		return approvalLayer3;
	}
	public void setApprovalLayer3(String approvalLayer3) {
		this.approvalLayer3 = approvalLayer3;
	}
	public Integer getApprovalFrom4() {
		return approvalFrom4;
	}
	public void setApprovalFrom4(Integer approvalFrom4) {
		this.approvalFrom4 = approvalFrom4;
	}
	public Integer getApprovalTo4() {
		return approvalTo4;
	}
	public void setApprovalTo4(Integer approvalTo4) {
		this.approvalTo4 = approvalTo4;
	}
	public String getApprovalSide4() {
		return approvalSide4;
	}
	public void setApprovalSide4(String approvalSide4) {
		this.approvalSide4 = approvalSide4;
	}
	public String getApprovalLayer4() {
		return approvalLayer4;
	}
	public void setApprovalLayer4(String approvalLayer4) {
		this.approvalLayer4 = approvalLayer4;
	}
	public Integer getApprovalFrom5() {
		return approvalFrom5;
	}
	public void setApprovalFrom5(Integer approvalFrom5) {
		this.approvalFrom5 = approvalFrom5;
	}
	public Integer getApprovalTo5() {
		return approvalTo5;
	}
	public void setApprovalTo5(Integer approvalTo5) {
		this.approvalTo5 = approvalTo5;
	}
	public String getApprovalSide5() {
		return approvalSide5;
	}
	public void setApprovalSide5(String approvalSide5) {
		this.approvalSide5 = approvalSide5;
	}
	public String getApprovalLayer5() {
		return approvalLayer5;
	}
	public void setApprovalLayer5(String approvalLayer5) {
		this.approvalLayer5 = approvalLayer5;
	}
	public Integer getApprovalFrom6() {
		return approvalFrom6;
	}
	public void setApprovalFrom6(Integer approvalFrom6) {
		this.approvalFrom6 = approvalFrom6;
	}
	public Integer getApprovalTo6() {
		return approvalTo6;
	}
	public void setApprovalTo6(Integer approvalTo6) {
		this.approvalTo6 = approvalTo6;
	}
	public String getApprovalSide6() {
		return approvalSide6;
	}
	public void setApprovalSide6(String approvalSide6) {
		this.approvalSide6 = approvalSide6;
	}
	public String getApprovalLayer6() {
		return approvalLayer6;
	}
	public void setApprovalLayer6(String approvalLayer6) {
		this.approvalLayer6 = approvalLayer6;
	}
	public Integer getApprovalFrom7() {
		return approvalFrom7;
	}
	public void setApprovalFrom7(Integer approvalFrom7) {
		this.approvalFrom7 = approvalFrom7;
	}
	public Integer getApprovalTo7() {
		return approvalTo7;
	}
	public void setApprovalTo7(Integer approvalTo7) {
		this.approvalTo7 = approvalTo7;
	}
	public String getApprovalSide7() {
		return approvalSide7;
	}
	public void setApprovalSide7(String approvalSide7) {
		this.approvalSide7 = approvalSide7;
	}
	public String getApprovalLayer7() {
		return approvalLayer7;
	}
	public void setApprovalLayer7(String approvalLayer7) {
		this.approvalLayer7 = approvalLayer7;
	}
	public Integer getApprovalFrom8() {
		return approvalFrom8;
	}
	public void setApprovalFrom8(Integer approvalFrom8) {
		this.approvalFrom8 = approvalFrom8;
	}
	public Integer getApprovalTo8() {
		return approvalTo8;
	}
	public void setApprovalTo8(Integer approvalTo8) {
		this.approvalTo8 = approvalTo8;
	}
	public String getApprovalSide8() {
		return approvalSide8;
	}
	public void setApprovalSide8(String approvalSide8) {
		this.approvalSide8 = approvalSide8;
	}
	public String getApprovalLayer8() {
		return approvalLayer8;
	}
	public void setApprovalLayer8(String approvalLayer8) {
		this.approvalLayer8 = approvalLayer8;
	}
	public Integer getApprovalFrom9() {
		return approvalFrom9;
	}
	public void setApprovalFrom9(Integer approvalFrom9) {
		this.approvalFrom9 = approvalFrom9;
	}
	public Integer getApprovalTo9() {
		return approvalTo9;
	}
	public void setApprovalTo9(Integer approvalTo9) {
		this.approvalTo9 = approvalTo9;
	}
	public String getApprovalSide9() {
		return approvalSide9;
	}
	public void setApprovalSide9(String approvalSide9) {
		this.approvalSide9 = approvalSide9;
	}
	public String getApprovalLayer9() {
		return approvalLayer9;
	}
	public void setApprovalLayer9(String approvalLayer9) {
		this.approvalLayer9 = approvalLayer9;
	}
	public Integer getApprovalFrom10() {
		return approvalFrom10;
	}
	public void setApprovalFrom10(Integer approvalFrom10) {
		this.approvalFrom10 = approvalFrom10;
	}
	public Integer getApprovalTo10() {
		return approvalTo10;
	}
	public void setApprovalTo10(Integer approvalTo10) {
		this.approvalTo10 = approvalTo10;
	}
	public String getApprovalSide10() {
		return approvalSide10;
	}
	public void setApprovalSide10(String approvalSide10) {
		this.approvalSide10 = approvalSide10;
	}
	public String getApprovalLayer10() {
		return approvalLayer10;
	}
	public void setApprovalLayer10(String approvalLayer10) {
		this.approvalLayer10 = approvalLayer10;
	}
	
	
	public Integer getBillNumber1() {
		return billNumber1;
	}
	public void setBillNumber1(Integer billNumber1) {
		this.billNumber1 = billNumber1;
	}
	public Integer getBillFrom1() {
		return billFrom1;
	}
	public void setBillFrom1(Integer billFrom1) {
		this.billFrom1 = billFrom1;
	}
	public Integer getBillTo1() {
		return billTo1;
	}
	public void setBillTo1(Integer billTo1) {
		this.billTo1 = billTo1;
	}
	public String getBillSide1() {
		return billSide1;
	}
	public void setBillSide1(String billSide1) {
		this.billSide1 = billSide1;
	}
	public String getBillLayer1() {
		return billLayer1;
	}
	public void setBillLayer1(String billLayer1) {
		this.billLayer1 = billLayer1;
	}
	public Integer getBillNumber2() {
		return billNumber2;
	}
	public void setBillNumber2(Integer billNumber2) {
		this.billNumber2 = billNumber2;
	}
	public Integer getBillFrom2() {
		return billFrom2;
	}
	public void setBillFrom2(Integer billFrom2) {
		this.billFrom2 = billFrom2;
	}
	public Integer getBillTo2() {
		return billTo2;
	}
	public void setBillTo2(Integer billTo2) {
		this.billTo2 = billTo2;
	}
	public String getBillSide2() {
		return billSide2;
	}
	public void setBillSide2(String billSide2) {
		this.billSide2 = billSide2;
	}
	public String getBillLayer2() {
		return billLayer2;
	}
	public void setBillLayer2(String billLayer2) {
		this.billLayer2 = billLayer2;
	}
	public Integer getBillNumber3() {
		return billNumber3;
	}
	public void setBillNumber3(Integer billNumber3) {
		this.billNumber3 = billNumber3;
	}
	public Integer getBillFrom3() {
		return billFrom3;
	}
	public void setBillFrom3(Integer billFrom3) {
		this.billFrom3 = billFrom3;
	}
	public Integer getBillTo3() {
		return billTo3;
	}
	public void setBillTo3(Integer billTo3) {
		this.billTo3 = billTo3;
	}
	public String getBillSide3() {
		return billSide3;
	}
	public void setBillSide3(String billSide3) {
		this.billSide3 = billSide3;
	}
	public String getBillLayer3() {
		return billLayer3;
	}
	public void setBillLayer3(String billLayer3) {
		this.billLayer3 = billLayer3;
	}
	public Integer getBillNumber4() {
		return billNumber4;
	}
	public void setBillNumber4(Integer billNumber4) {
		this.billNumber4 = billNumber4;
	}
	public Integer getBillFrom4() {
		return billFrom4;
	}
	public void setBillFrom4(Integer billFrom4) {
		this.billFrom4 = billFrom4;
	}
	public Integer getBillTo4() {
		return billTo4;
	}
	public void setBillTo4(Integer billTo4) {
		this.billTo4 = billTo4;
	}
	public String getBillSide4() {
		return billSide4;
	}
	public void setBillSide4(String billSide4) {
		this.billSide4 = billSide4;
	}
	public String getBillLayer4() {
		return billLayer4;
	}
	public void setBillLayer4(String billLayer4) {
		this.billLayer4 = billLayer4;
	}
	public Integer getBillNumber5() {
		return billNumber5;
	}
	public void setBillNumber5(Integer billNumber5) {
		this.billNumber5 = billNumber5;
	}
	public Integer getBillFrom5() {
		return billFrom5;
	}
	public void setBillFrom5(Integer billFrom5) {
		this.billFrom5 = billFrom5;
	}
	public Integer getBillTo5() {
		return billTo5;
	}
	public void setBillTo5(Integer billTo5) {
		this.billTo5 = billTo5;
	}
	public String getBillSide5() {
		return billSide5;
	}
	public void setBillSide5(String billSide5) {
		this.billSide5 = billSide5;
	}
	public String getBillLayer5() {
		return billLayer5;
	}
	public void setBillLayer5(String billLayer5) {
		this.billLayer5 = billLayer5;
	}
	public Integer getBillNumber6() {
		return billNumber6;
	}
	public void setBillNumber6(Integer billNumber6) {
		this.billNumber6 = billNumber6;
	}
	public Integer getBillFrom6() {
		return billFrom6;
	}
	public void setBillFrom6(Integer billFrom6) {
		this.billFrom6 = billFrom6;
	}
	public Integer getBillTo6() {
		return billTo6;
	}
	public void setBillTo6(Integer billTo6) {
		this.billTo6 = billTo6;
	}
	public String getBillSide6() {
		return billSide6;
	}
	public void setBillSide6(String billSide6) {
		this.billSide6 = billSide6;
	}
	public String getBillLayer6() {
		return billLayer6;
	}
	public void setBillLayer6(String billLayer6) {
		this.billLayer6 = billLayer6;
	}
	public Integer getBillNumber7() {
		return billNumber7;
	}
	public void setBillNumber7(Integer billNumber7) {
		this.billNumber7 = billNumber7;
	}
	public Integer getBillFrom7() {
		return billFrom7;
	}
	public void setBillFrom7(Integer billFrom7) {
		this.billFrom7 = billFrom7;
	}
	public Integer getBillTo7() {
		return billTo7;
	}
	public void setBillTo7(Integer billTo7) {
		this.billTo7 = billTo7;
	}
	public String getBillSide7() {
		return billSide7;
	}
	public void setBillSide7(String billSide7) {
		this.billSide7 = billSide7;
	}
	public String getBillLayer7() {
		return billLayer7;
	}
	public void setBillLayer7(String billLayer7) {
		this.billLayer7 = billLayer7;
	}
	public Integer getBillNumber8() {
		return billNumber8;
	}
	public void setBillNumber8(Integer billNumber8) {
		this.billNumber8 = billNumber8;
	}
	public Integer getBillFrom8() {
		return billFrom8;
	}
	public void setBillFrom8(Integer billFrom8) {
		this.billFrom8 = billFrom8;
	}
	public Integer getBillTo8() {
		return billTo8;
	}
	public void setBillTo8(Integer billTo8) {
		this.billTo8 = billTo8;
	}
	public String getBillSide8() {
		return billSide8;
	}
	public void setBillSide8(String billSide8) {
		this.billSide8 = billSide8;
	}
	public String getBillLayer8() {
		return billLayer8;
	}
	public void setBillLayer8(String billLayer8) {
		this.billLayer8 = billLayer8;
	}
	public Integer getBillNumber9() {
		return billNumber9;
	}
	public void setBillNumber9(Integer billNumber9) {
		this.billNumber9 = billNumber9;
	}
	public Integer getBillFrom9() {
		return billFrom9;
	}
	public void setBillFrom9(Integer billFrom9) {
		this.billFrom9 = billFrom9;
	}
	public Integer getBillTo9() {
		return billTo9;
	}
	public void setBillTo9(Integer billTo9) {
		this.billTo9 = billTo9;
	}
	public String getBillSide9() {
		return billSide9;
	}
	public void setBillSide9(String billSide9) {
		this.billSide9 = billSide9;
	}
	public String getBillLayer9() {
		return billLayer9;
	}
	public void setBillLayer9(String billLayer9) {
		this.billLayer9 = billLayer9;
	}
	public Integer getBillNumber10() {
		return billNumber10;
	}
	public void setBillNumber10(Integer billNumber10) {
		this.billNumber10 = billNumber10;
	}
	public Integer getBillFrom10() {
		return billFrom10;
	}
	public void setBillFrom10(Integer billFrom10) {
		this.billFrom10 = billFrom10;
	}
	public Integer getBillTo10() {
		return billTo10;
	}
	public void setBillTo10(Integer billTo10) {
		this.billTo10 = billTo10;
	}
	public String getBillSide10() {
		return billSide10;
	}
	public void setBillSide10(String billSide10) {
		this.billSide10 = billSide10;
	}
	public String getBillLayer10() {
		return billLayer10;
	}
	public void setBillLayer10(String billLayer10) {
		this.billLayer10 = billLayer10;
	}
	/**
	 * @return the approvalDate10
	 */
	public Date getApprovalDate10() {
		return approvalDate10;
	}
	/**
	 * @param approvalDate10 the approvalDate10 to set
	 */
	public void setApprovalDate10(Date approvalDate10) {
		this.approvalDate10 = approvalDate10;
	}
	/**
	 * @return the approvalDate9
	 */
	public Date getApprovalDate9() {
		return approvalDate9;
	}
	/**
	 * @param approvalDate9 the approvalDate9 to set
	 */
	public void setApprovalDate9(Date approvalDate9) {
		this.approvalDate9 = approvalDate9;
	}
}
