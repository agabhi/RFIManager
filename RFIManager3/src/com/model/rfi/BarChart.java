package com.model.rfi;

import java.util.Set;

public class BarChart {
	
	private Integer barChartId;
	
	public Integer getBarChartId() {
		return barChartId;
	}
	public void setBarChartId(Integer barChartId) {
		this.barChartId = barChartId;
	}
	private String barChartName;
	private Set<String> items;
	private Set<String> sides;
	private String barChartType;
	private String activity;
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	private String userName;
	
	private Set<String> statuses;
	
	public Set<String> getStatuses() {
		return statuses;
	}
	public void setStatuses(Set<String> statuses) {
		this.statuses = statuses;
	}
	public String getBarChartName() {
		return barChartName;
	}
	public void setBarChartName(String barChartName) {
		this.barChartName = barChartName;
	}
	public Set<String> getItems() {
		return items;
	}
	public void setItems(Set<String> items) {
		this.items = items;
	}
	public Set<String> getSides() {
		return sides;
	}
	public void setSides(Set<String> sides) {
		this.sides = sides;
	}
	public String getBarChartType() {
		return barChartType;
	}
	public void setBarChartType(String barChartType) {
		this.barChartType = barChartType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
