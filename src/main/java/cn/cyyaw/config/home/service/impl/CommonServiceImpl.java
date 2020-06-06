package cn.cyyaw.config.home.service.impl;

import cn.cyyaw.config.home.dao.CommonDao;
import cn.cyyaw.config.home.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<Map<String, Object>> query() {
        return commonDao.query();
    }

    @Override
    public List<Map<String, Object>> update() {
        return commonDao.update();
    }

    @Override
    public List<Map<String, Object>> delete() {
        return commonDao.delete();
    }
}
