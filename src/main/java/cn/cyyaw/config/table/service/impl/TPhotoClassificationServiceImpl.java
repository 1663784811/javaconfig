package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TPhotoClassificationService;
import cn.cyyaw.config.table.table.dao.TPhotoClassificationDao;
import cn.cyyaw.config.table.table.entity.tadmin.TPhotoClassification;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TPhotoClassificationServiceImpl extends BaseService<TPhotoClassification, Integer> implements TPhotoClassificationService {

    @Autowired
    private TPhotoClassificationDao tPhotoClassificationDao;

    @Override
    public BaseDao getBaseDao() {
        return tPhotoClassificationDao;
    }

}

