package cn.cyyaw.config.home.controller;


import cn.cyyaw.common.entity.SelectEntity;
import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.config.table.service.WBannerService;
import cn.cyyaw.config.table.table.entity.WBanner;
import cn.cyyaw.config.table.table.entityconst.WBannerConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/home/index")
@RestController
public class IndexController {


    @Autowired
    private WBannerService wBannerService;

    @GetMapping(value = "/getBanner")
    public void getBanner(HttpServletResponse response, String jsonStr, SelectEntity selectEntity) {
        List<WBanner> list = wBannerService.findAll(jsonStr, selectEntity);
        ResponseUtils.responseJsonFilter(response, list, WBannerConst.filterselectColumnArr);
    }


}
