package com.csi.service;

import com.csi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Employee signUp(Employee employee);

    boolean signIn(String empEmailId , String empPassword);

   Optional<Employee>  findById (int empId);

    List<Employee> findByName (String empName);

    Employee findByEmpEmailId(String empEmailID);

    List<Employee> findAll();

    Employee update(Employee employee);

    void deleteById(int empID);

    void  deleteAll ();


    List<Employee> saveAll(List<Employee> employeeList);
}
