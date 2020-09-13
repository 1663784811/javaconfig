package cn.cyyaw.config.home.service.impl;

import cn.cyyaw.config.home.service.GoodsService;
import cn.cyyaw.config.table.table.dao.enterprise.EStoreDao;
import cn.cyyaw.config.table.table.dao.goods.GGoodsDao;
import cn.cyyaw.config.table.table.dao.goods.GGoodsSearchDao;
import cn.cyyaw.config.table.table.dao.goods.GStoreGoodsSkuDao;
import cn.cyyaw.config.table.table.entity.enterprise.EStore;
import cn.cyyaw.config.table.table.entity.goods.GGoods;
import cn.cyyaw.config.table.table.entity.goods.GGoodsSearch;
import cn.cyyaw.config.table.table.entity.goods.GStoreGoodsSku;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GGoodsSearchDao gGoodsSearchDao;

    @Autowired
    private GGoodsDao gGoodsDao;

    @Autowired
    private EStoreDao eStoreDao;

    @Autowired
    private GStoreGoodsSkuDao gStoreGoodsSkuDao;

    @Override
    public Page<GGoodsSearch> searchGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size) {
        // 第一步：查找搜索表
        page = page == null ? 1 : page;
        size = size == null ? 30 : size;
        gGoodsSearch = gGoodsSearch == null ? new GGoodsSearch() : gGoodsSearch;
        PageRequest of = PageRequest.of(page-1, size, Sort.by("istop").descending().and(Sort.by("evaluate").descending()));
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);  //改变默认字符串匹配方式：模糊查询
        matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith());
        matcher.withIgnorePaths("id");  // 忽略属性：id
        matcher.withIgnoreCase(true);  //改变默认大小写忽略方式：忽略大小写
        Example<GGoodsSearch> example = Example.of(gGoodsSearch, matcher);
        return gGoodsSearchDao.findAll(example, of);
    }

    @Override
    public JSONObject indexGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size) {
        JSONObject res = new JSONObject();
        Page<GGoodsSearch> goods = searchGoods(gGoodsSearch, page, size);
        res.put("page", goods.getNumber() + 1);
        res.put("size", goods.getSize());
        res.put("total", goods.getTotalElements());
        List<GGoodsSearch> list = goods.getContent();
        List<String> goodsids = new ArrayList<>();
        List<String> storegoodsids = new ArrayList<>();
        JSONArray arr = JSONArray.parseArray(JSON.toJSONString(list));
        for (int i = 0; i < arr.size(); i++) {
            JSONObject js = arr.getJSONObject(i);
            goodsids.add(js.getString("goodsid"));
            storegoodsids.add(js.getString("storegoodsid"));
        }
        // 查商品详情
        List<GGoods> goodsInfo = gGoodsDao.findByTidIn(goodsids);
        // 查门店
        List<EStore> storeList = eStoreDao.findByTidIn(storegoodsids);
        // 查库存
        List<GStoreGoodsSku> storeSkus = gStoreGoodsSkuDao.findAllByStoreAndGoodsid(storegoodsids, goodsids);
        // 查活动
        // 查优惠
        for (int i = 0; i < arr.size(); i++) {
            JSONObject js = arr.getJSONObject(i);
            String goodsid = js.getString("goodsid");
            String storegoodsid = js.getString("storegoodsid");

            for (int g = 0; g < goodsInfo.size(); g++) {
                GGoods gGoods = goodsInfo.get(g);
                if (goodsid.equals(gGoods.getTid())) {
                    js.put("goodsInfo", gGoods);
                    break;
                }
            }

            for (int n = 0; n < storeList.size(); n++) {
                EStore eStore = storeList.get(n);
                if (storegoodsid.equals(eStore.getTid())) {
                    js.put("storeInfo", eStore);
                    break;
                }
            }

            for (int m = 0; m < storeSkus.size(); m++) {
                GStoreGoodsSku sku = storeSkus.get(m);
//                if(goodsid.equals(sku.getGoodsid()) && storegoodsid.equals(sku.gets)){
//
//                }
            }

            arr.set(i, js);
        }


        res.put("data", arr);
        return res;
    }
}
