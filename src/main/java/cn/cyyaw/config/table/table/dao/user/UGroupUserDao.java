package cn.cyyaw.config.table.table.dao.user;


import cn.cyyaw.config.table.table.entity.user.UGroupUser;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UGroupUserDao extends BaseDao<UGroupUser, Integer> {

    @Query("select m from UGroupUser m where m.groupid = ?1")
    List<UGroupUser> findByGroupid(String groupid);
}
