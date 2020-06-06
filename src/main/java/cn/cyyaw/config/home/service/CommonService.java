package cn.cyyaw.config.home.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface CommonService {


    /**
     * 通用查询
     */
    Map<String, Object>  query(JSONObject json);

    /**
     * 通用更新
     * @return
     */
    List<Map<String, Object>> update(JSONObject json);

    /**
     * 通用删除
     */
    List<Map<String, Object>> delete(JSONObject json);
}
