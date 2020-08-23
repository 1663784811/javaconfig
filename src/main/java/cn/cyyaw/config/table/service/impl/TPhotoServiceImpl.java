package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TPhotoService;
import cn.cyyaw.config.table.table.dao.tadmin.TPhotoDao;
import cn.cyyaw.config.table.table.entity.tadmin.TPhoto;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TPhotoServiceImpl extends BaseService<TPhoto, Integer> implements TPhotoService {

    @Autowired
    private TPhotoDao tPhotoDao;

    @Override
    public BaseDao getBaseDao() {
        return tPhotoDao;
    }

}

