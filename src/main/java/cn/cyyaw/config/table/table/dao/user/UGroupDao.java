package cn.cyyaw.config.table.table.dao.user;


import cn.cyyaw.config.table.table.entity.user.UGroup;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UGroupDao extends BaseDao<UGroup, Integer> {

    @Query("select m from UGroup m where m.tid in (select t.groupid from UGroupUser t where t.userid = ?1 ) ")
    List<UGroup> findByUserid(String userid);
}
