package com.muzi.controller;

import com.muzi.dao.DepartmentDao;
import com.muzi.dao.EmployeeDao;
import com.muzi.pojo.Department;
import com.muzi.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddEmployeePage(Model model){
        // 查出部门的数据
        Collection<Department> departments = departmentDao.getAllDepartment();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmployee(Employee e){
        // 添加的操作
        System.out.println("addEmployee==>" + e.toString());
        employeeDao.save(e);

        return "redirect:/emps";
    }

    // 去员工的修改界面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id, Model model){
        // 查出原来的数据
        Employee e = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", e);
        // 查出部门的数据
        Collection<Department> departments = departmentDao.getAllDepartment();
        model.addAttribute("departments", departments);

        return "emp/update";
    }

    // 修改员工
    @PostMapping("/updateEmp")
    public String updateEmp(Employee e){
        employeeDao.save(e);

        return "redirect:/emps";
    }

    // 删除员工
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        employeeDao.removeById(id);

        return "redirect:/emps";
    }

    // 退出登录
    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }

}
