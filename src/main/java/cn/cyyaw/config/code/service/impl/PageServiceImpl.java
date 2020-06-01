package cn.cyyaw.config.code.service.impl;

import cn.cyyaw.config.code.service.PageService;
import cn.cyyaw.config.table.table.dao.PFieldDao;
import cn.cyyaw.config.table.table.dao.PPageDao;
import cn.cyyaw.config.table.table.entity.PField;
import cn.cyyaw.config.table.table.entity.PPage;
import cn.cyyaw.jpa.WhyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PPageDao pPageDao;

    @Autowired
    private PFieldDao pFieldDao;

    @Override
    public Map<String, Object> pageConfig(String tid) {
        PPage page = pPageDao.findByTid(tid);
        Map<String, Object> stringObjectMap = WhyBeanUtils.bean2Map(page);
        if(null != page){
            List<PField> list = pFieldDao.findByPageid(page.getTid());
            stringObjectMap.put("fieldList", list);
        }
        return stringObjectMap;
    }
}
