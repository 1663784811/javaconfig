package cn.cyyaw.config.home.service.impl;

import cn.cyyaw.config.home.service.GoodsService;
import cn.cyyaw.config.table.table.dao.goods.GGoodsSearchDao;
import cn.cyyaw.config.table.table.entity.goods.GGoodsSearch;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GGoodsSearchDao gGoodsSearchDao;


    @Override
    public JSONObject searchGoods(JSONObject json) {
        // 第一步：查找搜索表

        PageRequest of = PageRequest.of(1, 10);

        String searchName = ""; // 商品名，品牌名，品类名称，描述名，标签，门店名，
        List<GGoodsSearch> gGoodsSearches = gGoodsSearchDao.searchGoods(
                searchName
        );


        // 第二步：查找商品


        return null;
    }
}
