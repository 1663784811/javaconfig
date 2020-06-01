package cn.cyyaw.config.code.controller;


import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.config.code.service.PageService;
import cn.cyyaw.config.table.table.entity.PPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping("/config/page")
@RestController
public class PageController {

    @Autowired
    private PageService pageService;

    @ResponseBody
    @PostMapping("/pageConfig")
    public void pageConfig(HttpServletResponse response, PPage pPage) {
        Map<String, Object> pageConfig = pageService.pageConfig(pPage.getTid());
        ResponseUtils.responseJsonFilter(response, pageConfig);
    }


}
