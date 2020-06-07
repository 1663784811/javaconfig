package cn.cyyaw.config.home.dao.impl;

import cn.cyyaw.common.util.SqlUtils;
import cn.cyyaw.config.home.dao.CommonDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
            String querySql = SqlUtils.explainSql(sqlcontent, json)+ " limit "+ ((page-1)*size+","+size);
            String countSql = SqlUtils.explainSql(countsql, json);
            log.info("统计sql语句: {} ", countSql);
            log.info("执行sql语句: {} ", querySql);
            //第三步：执行sql
            Integer total = jdbcTemplate.queryForObject(countSql, Integer.class);
            List<Map<String, Object>> data = jdbcTemplate.queryForList(querySql);
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
    public Map<String, Object> update(JSONObject json) {
        // 第一步: 查询表结构
        JSONArray page = tableInfo("c_page");
        for(int i=0;i<page.size();i++){
            page.getJSONObject(i);

        }
        // 判断数据库是否有数据

        // 新增或修改


        return null;
    }

    @Override
    public List<Map<String, Object>> delete(JSONObject json) {
        // 第一步: 查询表结构



        return null;
    }


    private JSONArray tableInfo(String table){
        StringBuffer sb = new StringBuffer("select ");
        sb.append(" table_name as table_name");
        sb.append(" ,column_name as column_nam");
        sb.append(" ,data_type as data_type");
        sb.append(" ,column_key as column_key");
        sb.append(" from information_schema.columns where table_name= ?");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sb.toString(), table);
        return JSONArray.parseArray(JSON.toJSON(maps).toString());
    }
}
