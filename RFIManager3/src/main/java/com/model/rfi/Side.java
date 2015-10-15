package com.model.rfi;

public class Side {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((side == null) ? 0 : side.toLowerCase().trim().hashCode());
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
		Side other = (Side) obj;
		if (side == null) {
			if (other.side != null)
				return false;
		} else if (!side.trim().equalsIgnoreCase(other.side.trim()))
			return false;
		return true;
	}
	private String side;    //primary field
	private String sideDescription;
	
	/**
	 * @return the side
	 */
	public String getSide() {
		return side;
	}
	/**
	 * @param side the side to set
	 */
	public void setSide(String side) {
		this.side = side;
	}
	/**
	 * @return the sideDescription
	 */
	public String getSideDescription() {
		return sideDescription;
	}
	/**
	 * @param sideDescription the sideDescription to set
	 */
	public void setSideDescription(String sideDescription) {
		this.sideDescription = sideDescription;
	}

}
