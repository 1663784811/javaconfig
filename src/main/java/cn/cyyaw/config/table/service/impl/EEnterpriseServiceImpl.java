package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.EEnterpriseService;
import cn.cyyaw.config.table.table.dao.enterprise.EEnterpriseDao;
import cn.cyyaw.config.table.table.entity.enterprise.EEnterprise;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EEnterpriseServiceImpl extends BaseService<EEnterprise, Integer> implements EEnterpriseService {

    @Autowired
    private EEnterpriseDao eEnterpriseDao;

    @Override
    public BaseDao getBaseDao() {
        return eEnterpriseDao;
    }

    /**
     * 注册企业
     * @param enterprise
     * @return
     */
    @Override
    public EEnterprise registerEnterprise(EEnterprise enterprise) {
        // 第一步: 注册企业
        EEnterprise save = eEnterpriseDao.save(enterprise);


        return save;
    }
}

