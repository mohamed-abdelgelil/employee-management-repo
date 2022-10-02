package dev.me.hr.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import dev.me.hr.model.EmployeeState;

public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 732852228261714585L;

	private Long id;
	private String fullName;
	private int age;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private EmployeeState state;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<EmployeeState> states;

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

	public List<EmployeeState> getStates() {
		return states;
	}

	public void setStates(List<EmployeeState> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", fullName=" + fullName + ", age=" + age + ", state=" + state + ", states="
				+ states + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, fullName, id, state, states);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDTO other = (EmployeeDTO) obj;
		return age == other.age && Objects.equals(fullName, other.fullName) && Objects.equals(id, other.id)
				&& state == other.state && Objects.equals(states, other.states);
	}

}
