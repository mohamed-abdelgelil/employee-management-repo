package dev.me.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.me.hr.model.Employee;
import dev.me.hr.model.EmployeeState;

@Repository
public interface EmployeeManageRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT E FROM Employee E WHERE E.id = ?1")
	Employee getEmployeeByID(Long employeeID);

	@Query("SELECT E FROM Employee E")
	List<Employee> getEmployes();

	@Query("SELECT E FROM Employee E where E.state = ?1")
	List<Employee> getEmployesByState(EmployeeState employeeState);
}
