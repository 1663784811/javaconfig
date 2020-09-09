package cn.cyyaw.config.admin.service;

import cn.cyyaw.config.table.table.entity.goods.GDetails;
import cn.cyyaw.config.table.table.entity.goods.GGoods;
import cn.cyyaw.config.table.table.entity.goods.GPhoto;
import cn.cyyaw.config.table.table.entity.goods.GSku;

import java.util.List;

public interface GGoodsService {
    /**
     * 保存商品
     */
    GGoods saveGoods(GGoods gGoods, List<GPhoto> photoList, List<GSku> skuList, GDetails gDetails);
}
