package dev.me.hr.model;

public enum EmployeeState {
	
	ADDED("ADDED"), 
	IN_CHECK("IN_CHECK"), 
	APPROVED("APPROVED"), 
	ACTIVE("ACTIVE");
	
	
	private EmployeeState(String name) {
		this.name = name;
	}

	private String name;
	
	public String getName() {
		return this.name;
	}
	

}
