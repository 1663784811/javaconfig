package cn.cyyaw.config.admin.dao;


import cn.cyyaw.config.table.table.entity.TAdmin;
import cn.cyyaw.config.table.table.entity.TRole;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TRoleDaoSystem extends BaseDao<TAdmin,Integer> {

    @Query("select m from TRole m where m.tid in ( select s.troleid from TAdminRole s where s.tadminid = ?1 )")
    List<TRole> getRolesByTAdminTid(String tid);
}
