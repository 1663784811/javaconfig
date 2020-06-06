package cn.cyyaw.config.home.controller;

import cn.cyyaw.config.home.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/home/common")
@RestController
public class CommonController {


    @Autowired
    private CommonService commonService;

    /**
     * 通用查询
     * @return
     */
    @RequestMapping("/query")
    public Map<Object, Object> query() {
        Map<Object, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", commonService.query());
        map.put("msg","ok");
        return map;
    }

    /**
     * 通用修改或添加
     */
    @RequestMapping("/update")
    public Map<Object, Object> update(){
        Map<Object, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", commonService.update());
        map.put("msg","ok");
        return map;
    }

    /**
     * 通用删除
     */
    @RequestMapping("/delete")
    public Map<Object, Object> delete(){
        Map<Object, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", commonService.delete());
        map.put("msg","ok");
        return map;
    }
}
