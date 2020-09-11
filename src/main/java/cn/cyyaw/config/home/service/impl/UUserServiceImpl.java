package cn.cyyaw.config.home.service.impl;


import cn.cyyaw.config.home.service.UUserService;
import cn.cyyaw.config.table.table.dao.user.UUserDao;
import cn.cyyaw.config.table.table.entity.user.UUser;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UUserServiceImpl extends BaseService<UUser, Integer> implements UUserService {

    @Autowired
    private UUserDao uUserDao;

    @Override
    public BaseDao getBaseDao() {
        return uUserDao;
    }

    @Override
    public UUser findByAccountAndPassword(String account, String password) {
        List<UUser> uUsers = uUserDao.findByAccount(account);
        if(uUsers.size() == 1){
            UUser uUser = uUsers.get(0);
            String pwd = uUser.getPassword();
            if(pwd.equals(password)){
                return uUser;
            }
        }else{

        }
        return null;
    }
}

