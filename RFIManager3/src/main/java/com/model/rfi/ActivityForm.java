package com.model.rfi;

public class ActivityForm {

	private String activity;
	private String newActivity;
	
	public String getNewActivity() {
		return newActivity;
	}

	public void setNewActivity(String newActivity) {
		this.newActivity = newActivity;
	}

	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

}
