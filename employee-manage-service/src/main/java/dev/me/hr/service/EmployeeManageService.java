package dev.me.hr.service;

import dev.me.hr.model.Employee;

public interface EmployeeManageService {

	Employee registerEmployee(Employee employee);

	Employee getEmployee(Long employeeID);

	void updateEmployeeToNextState(Long employeeID);

}
