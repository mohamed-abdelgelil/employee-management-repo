# 🚀 First part: Coding Challenge 🚀

WorkMotion (www.workmotion.com) is a global HR platform enabling companies to hire & onboard their employees internationally, at the push of a button.
It is our mission to create opportunities for anyone to work from anywhere.
As work is becoming even more global and remote, there has never been a bigger chance to build a truly global HR-tech company.

## 🧑‍💻 🤖 About the challenge

As a member of our backend engineering team, you will be responsible for building our core platform including an employee management system.
An employee entity (Employee) in this system contains very basic employee details including name, contract information, age, etc...

Employee also has a state. The state of Employee can be changed according to the predefined state transition rules.
We refer to the combination of states and state transition rules as State machine.

### 👾 State machine definition

The states for a given Employee are:
- `ADDED`
- `IN-CHECK`
- `APPROVED`
- `ACTIVE`

Furthermore, `IN-CHECK` state is special and has the following child substates:

- `SECURITY_CHECK_STARTED`
- `SECURITY_CHECK_FINISHED`


- `WORK_PERMIT_CHECK_STARTED`
- `WORK_PERMIT_CHECK_PENDING_VERIFICATION`
- `WORK_PERMIT_CHECK_FINISHED`

Initially when Employee is added, it is assigned `ADDED` state automatically.


State transitions rules are defined as follows:

![state diagram](diagrams/state_transition_diagram.png?raw=true "Statemachine diagram")

Where `BEGIN CHECK`, `FINISH SECURITY CHECK`, `COMPLETE INITIAL WORK PERMIT CHECK`, `FINISH WORK PERMIT CHECK`, `ACTIVATE` are state transition events.
Dotted arrows indicate transitions that happen automatically without a transition event.

#### 🤯 IN-CHECK state

This state has two orthogonal child state machines (also called orthogonal regions) inside itself.

This means that a complete state of an employee in the `IN-CHECK` state is defined by a triple: `(IN-CHECK, {security check state}, {work permit check state})`.
For instance, `(IN_CHECK, SECURITY_CHECK_STARTED, WORK_PERMIT_CHECK_STARTED)`.

The allowed state transitions within `IN_CHECK` state are:
- `FINISH SECURITY CHECK`: `SECURITY_CHECK_STARTED` -> `SECURITY_CHECK_FINISHED`
- `COMPLETE INITIAL WORK PERMIT CHECK`: `WORK_PERMIT_CHECK_STARTED` -> `WORK_PERMIT_CHECK_PENDING_VERIFICATION`
- `FINISH WORK PERMIT CHECK`: `WORK_PERMIT_CHECK_PENDING_VERIFICATION` -> `WORK_PERMIT_CHECK_FINISHED`

Since there are 2 orthogonal region, all permitted transitions are:
* `FINISH SECURITY CHECK`: `(IN-CHECK, SECURITY_CHECK_STARTED, WORK_PERMIT_CHECK_STARTED)` -> `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_STARTED)`
* `FINISH SECURITY CHECK`: `(IN-CHECK, SECURITY_CHECK_STARTED, WORK_PERMIT_CHECK_PENDING_VERIFICATION)` -> `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_PENDING_VERIFICATION)`
* `FINISH SECURITY CHECK`: `(IN-CHECK, SECURITY_CHECK_STARTED, WORK_PERMIT_CHECK_FINISHED)` -> `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_FINISHED)`
* `COMPLETE INITIAL WORK PERMIT CHECK`: `(IN-CHECK, SECURITY_CHECK_STARTED, WORK_PERMIT_CHECK_STARTED)` -> `(IN-CHECK, SECURITY_CHECK_STARTED, WORK_PERMIT_CHECK_PENDING_VERIFICATION)`
* `COMPLETE INITIAL WORK PERMIT CHECK`: `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_STARTED)` -> `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_PENDING_VERIFICATION)`
* `FINISH WORK PERMIT CHECK`: `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_PENDING_VERIFICATION)` -> `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_FINISHED)`
* `FINISH WORK PERMIT CHECK`: `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_PENDING_VERIFICATION)` -> `(IN-CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_FINISHED)`


