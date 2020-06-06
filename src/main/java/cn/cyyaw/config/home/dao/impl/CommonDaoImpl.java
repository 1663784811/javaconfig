package cn.cyyaw.config.home.dao.impl;

import cn.cyyaw.config.home.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String, Object>> query() {

        //第一步：查询  sql 字符串

        // 第二步：替换字符串


        //第三步：执行sql

        //第四步：返回结果列表


        return jdbcTemplate.queryForList("");
    }

    @Override
    public List<Map<String, Object>> update() {
        return null;
    }

    @Override
    public List<Map<String, Object>> delete() {
        return null;
    }
}
