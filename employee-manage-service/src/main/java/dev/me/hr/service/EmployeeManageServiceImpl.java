package dev.me.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.me.hr.dto.EmployeeDTO;
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
	public EmployeeDTO registerEmployee(EmployeeDTO employeeDTO) {
		// Initially when Employee is added, it is assigned ADDED state automatically
		Employee employeeEntity = new Employee(employeeDTO.getFullName(), employeeDTO.getAge(), EmployeeState.ADDED);
		employeeManageRepository.save(employeeEntity);

		employeeDTO.setId(employeeEntity.getId());
		employeeDTO.setState(employeeEntity.getState());
		return employeeDTO;
	}

	@Override
	public EmployeeDTO getEmployee(Long employeeID) {
		Employee employeeEntity = getEmployeeEntity(employeeID);
		if (employeeEntity == null) {
			// TODO throw exception
		}
		EmployeeDTO employeeDTO = new EmployeeDTO(employeeEntity.getId(), employeeEntity.getFullName(),
				employeeEntity.getAge(), employeeEntity.getState());
		return employeeDTO;
	}

	// Handle the Emplyoee State Transitions
	@Override
	public void updateEmployeeToNextState(Long employeeID) {
		Employee employeeEntity = getEmployeeEntity(employeeID);
		if (employeeEntity == null) {
			// TODO throw exception
		}
		employeeEntity.setState(EmployeeState.IN_CHECK);
		employeeManageRepository.save(employeeEntity);

	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = getAllEmployeesEntity();
		return convertEntitiesToDTOs(employees);
	}

	@Override
	public List<EmployeeDTO> getAllEmployeesByState(EmployeeState employeeState) {
		List<Employee> employees = getEmployeesEntityByState(employeeState);
		return convertEntitiesToDTOs(employees);
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

	private List<EmployeeDTO> convertEntitiesToDTOs(List<Employee> employees) {
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		if (employees != null && employees.size() > 0) {
			for (Employee employee : employees) {
				EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getFullName(), employee.getAge(),
						employee.getState());
				employeeDTOs.add(employeeDTO);
			}
		}
		return employeeDTOs;
	}

}
