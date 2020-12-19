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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * CacheManager管理多个Cache组件的，对缓存的真正操作在Cache组件中，每个缓存组件都有一个唯一名称
     * 几个属性
     * cacheNames/value 指定缓存的名称
     * key：缓存数据时需要指定的key，默认使用方法参数的值
     * keygenerator:key生成器，key和keygenerator二选一使用
     * CacheManager：缓存管理器
     * CacheResolver：缓存解析器
     * condition：指定缓存的条件则缓存
     * unless：当unless指定的条件为true，则不缓存，可以获取结果进行判断
     * sync:是否使用异步
     * @param employeeId
     * @return
     */
    @Cacheable(cacheNames = "emp", key = "#employeeId")
    public EmployeeDTO findEmployeeById(Long employeeId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        System.out.println("查询员工信息，employeeId：" + employeeId);
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

    /**
     * 执行顺序
     * 1、先执行方法
     * 2、将方法返回结果更新缓存中
     * @param employeeDO
     */
    @CachePut(cacheNames = "emp",key = "#employeeDO.employeeId")
    public EmployeeDTO updateEmployee(EmployeeDO employeeDO) {
        employeeDAO.updateEmployeeByEmployeeId(employeeDO.getEmployeeId(), employeeDO.getName(), employeeDO.getGender(),
            employeeDO.getBirth(), employeeDO.getEmail(), employeeDO.getDepartmentId(), employeeDO.getDescribes());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeDO, employeeDTO, "id", "departmentId");
        DepartmentDO departmentDO = departmentDAO.findDepartmentDOByDepartmentId(employeeDO.getDepartmentId());
        employeeDTO.setDepartmentDO(departmentDO);
        return employeeDTO;
    }

    @CacheEvict(cacheNames = "emp", key = "#employeeId")
    public void deleteEmployee(Long employeeId) {
        System.out.println("删除员工:" + employeeId);
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
