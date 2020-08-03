package cn.cyyaw.config.code.controller;


import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.config.code.service.PageService;
import cn.cyyaw.config.code.tools.database.DataBase;
import cn.cyyaw.config.code.tools.entity.java.JavaData;
import cn.cyyaw.config.table.table.entity.PPage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public void pageConfig(HttpServletResponse response,@RequestBody PPage pPage) {
        JSONObject pageConfig = pageService.pageConfig(pPage.getTid());
        ResponseUtils.responseJsonFilter(response, pageConfig);
    }

    /**
     * 读取数据表
     */
    @GetMapping("/readDataBaseTable")
    public void readDataBaseTable(HttpServletResponse response) throws SQLException, ClassNotFoundException {
        DataBase dataBase = new DataBase(driver, url, user, pwd);
        List<JavaData> tableList = dataBase.getTableList(null);




        ResponseUtils.responseJsonFilter(response, tableList);
    }

}
