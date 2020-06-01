package cn.cyyaw.config.admin.dao;


import cn.cyyaw.config.table.table.entity.TAdmin;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TAdminDaoSystem extends BaseDao<TAdmin, Integer> {

    @Query("select model from TAdmin model where model.account=?1 or model.phone=?1 or model.email=?1")
    List<TAdmin> getLoignInfo(String account);
}
