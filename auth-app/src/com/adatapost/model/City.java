package com.adatapost.model;

public class City {
	private Integer cityId,
	                stateId,
	                countryId;
	private String cityName, 
	               stateName,
	               countryName;
	public City() {
	}
	public City(Integer cityId, Integer stateId, String cityName) {
		super();
		this.cityId = cityId;
		this.stateId = stateId;
		this.cityName = cityName;
	}
	public City(Integer cityId, Integer stateId, Integer countryId, String cityName, String stateName,
			String countryName) {
		super();
		this.cityId = cityId;
		this.stateId = stateId;
		this.countryId = countryId;
		this.cityName = cityName;
		this.stateName = stateName;
		this.countryName = countryName;
	}
	public City(Integer cityId) {
		super();
		this.cityId = cityId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
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
		City other = (City) obj;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", stateId=" + stateId + ", countryId=" + countryId + ", cityName=" + cityName
				+ ", stateName=" + stateName + ", countryName=" + countryName + "]";
	}
}
