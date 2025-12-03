
package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
	private Integer id;
	private String empName;
	private String empDept;
	private Double salary;

	public Employee(String empName, String empDept, Double salary) {
		super();
		this.empName = empName;
		this.empDept = empDept;
		this.salary = salary;
	}

}
