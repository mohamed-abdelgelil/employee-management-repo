package dev.me.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.me.hr.dto.EmployeeDTO;
import dev.me.hr.model.EmployeeEvent;
import dev.me.hr.model.EmployeeState;
import dev.me.hr.service.EmployeeManageService;

@RestController
public class EmployeeManageController {

	@Autowired
	private EmployeeManageService employeeManageService;

	@RequestMapping(value = "/employee", method = RequestMethod.POST, headers = "Accept=application/json")
	public EmployeeDTO registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeManageService.registerEmployee(employeeDTO);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<EmployeeDTO> getEmployees() {
		return employeeManageService.getAllEmployees();
	}

	@RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public EmployeeDTO getEmployee(@PathVariable Long employeeID) {
		return employeeManageService.getEmployee(employeeID);
	}

	@RequestMapping(value = "/employee/state/{employeeState}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<EmployeeDTO> getEmployees(@PathVariable EmployeeState employeeState) {
		return employeeManageService.getAllEmployeesByState(employeeState);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateEmployeeToNextState(@RequestParam Long employeeID, @RequestParam EmployeeEvent employeeEvent) {
		employeeManageService.updateEmployeeState(employeeID, employeeEvent);
	}
}
