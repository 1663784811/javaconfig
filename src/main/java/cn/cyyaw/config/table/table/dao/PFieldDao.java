package cn.cyyaw.config.table.table.dao;


import cn.cyyaw.config.table.table.entity.PField;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PFieldDao extends BaseDao<PField, Integer> {


    @Query("select m from PField m where m.pageid = ?1")
    List<PField> findByPageid(String tid);
}
