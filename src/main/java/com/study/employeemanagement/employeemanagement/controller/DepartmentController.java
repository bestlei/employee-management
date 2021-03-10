package com.study.employeemanagement.employeemanagement.controller;

import java.util.List;

import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;
import com.study.employeemanagement.employeemanagement.service.DepartmentService;
import com.study.employeemanagement.employeemanagement.service.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Description:
 *
 * @author best.lei
 * @date 2021/3/5 9:56 上午
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门列表
     *
     * @param model
     * @return
     */
    @GetMapping("/depts")
    public String findAllDepartments(Model model) {
        List<DepartmentDO> departmentDOS = departmentService.findAllDepartment();
        // 放在请求域中
        model.addAttribute("depts", departmentDOS);
        return "/departments/list";
    }

    /**
     * 添加部门
     *
     * @param departmentDO
     * @return
     */
    @PostMapping("/department")
    public String addEmployee(DepartmentDO departmentDO) {
        departmentService.addDepartment(departmentDO);
        return "redirect:/depts";
    }

    /**
     * 编辑部门
     * @param departmentId
     * @param model
     * @return
     */
    @GetMapping("/department/{departmentId}")
    public String editDepartmentPage(@PathVariable("departmentId") Long departmentId, Model model) {
        DepartmentDO departmentDO = departmentService.findDepartmentDOById(departmentId);
        model.addAttribute("dept", departmentDO);
        return "/departments/add";
    }

    /**
     * 修改部门
     *
     * @param departmentDO
     * @return
     */
    @PutMapping("/department")
    public String editEmployee(DepartmentDO departmentDO) {
        departmentService.updateDepartment(departmentDO);
        return "redirect:/depts";
    }
}
