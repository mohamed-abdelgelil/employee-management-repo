package dev.me.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.me.hr.EmployeeManageServiceApplication;
import dev.me.hr.config.exception.TransitionException;
import dev.me.hr.dto.EmployeeDTO;
import dev.me.hr.model.EmployeeEvent;
import dev.me.hr.model.EmployeeState;
import dev.me.hr.service.EmployeeManageServiceImpl;



@SpringBootTest(classes = EmployeeManageServiceApplication.class)
class EmployeeManageServiceApplicationTests {

	@Autowired
	EmployeeManageServiceImpl employeeManageServiceImpl;
	
	//Happy path scenarios :)
	
	@Test
	void happey_scenario_1() {//Scenario #1
		
		//1.create an employee
		EmployeeDTO employeeDTO = createEmployee();
		assertEquals(EmployeeState.ADDED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
		//2.Update state of the employee to IN-CHECK
		updateState(employeeDTO.getId(), EmployeeEvent.BEGIN_CHECK);
		ArrayList<EmployeeState> beginState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(beginState));
		
		//3.Update substate of IN-CHECK state the employee to SECURITY_CHECK_FINISHED 
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_SECURITY_CHECK);
		ArrayList<EmployeeState> finishSecState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_FINISHED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(finishSecState));
		
		
		//4.Update substate of IN-CHECK state the employee to WORK_PERMIT_CHECK_PENDING_VERIFICATION
		updateState(employeeDTO.getId(), EmployeeEvent.COMPLETE_INITIAL_WORK_PERMIT_CHECK);
		ArrayList<EmployeeState> verifcicationState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_PENDING_VERIFICATION, EmployeeState.SECURITY_CHECK_FINISHED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(verifcicationState));
		
		
		//5.Update substate of IN-CHECK state the employee to WORK_PERMIT_CHECK_FINISHED
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_WORK_PERMIT_CHECK);
		assertEquals(EmployeeState.APPROVED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
			
		//6.Update state of the employee to ACTIVE
		updateState(employeeDTO.getId(), EmployeeEvent.ACTIVATE);
		assertEquals(EmployeeState.ACTIVE, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
	}
	
	@Test
	void happey_scenario_2() {//Scenario #2
		
		//1.create an employee
		EmployeeDTO employeeDTO = createEmployee();
		assertEquals(EmployeeState.ADDED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
		//2.Update state of the employee to IN-CHECK
		updateState(employeeDTO.getId(), EmployeeEvent.BEGIN_CHECK);
		ArrayList<EmployeeState> beginState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(beginState));
		
		
		//3.Update substate of IN-CHECK state the employee to WORK_PERMIT_CHECK_PENDING_VERIFICATION
		updateState(employeeDTO.getId(),EmployeeEvent.COMPLETE_INITIAL_WORK_PERMIT_CHECK);
		ArrayList<EmployeeState> verifcicationState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_PENDING_VERIFICATION, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(verifcicationState));
		
		//4.Update substate of IN-CHECK state the employee to WORK_PERMIT_CHECK_FINISHED
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_WORK_PERMIT_CHECK);
		ArrayList<EmployeeState> workFinishState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_FINISHED, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(workFinishState));
		
		//5.Update substate of IN-CHECK state the employee to SECURITY_CHECK_FINISHED 
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_SECURITY_CHECK);
		assertEquals(EmployeeState.APPROVED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
		
		//6.Update state of the employee to ACTIVE
		updateState(employeeDTO.getId(), EmployeeEvent.ACTIVATE);
		assertEquals(EmployeeState.ACTIVE, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
		
	}
	
	@Test
	void happey_scenario_3() {//Scenario #3
		
		//1.create an employee
		EmployeeDTO employeeDTO = createEmployee();
		assertEquals(EmployeeState.ADDED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
		//2.Update state of the employee to IN-CHECK
		updateState(employeeDTO.getId(), EmployeeEvent.BEGIN_CHECK);
		ArrayList<EmployeeState> beginState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(beginState));
		
		//3.Update substate of IN-CHECK state the employee to WORK_PERMIT_CHECK_PENDING_VERIFICATION
		updateState(employeeDTO.getId(), EmployeeEvent.COMPLETE_INITIAL_WORK_PERMIT_CHECK);
		ArrayList<EmployeeState> verifcicationState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_PENDING_VERIFICATION, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue(employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(verifcicationState));
		
		//4.Update substate of IN-CHECK state the employee to SECURITY_CHECK_FINISHED 
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_SECURITY_CHECK);
		ArrayList<EmployeeState> finishSecState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_PENDING_VERIFICATION, EmployeeState.SECURITY_CHECK_FINISHED));
		assertTrue(employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(finishSecState));
		
		
		//5.Update substate of IN-CHECK state the employee to WORK_PERMIT_CHECK_FINISHED
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_WORK_PERMIT_CHECK);
		assertEquals(EmployeeState.APPROVED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());	
		
		
		//6.Update state of the employee to ACTIVE
		updateState(employeeDTO.getId(), EmployeeEvent.ACTIVATE);
		assertEquals(EmployeeState.ACTIVE, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
				
	}
	
	
	
	
	
	//Unhappy path scenarios :(
	
	@Test
	void unhappey_scenario_1() {//Scenario #1
		//1.create an employee
		EmployeeDTO employeeDTO = createEmployee();
		assertEquals(EmployeeState.ADDED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
		// 2.Update state of the employee to IN-CHECK
		updateState(employeeDTO.getId(), EmployeeEvent.BEGIN_CHECK);
		ArrayList<EmployeeState> beginState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(beginState));
		
		//3.Update substate of IN-CHECK state the employee to SECURITY_CHECK_FINISHED 
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_SECURITY_CHECK);
		ArrayList<EmployeeState> finishSecState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_FINISHED));
		assertTrue(employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(finishSecState));
		
		//4.Update state of the employee to ACTIVE
		assertThrows(TransitionException.class,() -> {updateState(employeeDTO.getId(), EmployeeEvent.ACTIVATE);});
		
		
	}
	
	/**
	 * 
	 */
	@Test
	void unhappey_scenario_2() {//Scenario #2
		//1.create an employee
		EmployeeDTO employeeDTO = createEmployee();
		assertEquals(EmployeeState.ADDED, employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getState());
		
		// 2.Update state of the employee to IN-CHECK
		updateState(employeeDTO.getId(), EmployeeEvent.BEGIN_CHECK);
		ArrayList<EmployeeState> beginState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_STARTED));
		assertTrue( employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(beginState));
		
		//3.Update substate of IN-CHECK state the employee to SECURITY_CHECK_FINISHED 
		updateState(employeeDTO.getId(), EmployeeEvent.FINISH_SECURITY_CHECK);
		ArrayList<EmployeeState> finishSecState = new ArrayList<EmployeeState>(Arrays.asList(EmployeeState.IN_CHECK,
				EmployeeState.WORK_PERMIT_CHECK_STARTED, EmployeeState.SECURITY_CHECK_FINISHED));
		assertTrue(employeeManageServiceImpl.getEmployee(employeeDTO.getId()).getStates().containsAll(finishSecState));
		
		//4.Update substate of IN-CHECK state the employee to WORK_PERMIT_CHECK_FINISHED
		assertThrows(TransitionException.class,() -> {updateState(employeeDTO.getId(), EmployeeEvent.FINISH_WORK_PERMIT_CHECK);});
				
	}
	
	private EmployeeDTO createEmployee() {
		return employeeManageServiceImpl.registerEmployee(new EmployeeDTO(null, "Mohamed", 33, null));
	};
	
	private void updateState(Long employeeID, EmployeeEvent employeeEvent) {
		employeeManageServiceImpl.updateEmployeeState(employeeID, employeeEvent);
	}
	

}
