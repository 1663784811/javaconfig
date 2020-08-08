package cn.cyyaw.config.code.controller;


import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.config.code.database.TypeTools;
import cn.cyyaw.config.code.service.PageService;
import cn.cyyaw.config.code.tools.database.DataBase;
import cn.cyyaw.config.code.tools.entity.java.JavaColumn;
import cn.cyyaw.config.code.tools.entity.java.JavaData;
import cn.cyyaw.config.code.tools.entity.vue.VueJson;
import cn.cyyaw.config.table.table.entity.PField;
import cn.cyyaw.config.table.table.entity.PPage;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/config/page")
@RestController
public class PageController {

    @Autowired
    private PageService pageService;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String pwd;

    /**
     * 获取页面设置
     */
    @PostMapping("/pageConfig")
    public void pageConfig(HttpServletResponse response, @RequestBody PPage pPage) {
        JSONObject pageConfig = pageService.pageConfig(pPage.getTid());
        ResponseUtils.responseJsonFilter(response, pageConfig);
    }

    /**
     * 读取数据表
     */
    @PostMapping("/readDataBaseTable")
    public void readDataBaseTable(HttpServletResponse response) throws SQLException, ClassNotFoundException {
        DataBase dataBase = new DataBase(driver, url, user, pwd);
        List<JavaData> tableList = dataBase.getTableList(null);
        JSONArray arr = new JSONArray();
        for (int i = 0; i < tableList.size(); i++) {
            JavaData javaData = tableList.get(i);
            JSONObject json = new JSONObject();
            json.put("database", javaData.getDatabase());
            json.put("tablenote", javaData.getTablenote());
            json.put("table", javaData.getTable());
            JSONArray fields = new JSONArray();
            List<JavaColumn> javacolumns = javaData.getJavacolumns();
            if(null != javacolumns){
                for(int j=0;j<javacolumns.size();j++){
                    JavaColumn java = javacolumns.get(j);
                    VueJson vueJson = TypeTools.javaColumn2VueJson(java);
                    PField field = new PField();
                    field.setTitle(vueJson.getTitle());
                    field.setKey(vueJson.getKey());
                    field.setLength(vueJson.getLength());
                    field.setIsrequire(vueJson.getIsrequire()?1:0);
                    field.setJavatype(vueJson.getJavatype());
                    field.setIswhere(vueJson.getIswhere()?1:0);
                    field.setJavawhere(vueJson.getJavawhere());
                    field.setIsshowcolumn(vueJson.getIsshowcolumn()?1:0);
                    field.setComponentsid(vueJson.getControltype());
                    field.setFormat(vueJson.getFormat());
                    field.setMessage(vueJson.getMessage());
                    fields.add(field);
                }
            }
            json.put("fields", fields);
            arr.add(json);
        }
        ResponseUtils.responseJsonFilter(response, arr);
    }
















}


