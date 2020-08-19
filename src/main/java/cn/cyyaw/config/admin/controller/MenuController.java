package cn.cyyaw.config.admin.controller;


import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.config.admin.UserUtil;
import cn.cyyaw.config.admin.service.MenuService;
import cn.cyyaw.config.table.table.entity.tadmin.TAdmin;
import cn.cyyaw.config.table.table.entity.tadmin.TPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 菜单
 */
@RequestMapping("/shiro/admin/menu")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单列表
     */
    @PostMapping("/getAdminMenu")
    public void getAdminMenu(HttpServletResponse response){
        TAdmin admin = UserUtil.getAdminInfo();
        List<TPower> list = menuService.getAdminMenu(admin.getTid());
        ResponseUtils.responseJsonFilter(response, list, null);
    }

}
