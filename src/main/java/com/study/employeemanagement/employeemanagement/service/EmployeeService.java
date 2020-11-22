package com.study.employeemanagement.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.study.employeemanagement.employeemanagement.dao.DepartmentDAO;
import com.study.employeemanagement.employeemanagement.dao.EmployeeDAO;
import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;
import com.study.employeemanagement.employeemanagement.dao.entity.EmployeeDO;
import com.study.employeemanagement.employeemanagement.service.dto.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/3 9:40 上午
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    public List<EmployeeDTO> findAllEmployees() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<EmployeeDO> employeeDOList = employeeDAO.findAll();
        if (CollectionUtils.isEmpty(employeeDOList)) {
            return new ArrayList<>();
        }

        for (EmployeeDO employeeDO : employeeDOList) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employeeDO, employeeDTO, "id", "departmentId");
            DepartmentDO departmentDO = departmentDAO.findDepartmentDOByDepartmentId(employeeDO.getDepartmentId());
            employeeDTO.setDepartmentDO(departmentDO);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    public EmployeeDTO findEmployeeById(Long employeeId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        EmployeeDO employeeDO = employeeDAO.findEmployeeDOByEmployeeId(employeeId);
        BeanUtils.copyProperties(employeeDO, employeeDTO, "id", "departmentId");
        DepartmentDO departmentDO = departmentDAO.findDepartmentDOByDepartmentId(employeeDO.getDepartmentId());
        employeeDTO.setDepartmentDO(departmentDO);
        return employeeDTO;
    }

    public void addEmployee(EmployeeDO employeeDO) {
        if (employeeDO != null && employeeDO.getEmployeeId() == null) {
            employeeDO.setEmployeeId(generateEmployeeId());
        }
        System.out.println(employeeDO.toString());
        employeeDAO.save(employeeDO);
    }

    public void updateEmployee(EmployeeDO employeeDO) {
        employeeDAO.updateEmployeeByEmployeeId(employeeDO.getEmployeeId(), employeeDO.getName(), employeeDO.getGender(),
            employeeDO.getBirth(), employeeDO.getEmail(), employeeDO.getDepartmentId(), employeeDO.getDescribes());
    }

    public void deleteEmployee(Long employeeId) {
        employeeDAO.deleteByEmployeeId(employeeId);
    }

    private Long generateEmployeeId() {
        Long maxEmployeeId = employeeDAO.getMaxEmployeeId();
        if (maxEmployeeId == null) {
            maxEmployeeId = 0L;
        }
        return ++maxEmployeeId;
    }
}
