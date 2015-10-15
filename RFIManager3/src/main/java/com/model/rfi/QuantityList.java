package com.model.rfi;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "rows")
public class QuantityList {
	
	

	public QuantityList() {
		super();
	}

	public QuantityList(List<Quantity> expandedRFIList) {
		super();
		this.quantityList = expandedRFIList;
	}

	private List<Quantity> quantityList;

	@XmlElementWrapper(name = "rows")
	@XmlElement(name = "row")
	public List<Quantity> getQuantityList() {
		return quantityList;
	}

	public void setQuantityList(List<Quantity> quantityList) {
		this.quantityList = quantityList;
	}

}
