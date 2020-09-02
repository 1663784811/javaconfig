package cn.cyyaw.config.table.service;

import cn.cyyaw.config.table.table.entity.enterprise.EEnterprise;
import cn.cyyaw.jpa.BaseTableService;

public interface EEnterpriseService extends BaseTableService<EEnterprise, Integer> {


    EEnterprise registerEnterprise(EEnterprise enterprise);
}
