package com.model.rfi;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "rows")
public class LayerExceptionsList {
	
	

	public LayerExceptionsList() {
		super();
	}

	public LayerExceptionsList(List<LayerException> expandedRFIList) {
		super();
		this.layerExceptionsList = expandedRFIList;
	}

	private List<LayerException> layerExceptionsList;

	@XmlElementWrapper(name = "rows")
	@XmlElement(name = "row")
	public List<LayerException> getLayerExceptionsList() {
		return layerExceptionsList;
	}

	public void setLayerExceptionsList(List<LayerException> layerExceptionsList) {
		this.layerExceptionsList = layerExceptionsList;
	}

}
