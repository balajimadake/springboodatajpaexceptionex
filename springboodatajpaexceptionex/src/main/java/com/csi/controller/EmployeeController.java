package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.signUp(employee));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public  ResponseEntity<Boolean> signIn (@PathVariable String empEmailId , @PathVariable String empPassword){
        return ResponseEntity.ok(employeeService.signIn(empEmailId , empPassword));
    }
    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById (@PathVariable int empId){
       return ResponseEntity.ok(employeeService.findById(empId));
    }
    @GetMapping("/findbyempname/{empName}")
    public ResponseEntity<List<Employee>> findByEmpName(@PathVariable String empName){
        return ResponseEntity.ok(employeeService.findByName(empName));
    }
    @GetMapping("/findbyempemail/{empEmailId}")
    public ResponseEntity<Employee> findByEMpEmailId(@PathVariable String empEmailId){
        return ResponseEntity.ok(employeeService.findByEmpEmailId(empEmailId));
    }
    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());

    }
    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId , @RequestBody Employee employee){
        Employee employee1 = employeeService.findById(empId).orElseThrow(()->new RecordNotFoundException("Employee #ID dose not exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNo(employee.getEmpContactNo());
        return ResponseEntity.ok(employeeService.update(employee1));
    }
    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeService.deleteById(empId);
        return ResponseEntity.ok("Data deleted successfully");
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        employeeService.deleteAll();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }
    @GetMapping("/sortbyiddesc")
    public ResponseEntity<List<Employee>> sortById(){
        return ResponseEntity.ok(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpId).reversed()).toList());
    }
    @GetMapping("sortbysalary")
    public ResponseEntity<Employee> sortBySalary(){
        return ResponseEntity.ok(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpSalary)).toList().get(1));
    }
    @GetMapping("/loaneligibility/{empId}")
    public ResponseEntity<String> loanEligibility(@PathVariable int empId){
        Employee employee =employeeService.findById(empId).orElseThrow(()-> new RecordNotFoundException("#ID dose not exist"));

        return ResponseEntity.ok(employee.getEmpSalary()>=150000 ? "eligible for loan ": "not eligible for loan");
        }
        @PostMapping("/savebulkdata")
     public ResponseEntity<List<Employee>> saveAll(@RequestBody List<Employee> employeeList){
        return ResponseEntity.ok(employeeService.saveAll(employeeList));
        }
    }

