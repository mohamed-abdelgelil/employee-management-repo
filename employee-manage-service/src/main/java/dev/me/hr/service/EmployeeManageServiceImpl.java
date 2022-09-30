package dev.me.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.me.hr.model.Employee;
import dev.me.hr.model.EmployeeState;
import dev.me.hr.repository.EmployeeManageRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeManageServiceImpl implements EmployeeManageService {

	private EmployeeManageRepository employeeManageRepository;

	@Autowired
	public EmployeeManageServiceImpl(EmployeeManageRepository employeeManageRepository) {
		super();
		this.employeeManageRepository = employeeManageRepository;
	}

	@Override
	public Employee registerEmployee(Employee employee) {
		Employee employeeEntity = new Employee();
		employeeEntity.setFullName(employee.getFullName());
		employeeEntity.setAge(employee.getAge());
		// Initially when Employee is added, it is assigned ADDED state automatically
		employeeEntity.setState(EmployeeState.ADDED);
		employeeManageRepository.save(employeeEntity);
		employee.setId(employeeEntity.getId());
		employee.setState(employeeEntity.getState());
		return employee;
	}

	@Override
	public Employee getEmployee(Long employeeID) {
		return employeeManageRepository.getEmployeeByID(employeeID);
	}

	@Override
	public void updateEmployeeToNextState(Long employeeID) {
		Employee employeeEntity = getEmployee(employeeID);
		if (employeeEntity == null) {
			// TODO throw exception
		}
		employeeEntity.setState(EmployeeState.IN_CHECK);
		employeeManageRepository.save(employeeEntity);

	}

}
