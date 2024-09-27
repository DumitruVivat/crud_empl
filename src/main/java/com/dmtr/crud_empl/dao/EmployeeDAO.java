package com.dmtr.crud_empl.dao;

import com.dmtr.crud_empl.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

}
