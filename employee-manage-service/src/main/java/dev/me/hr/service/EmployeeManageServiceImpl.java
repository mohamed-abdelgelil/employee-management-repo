package dev.me.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.me.hr.dto.EmployeeDTO;
import dev.me.hr.model.Employee;
import dev.me.hr.model.EmployeeEvent;
import dev.me.hr.model.EmployeeState;
import dev.me.hr.repository.EmployeeManageRepository;

@Service
public class EmployeeManageServiceImpl implements EmployeeManageService {

	@Autowired
	private EmployeeStateMachineManager employeeStateMachineManager;

	private EmployeeManageRepository employeeManageRepository;

	@Autowired
	public EmployeeManageServiceImpl(EmployeeManageRepository employeeManageRepository) {
		super();
		this.employeeManageRepository = employeeManageRepository;
	}

	@Override
	public EmployeeDTO registerEmployee(EmployeeDTO employeeDTO) {
		// Save the DB Entity
		Employee employeeEntity = new Employee(employeeDTO.getFullName(), employeeDTO.getAge(), EmployeeState.ADDED);
		employeeManageRepository.save(employeeEntity);

		// Initiate the Employee Statemachine
		employeeStateMachineManager.initStateMachine(employeeEntity);

		// Return the EmployeeDTO
		employeeDTO.setId(employeeEntity.getId());
		employeeDTO.setState(employeeEntity.getState());
		return employeeDTO;
	}

	@Override
	public EmployeeDTO getEmployee(Long employeeID) {
		Employee employeeEntity = getEmployeeEntity(employeeID);
		if (employeeEntity == null) {
			// TODO throw custom exception
			throw new RuntimeException("Not A Valid Emplyee ID " + employeeID);
		}
		EmployeeDTO employeeDTO = convertEntityToDTO(employeeEntity);
		return employeeDTO;
	}

	// Handle the Emplyoee State Transitions
	@Override
	public void updateEmployeeState(Long employeeID, EmployeeEvent employeeEvent) {
		Employee employeeEntity = getEmployeeEntity(employeeID);
		if (employeeEntity == null) {
			// TODO throw custom exception
			throw new RuntimeException("Not A Valid Emplyee ID " + employeeID);
		}
		employeeStateMachineManager.fireEvent(employeeID, employeeEvent);
		employeeEntity.setState(employeeStateMachineManager.getState(employeeID));
		employeeManageRepository.save(employeeEntity);

	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return getAllEmployeesEntity().stream().map(emplyee -> convertEntityToDTO(emplyee))
				.collect(Collectors.toList());
	}

	@Override
	public List<EmployeeDTO> getAllEmployeesByState(EmployeeState employeeState) {
		return getEmployeesEntityByState(employeeState).stream().map(emplyee -> convertEntityToDTO(emplyee))
				.collect(Collectors.toList());
	}

	private Employee getEmployeeEntity(Long employeeID) {
		return employeeManageRepository.getEmployeeByID(employeeID);
	}

	private List<Employee> getAllEmployeesEntity() {
		return employeeManageRepository.getEmployes();
	}

	private List<Employee> getEmployeesEntityByState(EmployeeState employeeState) {
		return employeeManageRepository.getEmployesByState(employeeState);
	}

	private EmployeeDTO convertEntityToDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getFullName(), employee.getAge(),
				employee.getState());
		EmployeeState employeeState = employeeStateMachineManager.getState(employee.getId());
		if (employeeState.equals(EmployeeState.IN_CHECK)) {
			employeeDTO.setStates(employeeStateMachineManager.getStates(employee.getId()));
			employeeDTO.setState(null);
		}
		return employeeDTO;
	}

}
