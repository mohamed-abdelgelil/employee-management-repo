package dev.me.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.me.hr.model.Employee;

@Repository
public interface EmployeeManageRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT E FROM Employee E WHERE E.id =?1")
	Employee getEmployeeByID(Long employeeID);
}
