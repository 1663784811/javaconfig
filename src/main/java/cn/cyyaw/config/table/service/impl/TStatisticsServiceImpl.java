package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TStatisticsService;
import cn.cyyaw.config.table.table.dao.TStatisticsDao;
import cn.cyyaw.config.table.table.entity.tadmin.TStatistics;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TStatisticsServiceImpl extends BaseService<TStatistics, Integer> implements TStatisticsService {

    @Autowired
    private TStatisticsDao tStatisticsDao;

    @Override
    public BaseDao getBaseDao() {
        return tStatisticsDao;
    }

}

