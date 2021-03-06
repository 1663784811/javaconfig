package cn.cyyaw.config.code.service.impl;

import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.code.database.TypeTools;
import cn.cyyaw.config.code.service.PageService;
import cn.cyyaw.config.table.table.dao.page.PFieldDao;
import cn.cyyaw.config.table.table.dao.page.PPageComponentsDao;
import cn.cyyaw.config.table.table.dao.page.PPageDao;
import cn.cyyaw.config.table.table.entity.page.PField;
import cn.cyyaw.config.table.table.entity.page.PPage;
import cn.cyyaw.config.table.table.entity.page.PPageComponents;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PPageDao pPageDao;

    @Autowired
    private PPageComponentsDao pPageComponentsDao;

    @Autowired
    private PFieldDao pFieldDao;


    @Override
    public JSONObject pageConfig(String tid) {
        // 第一步：查找页面
        PPage page = pPageDao.findByTid(tid);
        JSONObject pageObj = JSONObject.parseObject(JSONObject.toJSONString(page));
        if (null != page) {
            // 第二步：查找组件
            List<PPageComponents> components = pPageComponentsDao.findByPageid(pageObj.getString("tid"));
            JSONArray componentsArr = JSONArray.parseArray(JSONArray.toJSONString(components));
            if (null != components && components.size() > 0) {
                List<String> ctidArr = new ArrayList<>();
                for (int i = 0; i < componentsArr.size(); i++) {
                    JSONObject json = componentsArr.getJSONObject(i);
                    ctidArr.add(json.getString("tid"));
                }
                List<PField> pFieldList = pFieldDao.findByComponentsId(ctidArr);
                JSONArray fieldArr = JSONArray.parseArray(JSONArray.toJSONString(pFieldList));
                JSONObject componentsObj = new JSONObject();
                for (int i = 0; i < componentsArr.size(); i++) {
                    JSONObject json = componentsArr.getJSONObject(i);
                    String ctid = json.getString("tid");
                    JSONArray arr = new JSONArray();
                    for (int j = 0; j < fieldArr.size(); j++) {
                        JSONObject js = fieldArr.getJSONObject(j);
                        String componentsid = js.getString("componentsid");
                        if (ctid.equals(componentsid)) {
                            String controldata = js.getString("controldata");
                            if(!StringUtilWHY.isEmpty(controldata)){
                                js.put("selectarr", TypeTools.getLabelAndValue(controldata));
                            }
                            arr.add(js);
                        }
                    }
                    json.put("fieldArr", arr);
                    componentsObj.put(ctid, json);
                }
                pageObj.put("components", componentsObj);
            }
        }
        return pageObj;
    }
}
