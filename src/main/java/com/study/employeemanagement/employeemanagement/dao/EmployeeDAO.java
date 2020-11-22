package com.study.employeemanagement.employeemanagement.dao;

import java.util.Date;

import com.study.employeemanagement.employeemanagement.dao.entity.EmployeeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeDAO extends JpaRepository<EmployeeDO, Long> {

    EmployeeDO findEmployeeDOByEmployeeId(Long employeeId);

    @Transactional
    void deleteByEmployeeId(Long employeeId);

    @Query("select max(e.employeeId) from EmployeeDO as e")
    Long getMaxEmployeeId();

    @Transactional
    @Modifying
    @Query("update EmployeeDO as e set e.name = ?2,e.gender=?3,e.birth=?4,e.email=?5,e.departmentId=?6,e.describes=?7 where e.employeeId=?1")
    void updateEmployeeByEmployeeId(Long employeeId, String name, Integer gender, Date birth, String email, Long departmentId, String describes);
}
