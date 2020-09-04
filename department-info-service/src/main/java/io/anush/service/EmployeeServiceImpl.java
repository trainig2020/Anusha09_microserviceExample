package io.anush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


import io.anush.model.Employee;
import io.anush.model.EmployeeList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public EmployeeList getAllEmployees(int eDid) {
		EmployeeList listEmp = restTemplate.getForObject("http://employee-info-service:8082/employees/listEmp/" + eDid,
				EmployeeList.class);
		return listEmp;
	}

	@Override
	public Employee getEmployee(int empId) {
		Employee employee = restTemplate.getForObject("http://employee-info-service:8082/employees/" + empId,
				Employee.class);
		return employee;
	}

	//@HystrixCommand(fallbackMethod = "getFallbackAddEmp")
	@Override
	public Employee addEmployee(Employee employee, int eDid) {
		return restTemplate.postForObject("http://employee-info-service:8082/employees/" + eDid + "/addEmployee",
				employee, Employee.class);
	}

	@Override
	public void updateEmployee(Employee employee, int eDid, int empId) {
		restTemplate.put("http://employee-info-service:8082/employees/" + eDid + "/updateEmployee/" + empId, employee);
	}

	@Override
	public void deleteEmployee(int eDid) {
		restTemplate.delete("http://employee-info-service:8082/employees/deleteEmployee/" + eDid, eDid);

	}

	@Override
	public void deleteSingleEmployee(int eDid, int empId) {
		restTemplate.delete("http://employee-info-service:8082/employees/deleteAll/" + eDid + "/" + empId);

	}

	public Employee getFallbackAddEmp(@RequestBody Employee employee) {
		return new Employee(0, "No Employee available", 0, 0);
	}

}
