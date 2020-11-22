package com.study.employeemanagement.employeemanagement.service.dto;

import java.util.Date;

import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/3 9:44 上午
 */
@lombok.Data
public class EmployeeDTO {
    
    private Long employeeId;

    private String name;

    private Integer gender;

    private Date birth;

    private String email;

    private DepartmentDO departmentDO;

    private String describes;
}
