package com.study.employeemanagement.employeemanagement.controller;

import java.util.List;

import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;
import com.study.employeemanagement.employeemanagement.dao.entity.EmployeeDO;
import com.study.employeemanagement.employeemanagement.service.DepartmentService;
import com.study.employeemanagement.employeemanagement.service.EmployeeService;
import com.study.employeemanagement.employeemanagement.service.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/3 9:53 上午
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取员工列表
     *
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String findAllEmployees(Model model) {
        List<EmployeeDTO> allEmployees = employeeService.findAllEmployees();
        // 放在请求域中
        model.addAttribute("emps", allEmployees);
        // classpath:/templates/xxx.html
        return "/emps/list";
    }

    @GetMapping("/emp")
    public String addEmpPage(Model model) {
        List<DepartmentDO> allDepartment = departmentService.findAllDepartment();
        model.addAttribute("depts", allDepartment);
        return "/emps/add";
    }

    /**
     * 添加员工
     *
     * @param employeeDO
     * @return
     */
    @PostMapping("/emp")
    public String addEmployee(EmployeeDO employeeDO) {
        employeeService.addEmployee(employeeDO);
        return "redirect:/emps";
    }

    /**
     * 删除员工
     *
     * @param employeeId
     * @return
     */
    @DeleteMapping("/emp/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{employeeId}")
    public String editEmployeePage(@PathVariable("employeeId") Long employeeId, Model model) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(employeeId);
        List<DepartmentDO> allDepartment = departmentService.findAllDepartment();
        model.addAttribute("depts", allDepartment);
        model.addAttribute("emp", employeeDTO);
        return "/emps/add";
    }

    @PutMapping("/emp")
    public String updateEmployee(EmployeeDO employeeDO){
        employeeService.updateEmployee(employeeDO);
        return "redirect:/emps";
    }
}
