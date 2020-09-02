package cn.cyyaw.config.home.service;

import com.alibaba.fastjson.JSONObject;

public interface GoodsService {

    /**
     * 搜索商品
     */
    JSONObject searchGoods(JSONObject json);
}
