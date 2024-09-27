package com.dmtr.crud_empl.service;

import com.dmtr.crud_empl.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
}
