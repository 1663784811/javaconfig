package cn.cyyaw.config.code.controller;


import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.config.code.service.PageService;
import cn.cyyaw.config.table.table.entity.PPage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/config/page")
@RestController
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 获取页面设置
     */
    @PostMapping("/pageConfig")
    public void pageConfig(HttpServletResponse response,@RequestBody PPage pPage) {
        JSONObject pageConfig = pageService.pageConfig(pPage.getTid());
        ResponseUtils.responseJsonFilter(response, pageConfig);
    }


}