Transition from `IN-CHECK` state to `APPROVED` state happens automatically from `(IN_CHECK, SECURITY_CHECK_FINISHED, WORK_PERMIT_CHECK_FINISHED)` state.
This is the only way of transitioning from `IN-CHECK` state to `APPROVED` state.

### ‍💻🧑‍🔬 Task

Your task is to build  Restful API doing the following:
- An Endpoint to add an employee
- An Endpoint to change the state of a given employee according to the state machine rules
- An Endpoint to fetch employee details

### 🙌🏻 Happy path scenarios

#### 🏃 Scenario 1 :

1. create an employee
2. Update state of the employee to `IN-CHECK`
3. Update substate of `IN-CHECK` state of the employee to `SECURITY_CHECK_FINISHED`
3. Update substate of `IN-CHECK` state of the employee to `WORK_PERMIT_CHECK_PENDING_VERIFICATION`
4. Update substate of `IN-CHECK` state the employee to `WORK_PERMIT_CHECK_FINISHED` (employee is auto-transitioned to `APPROVED` state)
5. Update state of the employee to `ACTIVE`

#### 🏃 Scenario 2 :

1. create an employee
2. Update state of the employee to `IN-CHECK`
3. Update substate of `IN-CHECK` state the employee to `WORK_PERMIT_CHECK_PENDING_VERIFICATION`
3. Update substate of `IN-CHECK` state the employee to `WORK_PERMIT_CHECK_FINISHED`
4. Update substate of `IN-CHECK` state the employee to `SECURITY_CHECK_FINISHED` (employee is auto-transitioned to `APPROVED` state)
5. Update state of the employee to `ACTIVE`

#### 🏃 Scenario 3 :

1. create an employee
2. Update state of the employee to `IN-CHECK`
3. Update substate of `IN-CHECK` state the employee to `WORK_PERMIT_CHECK_PENDING_VERIFICATION`
4. Update substate of `IN-CHECK` state the employee to `SECURITY_CHECK_FINISHED`
5. Update substate of `IN-CHECK` state the employee to `WORK_PERMIT_CHECK_FINISHED` (employee is auto-transitioned to `APPROVED` state)
6. Update state of the employee to `ACTIVE`

### 😟 Unhappy path scenarios

#### 💣 Scenario 1 :

1. create an employee
2. Update state of the employee to `IN-CHECK`
3. Update substate of `IN-CHECK` state of the employee to `SECURITY_CHECK_FINISHED`
4. Update state of the employee to `ACTIVE`: ❗✋transition `IN-CHECK` -> `ACTIVE` is not allowed

#### 💣  Scenario 2 :

1. create an employee
2. Update state of the employee to `IN-CHECK`
3. Update substate of `IN-CHECK` state the employee to `SECURITY_CHECK_FINISHED`
4. Update substate of `IN-CHECK` state the employee to `WORK_PERMIT_CHECK_FINISHED`: ❗️✋transition `WORK_PERMIT_CHECK_STARTED` -> `WORK_PERMIT_CHECK_FINISHED` is not allowed

## 🏗 What else you need to know

Our backend stack is:
- Java 11
- Spring Framework

Please provide a solution with the  above features with the following consideration.

- Being simply executable with minimum effort. Ideally using Docker and docker-compose or any similar approach
- For state management (State machine) you can use any library or data structure you consider appropriate
    - Some suggestions from our side (these are only suggestions, feel free to use something else if you want):
        - ENUM with states
        - Stateless4j library: https://github.com/stateless4j/stateless4j
        - Spring state machine: https://projects.spring.io/spring-statemachine/
- Please provide testing for your solution
- Providing an API Contract e.g. OPENAPI spec. is a big plus


# Second Part (Optional but a plus)

Being concerned about developing high quality, resilient software, giving the fact, that you will be participating, mentoring other engineers in the coding review process.


- Suggest what will be your silver bullet, concerns while you're reviewing this part of the software that you need to make sure is being there.
- What the production-readiness criteria that you consider for this solution

# Third Part (Optional but a plus)

Another Team in the company is building another service, This service will be used to provide some statistics of the employees, this could be used to list the number of employees per country, other types of statistics which is very vague at the moment.

- Please think of a solution without any further implementation that could be able to integrate on top of your service, including the integration pattern will be used, the database storage etc.

A high-level architecture diagram is sufficient to present this.
