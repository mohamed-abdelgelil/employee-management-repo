package dev.me.hr.config.statemachine;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import dev.me.hr.model.EmployeeEvent;
import dev.me.hr.model.EmployeeState;

@Configuration
@EnableStateMachineFactory
public class EmployeeSatateMachineConfig extends EnumStateMachineConfigurerAdapter<EmployeeState, EmployeeEvent> {

	
	
	@Override
	public void configure(StateMachineConfigurationConfigurer<EmployeeState, EmployeeEvent> config) throws Exception {
		config.withConfiguration()
				// .autoStartup(true)
				.listener(listener());
	}
	
	@Override
	public void configure(StateMachineStateConfigurer<EmployeeState, EmployeeEvent> states) throws Exception {
		
		states.withStates()
		.initial(EmployeeState.ADDED)
		.fork(EmployeeState.IN_CHECK) // Fork Point
		.join(EmployeeState.APPROVED) // Join Point
		.end(EmployeeState.ACTIVE)
		.and().withStates()
			.parent(EmployeeState.IN_CHECK)
			.region("WORK")
			.initial(EmployeeState.WORK_PERMIT_CHECK_STARTED)
			.state(EmployeeState.WORK_PERMIT_CHECK_PENDING_VERIFICATION)
			.end(EmployeeState.WORK_PERMIT_CHECK_FINISHED)
		.and().withStates()
			.parent(EmployeeState.IN_CHECK)
			.region("SECURITY")
			.initial(EmployeeState.SECURITY_CHECK_STARTED)
			.end(EmployeeState.SECURITY_CHECK_FINISHED);
		
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<EmployeeState, EmployeeEvent> transitions) throws Exception {
		
		transitions.withExternal()
		.source(EmployeeState.ADDED).target(EmployeeState.IN_CHECK).event(EmployeeEvent.BEGIN_CHECK)
		.and().withFork() //Start the Fork at the IN_CHECK State
		.source(EmployeeState.IN_CHECK).target(EmployeeState.WORK_PERMIT_CHECK_STARTED).target(EmployeeState.SECURITY_CHECK_STARTED)
		.and().withLocal()
		.source(EmployeeState.WORK_PERMIT_CHECK_STARTED).target(EmployeeState.WORK_PERMIT_CHECK_PENDING_VERIFICATION).event(EmployeeEvent.COMPLETE_INITIAL_WORK_PERMIT_CHECK)
		.and().withLocal()
		.source(EmployeeState.WORK_PERMIT_CHECK_PENDING_VERIFICATION).target(EmployeeState.WORK_PERMIT_CHECK_FINISHED).event(EmployeeEvent.FINISH_WORK_PERMIT_CHECK)
		.and().withLocal()
		.source(EmployeeState.SECURITY_CHECK_STARTED).target(EmployeeState.SECURITY_CHECK_FINISHED).event(EmployeeEvent.FINISH_SECURITY_CHECK)
		.and().withJoin() // Join States again on APPROVE State
		.source(EmployeeState.SECURITY_CHECK_FINISHED).source(EmployeeState.WORK_PERMIT_CHECK_FINISHED).target(EmployeeState.APPROVED)
		.and().withExternal()
		.source(EmployeeState.APPROVED).target(EmployeeState.ACTIVE).guardExpression("extendedState.variables.get('activate').equals('true')").event(EmployeeEvent.ACTIVATE);	
		
	}
	
	
	@Bean
	public StateMachineListener<EmployeeState, EmployeeEvent> listener() {
		return new StateMachineListenerAdapter<EmployeeState, EmployeeEvent>() {


			@Override
			public void stateChanged(State<EmployeeState, EmployeeEvent> from, State<EmployeeState, EmployeeEvent> to) {
				//Commented as it's already handled at the service Layer
				//System.out.println("Employee State changed to " + to.getId());
			}

		};
	}
}
