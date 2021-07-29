package com.rueibin.dao;

import java.util.List;

import com.rueibin.entity.Employee;

public interface EmployeeDAO {
	List<Employee> getEmployees();
	List<Employee> getEmployeeByName(String name);
	Employee getEmployeeById(Integer id);
    int save(Employee emp);
    int update(Employee emp);
    int delete(Integer id);
}
