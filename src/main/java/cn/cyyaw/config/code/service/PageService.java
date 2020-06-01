package cn.cyyaw.config.code.service;

import java.util.Map;

public interface PageService {

    /**
     * 获取页面设置
     *
     * @param tid 页面表ID
     * @return
     */
    Map<String, Object> pageConfig(String tid);


}
