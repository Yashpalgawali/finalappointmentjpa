package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee emp);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(String id);
	
	public int updateEmployee(Employee empl);

	//public Employee getEmployeeByDeptId(String deptid);

	public Employee getDeptByEmpId(String id);
	
}

