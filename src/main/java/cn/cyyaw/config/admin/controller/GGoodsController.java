package cn.cyyaw.config.admin.controller;


import cn.cyyaw.config.admin.service.GGoodsService;
import cn.cyyaw.config.table.table.entity.goods.GDetails;
import cn.cyyaw.config.table.table.entity.goods.GGoods;
import cn.cyyaw.config.table.table.entity.goods.GPhoto;
import cn.cyyaw.config.table.table.entity.goods.GSku;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RequestMapping("/shiro/admin/goods")
@RestController
public class GGoodsController {

    @Autowired
    private GGoodsService gGoodsService;

    @PostMapping("saveGoods")
    public void saveGoods(@RequestBody Map<String,Object> map){
        JSONObject json = new JSONObject();
        for (String key: map.keySet()) {
            json.put(key,map.get(key));
        }
        GGoods gGoods = JSON.parseObject(json.toJSONString(), GGoods.class);
        List<GPhoto> photoList = JSON.parseArray(json.getString("photoList"), GPhoto.class);
        List<GSku> skuList = JSON.parseArray(json.getString("skuArr"), GSku.class);
        GDetails gDetails = JSON.parseObject(json.getString("details"), GDetails.class);

        gGoodsService.saveGoods(gGoods, photoList, skuList, gDetails);
    }




}
