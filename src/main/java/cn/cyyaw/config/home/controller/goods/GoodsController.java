package cn.cyyaw.config.home.controller.goods;


import cn.cyyaw.config.home.service.GoodsService;
import cn.cyyaw.config.table.table.entity.goods.GGoodsSearch;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商品
 */
@RequestMapping("/home/goods")
@RestController
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    /**
     * 搜索商品
     *
     * @return
     */
    @PostMapping("/searchGoods")
    public JSONObject searchGoods(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }
        GGoodsSearch gGoodsSearch = JSON.toJavaObject(json.getJSONObject("goods"), GGoodsSearch.class);
        Integer page = json.getInteger("page");
        Integer size = json.getInteger("size");
        // 查列表商品
        return  goodsService.indexGoods(gGoodsSearch, page, size);
    }


}
