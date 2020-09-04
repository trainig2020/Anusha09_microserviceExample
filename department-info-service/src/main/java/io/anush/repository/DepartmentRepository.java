package io.anush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.anush.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
