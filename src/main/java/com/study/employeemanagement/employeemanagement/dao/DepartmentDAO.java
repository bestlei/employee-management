package com.study.employeemanagement.employeemanagement.dao;

import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DepartmentDAO extends JpaRepository<DepartmentDO,Long> {

    DepartmentDO findDepartmentDOByDepartmentId(Long departmentId);

    @Transactional
    @Modifying
    @Query("UPDATE DepartmentDO AS d SET d.departmentName = ?1 WHERE d.departmentId = ?2")
    void updateDepartmentDOById(String departmentName, Long departmentId);
}
