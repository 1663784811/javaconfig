package cn.cyyaw.config.table.table.dao.user;

import cn.cyyaw.config.table.table.entity.user.UGroupMessage;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UGroupMessageDao extends BaseDao<UGroupMessage, Integer> {


    @Query("select m from UGroupMessage m where m.groupid = ?1 and m.createtime <= ?2 ")
    List<UGroupMessage> findByGroupidAndCreatetime(String groupid, Date createtime, Pageable pageable);
}
