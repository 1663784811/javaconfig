package cn.cyyaw.config.table.table.dao;


import cn.cyyaw.config.table.table.entity.page.PPageComponents;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PPageComponentsDao extends BaseDao<PPageComponents, Integer> {


    @Query("select m from PPageComponents m where m.pageid = ?1")
    List<PPageComponents> findByPageid(String pageid);
}
