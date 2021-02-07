package com.study.employeemanagement.employeemanagement.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/3 9:36 上午
 */
@Entity(name = "EmployeeDO")
@Table(name = "employee")
@lombok.Data
public class EmployeeDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String name;

    private Integer gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private String email;

    private Long departmentId;

    private String describes;

    @Override
    public String toString() {
        return String.format(
            "employeeId:%d, name:%s, birth:%s, departmentId:%d, gender:%d, email:%s, describe:%s", employeeId,
            name, birth.toString(), departmentId, gender, email, describes);
    }
}
