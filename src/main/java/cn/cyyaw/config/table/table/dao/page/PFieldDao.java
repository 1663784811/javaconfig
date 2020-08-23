package cn.cyyaw.config.table.table.dao.page;


import cn.cyyaw.config.table.table.entity.page.PField;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PFieldDao extends BaseDao<PField, Integer> {


    @Query("select m from PField m where m.componentsid in( :ctidArr ) order by m.sort ")
    List<PField> findByComponentsId(@Param("ctidArr") List<String> ctidArr);
}
