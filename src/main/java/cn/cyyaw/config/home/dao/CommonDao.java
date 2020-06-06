package cn.cyyaw.config.home.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface CommonDao {


    Map<String, Object> query(JSONObject json);

    List<Map<String, Object>> update(JSONObject json);

    List<Map<String, Object>> delete(JSONObject json);
}
