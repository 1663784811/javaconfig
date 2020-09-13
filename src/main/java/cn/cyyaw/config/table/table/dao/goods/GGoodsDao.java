package cn.cyyaw.config.table.table.dao.goods;


import cn.cyyaw.config.table.table.entity.goods.GGoods;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GGoodsDao extends BaseDao<GGoods, Integer> {


    @Query("select m from GGoods m where m.tid in ( :goodsid ) ")
    List<GGoods> findByTidIn(@Param("goodsid") List<String> goodsids);
}
