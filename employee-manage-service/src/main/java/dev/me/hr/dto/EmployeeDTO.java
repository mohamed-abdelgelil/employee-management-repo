package dev.me.hr.dto;

import java.io.Serializable;

import dev.me.hr.model.EmployeeState;

public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 732852228261714585L;

	private Long id;
	private String fullName;
	private int age;
	private EmployeeState state;

	public EmployeeDTO(Long id, String fullName, int age, EmployeeState state) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public EmployeeState getState() {
		return state;
	}

	public void setState(EmployeeState state) {
		this.state = state;
	}

}
