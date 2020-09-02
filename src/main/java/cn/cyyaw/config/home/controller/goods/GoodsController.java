package cn.cyyaw.config.home.controller.goods;


import cn.cyyaw.config.home.service.GoodsService;
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
     */
    @PostMapping("/photo")
    public void searchGoods(@RequestBody Map<String,Object> map){
        JSONObject json = new JSONObject();
        for (String key: map.keySet()) {
            json.put(key,map.get(key));
        }
        goodsService.searchGoods(json);

    }


}
