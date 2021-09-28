package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询数据库的所有信息
    // 没有实体类，使用 Map 获取信息
    @GetMapping("/userList")
    public List<Map<String, Object>> userList(){
        String sql = "select * from user";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    // 可以实现增删改查
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id")Integer id){
        String sql = "update mybatis.user set name=?, pwd=? where id=" + id;

        // 封装
        Object[] objects = new Object[2];
        objects[0] = "面包+";
        objects[1] = "aaaaa";
        jdbcTemplate.update(sql, objects);

        return "Update-Ok";
    }

}
