package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.JMajorService;
import cn.cyyaw.config.table.table.dao.JMajorDao;
import cn.cyyaw.config.table.table.entity.JMajor;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class JMajorServiceImpl extends BaseService<JMajor, Integer> implements JMajorService {

    @Autowired
    private JMajorDao jMajorDao;

    @Override
    public BaseDao getBaseDao() {
        return jMajorDao;
    }

}

