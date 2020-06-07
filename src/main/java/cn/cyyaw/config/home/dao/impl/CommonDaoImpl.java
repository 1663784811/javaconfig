package cn.cyyaw.config.home.dao.impl;

import cn.cyyaw.common.util.SqlUtils;
import cn.cyyaw.config.home.dao.CommonDao;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Map<String, Object> query(JSONObject json) {
        HashMap<String, Object> map = new HashMap<>();
        //第一步：查询  sql 字符串
        Integer page = json.getInteger("page");
        Integer size = json.getInteger("size");
        page = page == null ? 1 : page;
        size = size == null ? 30 : size;
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from c_sql c where c.tid = ?", json.getString("_code"));
        if (sqlRowSet.next()) {
            String countsql = sqlRowSet.getString("countsql");
            String sqlcontent = sqlRowSet.getString("sqlcontent");
            // 第二步：替换字符串
            String querySql = SqlUtils.explainSql(sqlcontent, json);
            String countSql = SqlUtils.explainSql(countsql, json);
            log.info("统计sql语句: {} ", countSql);
            log.info("执行sql语句: {} ", querySql);
            //第三步：执行sql
            Integer total = jdbcTemplate.queryForObject(countSql, Integer.class);
            List<Map<String, Object>> data = jdbcTemplate.queryForList(querySql+" limit "+ ((page-1)*size+","+size));
            //第四步：返回结果列表
            map.put("code", 0);
            map.put("data", data);
            map.put("msg", "ok");
            map.put("total", total);
            map.put("page", page);
            map.put("size", size);
            return map;
        } else {
            map.put("code", -1);
            map.put("msg", "找不到可用条件");
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> update(JSONObject json) {
        return null;
    }

    @Override
    public List<Map<String, Object>> delete(JSONObject json) {
        return null;
    }
}
