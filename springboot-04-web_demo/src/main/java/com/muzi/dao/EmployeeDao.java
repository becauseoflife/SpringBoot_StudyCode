package com.muzi.dao;

import com.muzi.pojo.Department;
import com.muzi.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    // 模拟数据库中的值
    private static Map<Integer, Employee> employees = null;
    // 员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();  // 创建部门表

        employees.put(1001, new Employee(1001, "AA", "A1752196851@qq.com", 1, new Department(101, "教学部"), new Date()));
        employees.put(1002, new Employee(1002, "BB", "B1752196851@qq.com", 0, new Department(102, "市场部"), new Date()));
        employees.put(1003, new Employee(1003, "CC", "C1752196851@qq.com", 1, new Department(103, "教研部"), new Date()));
        employees.put(1004, new Employee(1004, "DD", "D1752196851@qq.com", 0, new Department(104, "运营部"), new Date()));
        employees.put(1005, new Employee(1005, "EE", "E1752196851@qq.com", 1, new Department(105, "后勤部"), new Date()));

    }

    // 主键自增
    private static Integer initId = 1006;
    // 增加一个员工
    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        System.out.println("save ==>" + employee.toString());

        employees.put(employee.getId(), employee);
    }

    // 查询全部员工
    public Collection<Employee> getAll(){
        return employees.values();
    }

    // 通过 id 查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    // 通过 id 删除员工
    public void removeById(Integer removeId){
        if (employees.get(removeId) != null){
            employees.remove(removeId);
        }
    }

}
