package com.model.rfi;

public class TopLayerTableEntry {
	
	private Integer fromChainage;
	private Integer toChainage;
	private String itemDescription;
	
	private Integer beneathTopLayer;

	public Integer getFromChainage() {
		return fromChainage;
	}

	public void setFromChainage(Integer fromChainage) {
		this.fromChainage = fromChainage;
	}

	public Integer getToChainage() {
		return toChainage;
	}

	public void setToChainage(Integer toChainage) {
		this.toChainage = toChainage;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getBeneathTopLayer() {
		return beneathTopLayer;
	}

	public void setBeneathTopLayer(Integer beneathTopLayer) {
		this.beneathTopLayer = beneathTopLayer;
	}

}
