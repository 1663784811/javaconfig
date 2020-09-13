package cn.cyyaw.config.table.table.dao.enterprise;


import cn.cyyaw.config.table.table.entity.enterprise.EStore;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EStoreDao extends BaseDao<EStore, Integer> {


    @Query("select m from EStore m where m.tid in ( :storegoodsids ) ")
    List<EStore> findByTidIn(@Param("storegoodsids") List<String> storegoodsids);
}
