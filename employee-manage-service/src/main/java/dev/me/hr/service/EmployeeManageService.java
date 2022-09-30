package dev.me.hr.service;

import java.util.List;

import dev.me.hr.dto.EmployeeDTO;
import dev.me.hr.model.EmployeeState;

public interface EmployeeManageService {

	EmployeeDTO registerEmployee(EmployeeDTO employee);

	EmployeeDTO getEmployee(Long employeeID);

	void updateEmployeeToNextState(Long employeeID);

	List<EmployeeDTO> getAllEmployees();

	List<EmployeeDTO> getAllEmployeesByState(EmployeeState employeeState);

}
