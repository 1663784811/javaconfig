package cn.cyyaw.config.admin.dao;


import cn.cyyaw.config.table.table.entity.TPower;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao  extends BaseDao<TPower, Integer> {


    @Query("select m from TPower m where m.tid in( select t.tpowerid from TAdminPower t where t.tadminid = ?1) or m.ispower = 0 order by m.sort asc ")
    List<TPower> getAdminMenu(String tid);
}
