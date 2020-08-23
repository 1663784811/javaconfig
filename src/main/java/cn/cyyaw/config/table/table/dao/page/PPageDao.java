package cn.cyyaw.config.table.table.dao.page;


import cn.cyyaw.config.table.table.entity.page.PPage;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

public interface PPageDao extends BaseDao<PPage, Integer> {

    @Query("select m from PPage m where m.tid = ?1")
    PPage findByTid(String tid);
}
