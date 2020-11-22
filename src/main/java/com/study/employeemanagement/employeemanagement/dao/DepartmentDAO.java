package com.study.employeemanagement.employeemanagement.dao;

import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDAO extends JpaRepository<DepartmentDO,Long> {

    DepartmentDO findDepartmentDOByDepartmentId(Long departmentId);
}
