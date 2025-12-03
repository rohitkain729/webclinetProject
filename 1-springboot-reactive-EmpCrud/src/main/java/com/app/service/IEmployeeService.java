package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.Employee;

public interface IEmployeeService {

	public String saveEmployee(Employee e);

	public List<Employee> getAllEmployee();

	public Employee getEmployee(Integer id);

	public void deleteEmployee(Integer id);

	public Employee updateEmployee(Employee e,Integer id);
}
