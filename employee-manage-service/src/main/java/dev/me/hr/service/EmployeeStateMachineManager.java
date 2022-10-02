package dev.me.hr.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import dev.me.hr.model.Employee;
import dev.me.hr.model.EmployeeEvent;
import dev.me.hr.model.EmployeeState;

@Component
public class EmployeeStateMachineManager {

	private static final Map<Long, StateMachine<EmployeeState, EmployeeEvent>> stateMachineMap = new Hashtable<Long, StateMachine<EmployeeState, EmployeeEvent>>();

	@Autowired
	private StateMachineFactory<EmployeeState, EmployeeEvent> stateMachineFactory;

	@SuppressWarnings("deprecation")
	public EmployeeState initStateMachine(Employee employee) {
		StateMachine<EmployeeState, EmployeeEvent> stateMachine = stateMachineFactory
				.getStateMachine("EmpID_" + employee.getId());
		stateMachine.getExtendedState().getVariables().put(employee.getId(), employee);
		stateMachine.getExtendedState().getVariables().put("activate", "false");
		stateMachine.start();
		stateMachineMap.put(employee.getId(), stateMachine);
		return getState(employee.getId());
	}

	@SuppressWarnings("deprecation")
	public EmployeeState fireEvent(Long employeeId, EmployeeEvent employeeEvent) {
		validateOnAvtivation(employeeId, employeeEvent);
		if(!stateMachineMap.get(employeeId).sendEvent(employeeEvent)) {
			// TODO throw Custom Exception
			throw new RuntimeException("Not A Valid Transition " + employeeEvent + " After " + getState(employeeId));
		}
		return getState(employeeId);
	}

	public EmployeeState getState(Long employeeId) {
		return stateMachineMap.get(employeeId).getState().getId();
	}

	public List<EmployeeState> getStates(Long employeeId) {
		return (List<EmployeeState>) stateMachineMap.get(employeeId).getState().getIds();
	}

	private void validateOnAvtivation(Long employeeId, EmployeeEvent employeeEvent) {
		if (employeeEvent.equals(EmployeeEvent.ACTIVATE) && getState(employeeId).equals(EmployeeState.APPROVED)) {
			stateMachineMap.get(employeeId).getExtendedState().getVariables().put("activate", "true");
		}
	}

}
