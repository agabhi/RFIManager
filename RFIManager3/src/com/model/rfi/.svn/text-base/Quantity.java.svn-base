package com.model.rfi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "row")
@XmlType(propOrder = { "quantityId","activity","itemDescription", "fromChainage", "toChainage", "side", "layer", "quantity"})
public class Quantity {
	
	private Long quantityId;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = prime * result + ((layer == null) ? 0 : layer.hashCode());
		result = prime * result + ((side == null) ? 0 : side.hashCode());
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
		Quantity other = (Quantity) obj;
		
		if (activity == null || other.activity == null) {
			return false;
		} else if (!activity.equals(other.activity))
			return false;
		
		
		if (itemDescription == null || other.itemDescription == null) {
			return false;
		} else if (!itemDescription.equals(other.itemDescription))
			return false;
		
		if (side == null || other.side == null) {
			return false;
		} else if (!side.equals(other.side))
			return false;
		
		if (layer == null || other.layer == null) {
			return false;
		} else if (!layer.equals(other.layer))
			return false;
		
		if (fromChainage == null || other.fromChainage == null) {
			return false;
		} 
		else if (toChainage == null || other.toChainage == null) {
			return false;
		} 
		else if ((other.fromChainage >= fromChainage && other.fromChainage < toChainage) || (fromChainage >= other.fromChainage && fromChainage < other.toChainage)) 
			return true;
		else if ((other.toChainage > fromChainage && other.toChainage <= toChainage) || (toChainage > other.fromChainage && toChainage <= other.toChainage)) 
			return true;
		
		return false;
	}
	private Integer fromChainage;
	private Integer toChainage;
	private Double quantity;
	private String itemDescription;
	private String layer;
	private String side;
	private String activity;
	
	@XmlElement(name = "cell", nillable=true)
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	@XmlElement(name = "cell", nillable=true)
	public Long getQuantityId() {
		return quantityId;
	}
	public void setQuantityId(Long quantityId) {
		this.quantityId = quantityId;
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
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	@XmlElement(name = "cell", nillable=true)
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	@XmlElement(name = "cell", nillable=true)
	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
	
	@XmlElement(name = "cell", nillable=true)
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}


}
