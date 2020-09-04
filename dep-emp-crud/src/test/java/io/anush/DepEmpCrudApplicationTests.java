package io.anush;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.anush.controller.DepEmpController;
import io.anush.model.Department;
import io.anush.model.DepartmentList;
import io.anush.model.Employee;
import io.anush.model.EmployeeList;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = DepEmpController.class)
public class DepEmpCrudApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@MockBean
	private DepEmpController depEmpController;

	ObjectMapper obj = new ObjectMapper();

	@Autowired
	private MockMvc mockmvc;

	@Test
	public void getAllDepartments() {
		Department department1 = new Department(1, "HR");
		Department department2 = new Department(2, "SUPPORT");
		List<Department> lst = new ArrayList<>();
		lst.add(department1);
		lst.add(department2);
		DepartmentList lst1 = new DepartmentList();
		lst1.setDeptList(lst);
		when(depEmpController.getListOfDepartments()).thenReturn(lst1);

		try {
			mockmvc.perform(get("/DeptList").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
					.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(2, lst1.getDeptList().size());
	}

	@Test
	public void addDepartment() {

		Department department = new Department(1, "Infrastructure");

		try {
			when(depEmpController.addDepartment(department)).thenReturn(department);
			String jasontype = obj.writeValueAsString(department);
			mockmvc.perform(post("/CreateDepartment").content(jasontype).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateDepartment() {

		Department department = new Department(1, "Infrastructure");

		try {
			verify(depEmpController, times(0)).updateDept(department, department.getDeptId());
			String jasontype = obj.writeValueAsString(department);
			mockmvc.perform(post("/UpdateDepartment").content(jasontype).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteDepartment() {
		Department department = new Department(1, "HR");

		try {
			verify(depEmpController, times(0)).deleteDept(department.getDeptId());
			mockmvc.perform(delete("/DeleteDepartment").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getAllEmployees() {
		Employee e1 = new Employee(1, "Anusha", 21, 1);
		Employee e2 = new Employee(2, "Akhila", 22, 1);
		EmployeeList lstemp = new EmployeeList();
		List<Employee> lst = new ArrayList<>();
		lst.add(e1);
		lst.add(e2);
		lstemp.setListOfEmployees(lst);
		when(depEmpController.getAllDepartments(1)).thenReturn(lstemp);

		try {
			mockmvc.perform(get("/emplist").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
					.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2, lstemp.getListOfEmployees().size());
	}

	@Test
	public void addEmployee() {
		Employee e1 = new Employee(1, "Manasa", 29, 1);

		try {
			when(depEmpController.addEmployee(e1, e1.geteDid())).thenReturn(e1);
			String jasontype = obj.writeValueAsString(e1);
			mockmvc.perform(post("/saveEmployee").content(jasontype).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void deleteEmployee() {
		Employee employee = new Employee(1, "Anusha", 24, 1);

		try {
			verify(depEmpController, times(0)).deleteEmp(employee.geteDid(), employee.getEmpId());
			mockmvc.perform(delete("/deleteEmployee").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void updateEmployee() {
		Employee e1 = new Employee(1, "Anvitha", 29, 1);
		try {
			verify(depEmpController, times(0)).updateEmp(e1, e1.geteDid(), e1.getEmpId());
			String jasontype = obj.writeValueAsString(e1);
			mockmvc.perform(post("/updateEmployee").content(jasontype).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}