package dev.me.hr.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8140591674171645968L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	private int age;

	@Enumerated(EnumType.STRING)
	private EmployeeState state;

	public Employee(String fullName, int age, EmployeeState state) {
		super();
		this.fullName = fullName;
		this.age = age;
		this.state = state;
	}

	public Employee() {
		super();
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
