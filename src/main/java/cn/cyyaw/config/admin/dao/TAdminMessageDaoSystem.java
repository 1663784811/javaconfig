package cn.cyyaw.config.admin.dao;


import cn.cyyaw.config.table.table.entity.TAdminMessage;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TAdminMessageDaoSystem extends BaseDao<TAdminMessage, Integer> {


    @Query("select modle from TAdminMessage modle where modle.status = ?2 and modle.tadminid = ?1")
    List<TAdminMessage> findMyMessageList(String tid, Integer status);


    @Query("select modle from TAdminMessage modle where modle.id = ?2 and modle.tadminid = ?1")
    TAdminMessage findMyMessage(String tid, Integer id);

    @Modifying
    @Query("update TAdminMessage model set model.status=?3 where model.tadminid = ?1 and model.id = ?2 ")
    TAdminMessage updateMyMessageStatus(String tid, Integer id, Integer status);
}
