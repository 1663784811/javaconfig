package cn.cyyaw.config.home.controller;

import cn.cyyaw.config.home.service.CommonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/home/common")
@RestController
public class CommonController {


    @Autowired
    private CommonService commonService;

    /**
     * 通用查询
     *
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(String _code ) {
        JSONObject json = new JSONObject();
        json.put("_code", _code);
        return commonService.query(json);
    }

    /**
     * 通用修改或添加
     */
    @RequestMapping("/update")
    public Map<String, Object> update() {
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", commonService.update(json));
        map.put("msg", "ok");
        return map;
    }

    /**
     * 通用删除
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete() {
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", commonService.delete(json));
        map.put("msg", "ok");
        return map;
    }
}
