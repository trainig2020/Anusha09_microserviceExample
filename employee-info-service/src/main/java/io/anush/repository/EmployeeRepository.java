package io.anush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.anush.model.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Modifying
	@Query(value = "delete from employee  where eDid=?1 and empId=?2", nativeQuery = true)
	public void deleteByEdidAndEmpid(int eDid, int empId);

	@Modifying
	@Query(value = "delete from employee where eDid=?1", nativeQuery = true)
	public void deleteByEdId(int eDid);

	@Modifying
	@Query(value = "select * from employee e where e.eDid=?1", nativeQuery = true)
	public List<Employee> findAllByEdId(int eDid);

}
