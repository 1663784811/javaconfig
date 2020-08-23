package cn.cyyaw.config.table.table.dao.user;


import cn.cyyaw.config.table.table.entity.user.UUser;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UUserDao extends BaseDao<UUser, Integer> {


    @Query("select m from UUser m where m.tid in ( select t.touserid from UFriendsUser t where t.userid = ?1 )")
    List<UUser> findByUserid(String userid);


    UUser findFirstByTid(String userid);

    List<UUser> findByType(Integer type);



    @Query("select m from UUser m where m.tid in ( select t.userid from UGroupUser t where t.groupid = ?1)")
    List<UUser> findByGroup(String userid);
}
