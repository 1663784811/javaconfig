package cn.cyyaw.config.table.table.dao.goods;


import cn.cyyaw.config.table.table.entity.goods.GStoreGoodsSku;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GStoreGoodsSkuDao extends BaseDao<GStoreGoodsSku, Integer> {


    @Query("select m from GStoreGoodsSku m where m.goodsid in( :goodsids ) and m.storegoodsid in ( select t.tid from GStoreGoods t where t.storeid in(:storegoodsids) ) ")
    List<GStoreGoodsSku> findAllByStoreAndGoodsid(List<String> storegoodsids, List<String> goodsids);
}
