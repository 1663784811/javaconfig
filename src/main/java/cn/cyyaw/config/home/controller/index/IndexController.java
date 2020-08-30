package cn.cyyaw.config.home.controller.index;


import cn.cyyaw.common.entity.SelectEntity;
import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.config.table.service.JShoolService;
import cn.cyyaw.config.table.service.WBannerService;
import cn.cyyaw.config.table.table.entity.JShool;
import cn.cyyaw.config.table.table.entity.WBanner;
import cn.cyyaw.config.table.table.entityconst.JShoolConst;
import cn.cyyaw.config.table.table.entityconst.WBannerConst;
import cn.cyyaw.jpa.JpaUtils;
import cn.cyyaw.jpa.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页
 */
@RequestMapping("/home/index")
@RestController
public class IndexController {


    @Autowired
    private WBannerService wBannerService;
    @Autowired
    private JShoolService jShoolService;

    @GetMapping(value = "/getBanner")
    public void getBanner(HttpServletResponse response, String jsonStr, SelectEntity selectEntity) {
        List<WBanner> list = wBannerService.findAll(jsonStr, selectEntity);
        ResponseUtils.responseJsonFilter(response, list, WBannerConst.filterselectColumnArr);
    }


    /**
     * 分页条件查询
     */
    @GetMapping(value = "/indexJShool")
    public void indexJShool(HttpServletResponse response,String jsonStr,  SelectEntity selectEntity) {
        PageRequest pageRequest = JpaUtils.getPageRequest(selectEntity);
        Page<JShool> page = jShoolService.findPage(jsonStr, pageRequest);
        ResponseUtils.responseJsonFilter(response, PageUtil.pageFormat(page),JShoolConst.filterselectColumnArr);
    }

}
