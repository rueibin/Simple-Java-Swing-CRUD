package com.rueibin.dao;

import java.util.List;

import com.rueibin.entity.Employee;

public interface EmployeeDAO {
	List<Employee> getEmployees();
	Employee getEmployeeByName(String name);
    int save(Employee emp);
    int update(Employee emp);
    int delete(Employee id);
}
