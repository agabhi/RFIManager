package com.model.rfi;

public class RFICode {

	private String rfiCode;
	
	/**
	 * @return the rfiCode
	 */
	public String getRfiCode() {
		return rfiCode;
	}

	/**
	 * @param rfiCode the rfiCode to set
	 */
	public void setRfiCode(String rfiCode) {
		this.rfiCode = rfiCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rfiCode == null) ? 0 : rfiCode.toLowerCase().trim().hashCode());
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
		RFICode other = (RFICode) obj;
		if (rfiCode == null) {
			if (other.rfiCode != null)
				return false;
		} else if (!rfiCode.trim().equalsIgnoreCase(other.rfiCode.trim()))
			return false;
		return true;
	}

}
