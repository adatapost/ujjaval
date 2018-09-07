package com.adatapost.model;

public class State {
	private Integer stateId, countryId;
	private String stateName, countryName;

	public State() {
		super();
		// TODO Auto-generated constructor stub
	}

	public State(Integer stateId, Integer countryId, String stateName, String countryName) {
		super();
		this.stateId = stateId;
		this.countryId = countryId;
		this.stateName = stateName;
		this.countryName = countryName;
	}

	public State(Integer stateId, Integer countryId, String stateName) {
		super();
		this.stateId = stateId;
		this.countryId = countryId;
		this.stateName = stateName;
	}

	public State(Integer stateId) {
		super();
		this.stateId = stateId;
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
		result = prime * result + ((stateId == null) ? 0 : stateId.hashCode());
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
		State other = (State) obj;
		if (stateId == null) {
			if (other.stateId != null)
				return false;
		} else if (!stateId.equals(other.stateId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", countryId=" + countryId + ", stateName=" + stateName + ", countryName="
				+ countryName + "]";
	}
}
