package cn.cyyaw.config.home.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface CommonDao {

    /**
     * 通用查询
     * @param json
     * @return
     */
    Map<String, Object> query(JSONObject json);

    /**
     * 通用更新
     * @param json
     * @return
     */
    Map<String, Object> update(JSONObject json);

    /**
     * 通用删除
     * @param json
     * @return
     */
    List<Map<String, Object>> delete(JSONObject json);
}
