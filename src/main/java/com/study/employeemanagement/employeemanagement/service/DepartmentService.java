package com.study.employeemanagement.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.study.employeemanagement.employeemanagement.dao.DepartmentDAO;
import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/17 10:03 上午
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentDAO departmentDAO;

    public List<DepartmentDO> findAllDepartment(){
        return departmentDAO.findAll();
    }
}
