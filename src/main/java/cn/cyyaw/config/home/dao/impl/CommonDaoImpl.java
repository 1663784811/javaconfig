package cn.cyyaw.config.home.dao.impl;

import cn.cyyaw.common.util.SqlUtils;
import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.home.dao.CommonDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
            String querySql = SqlUtils.explainSql(sqlcontent, json) + " limit " + ((page - 1) * size + "," + size);
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
        String table = json.getString("table");
        JSONArray data = json.getJSONArray("data");
        JSONArray reArr = new JSONArray();
        if (null != data && data.size() > 0) {
            JSONArray page = tableInfo(table);
            JSONArray addArr = new JSONArray();
            JSONArray updateArr = new JSONArray();
            String pk = null;
            // 判断数据库是否有数据
            for (int i = 0; i < page.size(); i++) {
                JSONObject js = page.getJSONObject(i);
                String columnName = js.getString("column_name");
                String columnKey = js.getString("column_key");
                if (columnKey.equals("PRI")) {
                    pk = columnName;
                    for (int j = 0; j < data.size(); j++) {
                        JSONObject mm = data.getJSONObject(j);
                        String id = mm.getString(columnName);
                        if (StringUtilWHY.isEmpty(id)) {
                            addArr.add(mm);
                        } else {
                            updateArr.add(mm);
                        }
                    }
                    break;
                }
            }
            if (addArr.size() > 0) {
                String sql = "insert into " + table + " ( ";
                StringBuffer sbm = new StringBuffer();
                for (int m = 0; m < page.size(); m++) {
                    if (m != 0) {
                        sbm.append(",");
                    }
                    JSONObject js = page.getJSONObject(m);
                    sbm.append(js.getString("column_name"));
                }
                sql += (sbm.toString() + ") values ");
                List<String> list = new ArrayList<>();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < addArr.size(); i++) {
                    if (i != 0) {
                        sb.append(",");
                    }
                    sb.append(" ( ");
                    JSONObject obj = addArr.getJSONObject(i);
                    for (int j = 0; j < page.size(); j++) {
                        if (j != 0) {
                            sb.append(",");
                        }
                        sb.append("?");
                        JSONObject js = page.getJSONObject(j);
                        String cn = obj.getString(js.getString("column_name"));
                        list.add(cn);
                    }
                    sb.append(" ) ");
                }
                sql += sb.toString();
                jdbcTemplate.update(sql, list.toArray());
            }
            // 修改
            if (updateArr.size() > 0) {
                if (!StringUtilWHY.isEmpty(pk)) {
                    for (int i = 0; i < addArr.size(); i++) {
                        String pkvalue = null;
                        StringBuffer sb = new StringBuffer();
                        StringBuffer set = new StringBuffer();
                        JSONObject obj = addArr.getJSONObject(i);
                        List<String> list = new ArrayList<>();
                        sb.append("update " + table + " set ");
                        for (int j = 0; j < page.size(); j++) {
                            JSONObject js = page.getJSONObject(j);
                            String name = js.getString("column_name");
                            String columnKey = js.getString("column_key");
                            String cn = obj.getString(name);
                            if (null != cn) {
                                if (columnKey.equals("PRI")) {
                                    pkvalue = columnKey;
                                } else {
                                    if (set.length() > 0) {
                                        set.append("," + name + " = ? ");
                                    } else {
                                        set.append(name + " = ? ");
                                    }
                                    list.add(pkvalue);
                                }
                            }
                        }
                        sb.append(set);
                        sb.append(" where " + pk + " = ?");
                        if (null != pkvalue) {
                            list.add(pkvalue);
                            jdbcTemplate.update(sb.toString());
                        }
                    }
                }
            }
            reArr.addAll(addArr);
            reArr.addAll(updateArr);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> delete(JSONObject json) {
        // 第一步: 查询表结构
        String table = json.getString("table");
        JSONArray data = json.getJSONArray("data");
        if (null != data && data.size() > 0) {
            JSONArray page = tableInfo(table);
            JSONArray delArr = new JSONArray();
            // 判断数据库是否有数据
            for (int i = 0; i < page.size(); i++) {
                JSONObject js = page.getJSONObject(i);
                String columnName = js.getString("column_name");
                String columnKey = js.getString("column_key");
                if (columnKey.equals("PRI")) {
                    for (int j = 0; j < data.size(); j++) {
                        JSONObject mm = data.getJSONObject(j);
                        String id = mm.getString(columnName);
                        if (!StringUtilWHY.isEmpty(id)) {
                            delArr.add(mm);
                        }
                    }
                    break;
                }
            }
            // 删除
            if (delArr.size() > 0) {

            }
        }
        return null;
    }


    private JSONArray tableInfo(String table) {
        StringBuffer sb = new StringBuffer("select ");
        sb.append(" table_name as table_name");
        sb.append(" ,column_name as column_name");
        sb.append(" ,data_type as data_type");
        sb.append(" ,column_key as column_key");
        sb.append(" from information_schema.columns where table_name= ?");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sb.toString(), table);
        return JSONArray.parseArray(JSON.toJSON(maps).toString());
    }
}
