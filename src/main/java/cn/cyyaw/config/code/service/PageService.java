package cn.cyyaw.config.code.service;

import com.alibaba.fastjson.JSONObject;

public interface PageService {

    /**
     * 获取页面设置
     *
     * @param tid 页面表ID
     * @return
     */
    JSONObject pageConfig(String tid);


}
