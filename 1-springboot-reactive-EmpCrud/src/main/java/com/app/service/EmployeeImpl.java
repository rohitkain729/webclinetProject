package com.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;

@Service
public class EmployeeImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public String saveEmployee(Employee e) {

		return "Employee saved with id::" + repo.save(e).getId();
	}

	@Override
	public List<Employee> getAllEmployee() {

		return repo.findAll();
	}

	@Override
	public Employee getEmployee(Integer id) {

		Optional<Employee> opt = repo.findById(id);

		if (opt.isPresent()) {
			Employee emp = opt.get();
			return emp;
		} else {
			throw new RuntimeException("Employee not found");
		}
	}

	@Override
	public void deleteEmployee(Integer id) {
		Employee emp = getEmployee(id);
		if (Objects.nonNull(emp)) {
			repo.delete(emp);
		} else {
			throw new RuntimeException("empo not found");
		}
	}

	@Override
	public Employee updateEmployee(Employee e, Integer id) {

		Optional<Employee> opt = repo.findById(id);

		if (opt.isPresent()) {
			Employee empDB = opt.get();
			BeanUtils.copyProperties(e, empDB);
			repo.save(empDB);
			return repo.findById(id).get();
		} else {
			throw new RuntimeException("no emp found");
		}

	}

}
