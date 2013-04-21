package com.model.rfi;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "rows")
public class ExpandedRFIList {
	
	

	public ExpandedRFIList() {
		super();
	}

	public ExpandedRFIList(List<ExpandedRFI> expandedRFIList) {
		super();
		this.expandedRFIList = expandedRFIList;
	}

	private List<ExpandedRFI> expandedRFIList;

	@XmlElementWrapper(name = "rows")
	@XmlElement(name = "row")
	public List<ExpandedRFI> getExpandedRFIList() {
		return expandedRFIList;
	}

	public void setExpandedRFIList(List<ExpandedRFI> expandedRFIList) {
		this.expandedRFIList = expandedRFIList;
	}

}
