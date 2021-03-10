package com.study.employeemanagement.employeemanagement.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/3 9:39 上午
 */
@Entity(name = "DepartmentDO")
@Table(name = "department")
@lombok.Data
public class DepartmentDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long departmentId;

    private String departmentName;
}
