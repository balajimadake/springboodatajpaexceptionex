package com.csi.repository;

import com.csi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

 Employee findByEmpEmailIdAndEmpPassword(String empEmailId , String empPassword);

    @Query("SELECT emp FROM Employee emp WHERE LOWER(emp.empName) LIKE LOWER(CONCAT('%', :empName, '%'))")
    List<Employee> findByEmpNameContainsIgnoreCase(@Param("empName") String empName);


}
