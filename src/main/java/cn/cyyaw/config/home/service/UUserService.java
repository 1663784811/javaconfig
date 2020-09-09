package cn.cyyaw.config.home.service;

import cn.cyyaw.config.table.table.entity.user.UUser;
import cn.cyyaw.jpa.BaseTableService;

public interface UUserService extends BaseTableService<UUser, Integer> {


    UUser findByAccountAndPassword(String account, String password);
}
