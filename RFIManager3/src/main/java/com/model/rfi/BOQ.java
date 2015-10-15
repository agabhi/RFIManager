package com.model.rfi;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "boq")
public class BOQ {
	
	private String boqNumber;
	private String category;
	private int categorySequence;
	private String description;
	private String unit;
	private Double quantity;
	private Double rate;
	private Double amount;
	
	
	
	public Double getDisplayAmount() {
		if( amount != null)
		{
			return amount;
		}
		else if (quantity != null && rate != null)
		{
			return quantity*rate;
		}
		return null;
	}
	

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boqNumber == null) ? 0 : boqNumber.toLowerCase().trim().hashCode());
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
		BOQ other = (BOQ) obj;
		if (boqNumber == null) {
			if (other.boqNumber != null)
				return false;
		} else if (!boqNumber.trim().equalsIgnoreCase(other.boqNumber.trim()))
			return false;
		return true;
	}
	public String getBoqNumber() {
		return boqNumber;
	}
	public void setBoqNumber(String boqNumber) {
		this.boqNumber = boqNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCategorySequence() {
		return categorySequence;
	}
	public void setCategorySequence(int categorySequence) {
		this.categorySequence = categorySequence;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	

}
