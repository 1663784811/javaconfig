package cn.cyyaw.config.admin.service.impl;


import cn.cyyaw.config.admin.dao.MenuDao;
import cn.cyyaw.config.admin.service.MenuService;
import cn.cyyaw.config.table.table.entity.TPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuDao menuDao;


    @Override
    public List<TPower> getAdminMenu(String tid) {
        return menuDao.getAdminMenu(tid);
    }

}
