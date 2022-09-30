package dev.me.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.me.hr.model.Employee;
import dev.me.hr.service.EmployeeManageService;

@RestController
public class EmployeeManageController {

	@Autowired
	private EmployeeManageService employeeManageService;

	@RequestMapping(value = "/employee", method = RequestMethod.POST, headers = "Accept=application/json")
	public Employee registerEmployee(@RequestBody Employee employee) {
		return employeeManageService.registerEmployee(employee);
	}

	@RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Employee getEmployee(@PathVariable Long employeeID) {
		return employeeManageService.getEmployee(employeeID);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateEmployeeToNextState(@RequestParam Long employeeID) {
		employeeManageService.updateEmployeeToNextState(employeeID);
	}
}
