package com.csi.service;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag=false;
        Employee employee =employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId ,empPassword);
        if(employee!=null && employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword) ){
            flag =true;
        }
        return flag;
    }

    @Override
    public Optional<Employee> findById(int empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public List<Employee> findByName(String empName) {
        return employeeRepository.findByEmpNameContainsIgnoreCase(empName);
    }

    @Override
    public Employee findByEmpEmailId(String empEmailID) {
        return employeeRepository.findAll().stream().filter(mail->mail.getEmpEmailId().equals(empEmailID)).toList().get(0);

    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int empID) {
        employeeRepository.deleteById(empID);

    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();

    }

    @Override
    public List<Employee> saveAll(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList);
    }
}
