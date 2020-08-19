package cn.cyyaw.config.admin.service;

import cn.cyyaw.config.table.table.entity.tadmin.TPower;

import java.util.List;

public interface MenuService {


    List<TPower> getAdminMenu(String tid);
}
