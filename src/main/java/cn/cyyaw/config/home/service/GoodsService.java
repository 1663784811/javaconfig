package cn.cyyaw.config.home.service;

import cn.cyyaw.config.table.table.entity.goods.GGoodsSearch;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;

public interface GoodsService {

    /**
     * 搜索商品
     */
    Page<GGoodsSearch> searchGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size);

    /**
     * 获取首页商品列表
     */
    JSONObject indexGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size);
}
