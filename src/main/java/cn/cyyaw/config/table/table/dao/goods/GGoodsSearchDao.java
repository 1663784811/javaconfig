package cn.cyyaw.config.table.table.dao.goods;


import cn.cyyaw.config.table.table.entity.goods.GGoodsSearch;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GGoodsSearchDao extends BaseDao<GGoodsSearch, Integer> {


    @Query("select m from GGoodsSearch m where m.del = 0 and m.name like concat('%',?1,'%')")
    List<GGoodsSearch> searchGoods(String searchName);
}
