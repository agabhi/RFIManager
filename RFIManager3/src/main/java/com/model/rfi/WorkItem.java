package com.model.rfi;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workItem")
public class WorkItem {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemDescription == null) ? 0 : itemDescription.toLowerCase().trim().hashCode());
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
		WorkItem other = (WorkItem) obj;
		if (itemDescription == null) {
			if (other.itemDescription != null)
				return false;
		} else if (!itemDescription.trim().equalsIgnoreCase(other.itemDescription.trim()))
			return false;
		return true;
	}
	private String boqNumber;
	private String itemDescription;
	private Set<String> layers;
	private Set<String> sides;
	private Set<String> activities;
	private Integer workItemId;
	
	
	private Integer sequence;
	
	
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
	public Integer getWorkItemId() {
		return workItemId;
	}
	public void setWorkItemId(Integer workItemId) {
		this.workItemId = workItemId;
	}
	
	@XmlElementWrapper(name = "layers")
	@XmlElement(name = "layer")
	public Set<String> getLayers() {
		return layers;
	}
	
	public void addLayer(String layer)
	{
		if (this.layers == null)
		{
			Set<String> layers = new HashSet<String>();
			layers.add(layer);
			this.layers = layers;
		}
		else
		{
			this.layers.add(layer);
			
		}
	}
	
	public void addSide(String side)
	{
		if (this.sides == null)
		{
			Set<String> sides = new HashSet<String>();
			sides.add(side);
			this.sides = sides;
		}
		else
		{
			this.sides.add(side);
			
		}
	}
	
	public void addActivity(String activity)
	{
		if (this.activities == null)
		{
			Set<String> activities = new HashSet<String>();
			activities.add(activity);
			this.activities = activities;
		}
		else
		{
			this.activities.add(activity);
			
		}
	}
	
	public void setLayers(Set<String> layers) {
		this.layers = layers;
	}
	
	@XmlElementWrapper(name = "sides")
	@XmlElement(name = "side")
	public Set<String> getSides() {
		return sides;
	}
	public void setSides(Set<String> sides) {
		this.sides = sides;
	}
	
	@XmlElementWrapper(name = "activities")
	@XmlElement(name = "activity")
	public Set<String> getActivities() {
		return activities;
	}
	public void setActivities(Set<String> activities) {
		this.activities = activities;
	}
	
	
	/**
	 * @return the boqNumber
	 */
	public String getBoqNumber() {
		return boqNumber;
	}
	/**
	 * @param boqNumber the boqNumber to set
	 */
	public void setBoqNumber(String boqNumber) {
		this.boqNumber = boqNumber;
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
}
