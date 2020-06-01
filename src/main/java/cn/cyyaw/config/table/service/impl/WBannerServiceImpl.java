package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.WBannerService;
import cn.cyyaw.config.table.table.dao.WBannerDao;
import cn.cyyaw.config.table.table.entity.WBanner;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class WBannerServiceImpl extends BaseService<WBanner, Integer> implements WBannerService {

    @Autowired
    private WBannerDao wBannerDao;

    @Override
    public BaseDao getBaseDao() {
        return wBannerDao;
    }

}

