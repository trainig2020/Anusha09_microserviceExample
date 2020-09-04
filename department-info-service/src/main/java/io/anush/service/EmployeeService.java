package io.anush.service;

import org.springframework.stereotype.Repository;

import io.anush.model.Employee;
import io.anush.model.EmployeeList;

@Repository
public interface EmployeeService {

	public EmployeeList getAllEmployees(int eDId);

	public Employee getEmployee(int empId);

	public Employee addEmployee(Employee employee, int eDid);

	public void updateEmployee(Employee employee, int eDid, int empId);

	public void deleteEmployee(int empId);

	public void deleteSingleEmployee(int eDid, int empId);

}
