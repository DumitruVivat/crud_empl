package com.dmtr.crud_empl.rest;

import com.dmtr.crud_empl.entity.Employee;
import com.dmtr.crud_empl.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeServiceImpl employeeService;
    @Autowired
    public EmployeeRestController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findEmployeeById(employeeId);
        if (employeeService == null){
            throw  new RuntimeException("Employee with id=" + employeeId + " not found.");
        } else {
            return employee;
        }
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.save(employee);
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }
}
