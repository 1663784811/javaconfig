package cn.cyyaw.config.home.service.impl;

import cn.cyyaw.config.home.dao.CommonDao;
import cn.cyyaw.config.home.service.CommonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Map<String, Object> query(JSONObject json) {
        return commonDao.query(json);
    }

    @Override
    public List<Map<String, Object>> update(JSONObject json) {
        return commonDao.update(json);
    }

    @Override
    public List<Map<String, Object>> delete(JSONObject json) {
        return commonDao.delete(json);
    }
}
