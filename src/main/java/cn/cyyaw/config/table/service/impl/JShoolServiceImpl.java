package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.JShoolService;
import cn.cyyaw.config.table.table.dao.JShoolDao;
import cn.cyyaw.config.table.table.entity.JShool;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class JShoolServiceImpl extends BaseService<JShool, Integer> implements JShoolService {

    @Autowired
    private JShoolDao jShoolDao;

    @Override
    public BaseDao getBaseDao() {
        return jShoolDao;
    }

}

