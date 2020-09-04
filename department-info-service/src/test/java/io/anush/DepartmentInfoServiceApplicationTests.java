package io.anush;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import io.anush.model.Department;
import io.anush.repository.DepartmentRepository;
import io.anush.service.DepartmentService;
import io.anush.service.EmployeeService;

@SpringBootTest
class DepartmentServiceApplicationTests {

	@Autowired
	private DepartmentService departmentService;

	@MockBean
	private DepartmentRepository departmentRepository;

	@Autowired
	private RestTemplate restTemplate;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void getAllDepartmentsTests() {
		when(departmentRepository.findAll()).thenReturn(
				Stream.of(new Department(1, "HR"), new Department(2, "SUPPORT")).collect(Collectors.toList()));
		assertEquals(2, departmentService.getAllDepartments().size());
		assertNotNull(departmentService.getAllDepartments().size());
	}

	@Test
	public void addDepartmentTests() {
		Department department = new Department(1, "HR");
		when(departmentRepository.save(department)).thenReturn(department);
		assertEquals(department, departmentService.addDepartment(department));
	}

	@Test
	public void updateDepartment() {
		Department department = new Department(1, "HR");
		department.setDeptName("Update HR");
		when(departmentRepository.save(department)).thenReturn(department);
		assertEquals(department, departmentService.updateDepartment(department));
	}

	@Test
	public void deleteDepartment() {
		Department department = new Department(1, "HR");
		verify(departmentRepository, times(0)).deleteById(department.getDeptId());
	}

}
