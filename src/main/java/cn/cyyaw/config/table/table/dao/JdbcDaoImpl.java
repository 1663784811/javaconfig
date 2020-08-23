package cn.cyyaw.config.table.table.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class JdbcDaoImpl implements JdbcDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;






}
