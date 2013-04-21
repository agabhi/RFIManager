package com.model.rfi;

import java.util.Set;

public class DrawStatusBarChartForm {
	
	private Integer fromChainage;
	private Integer toChainage;
	private Set<String> sides;
	private Set<String> items;
	private Set<String> statuses;
	private String activity;
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Set<String> getstatuses() {
		return statuses;
	}
	public void setstatuses(Set<String> statuses) {
		this.statuses = statuses;
	}
	private String barChartName;
	
	public String getBarChartName() {
		return barChartName;
	}
	public void setBarChartName(String barChartName) {
		this.barChartName = barChartName;
	}
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
	public Set<String> getSides() {
		return sides;
	}
	public void setSides(Set<String> sides) {
		this.sides = sides;
	}
	public Set<String> getItems() {
		return items;
	}
	public void setItems(Set<String> items) {
		this.items = items;
	}

}
