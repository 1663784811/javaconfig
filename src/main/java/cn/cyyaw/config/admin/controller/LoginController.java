package cn.cyyaw.config.admin.controller;

import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.admin.service.LoginService;
import cn.cyyaw.config.config.shiro.ShiroEnum;
import cn.cyyaw.config.table.table.entity.tadmin.TAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 登录 ， 注册 ， 验证码
 */
@RequestMapping("/shiro/admin")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public Map login(@RequestBody HashMap<String, String> map) {
        //第一步：验证验证码
        //第二步：登录
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
            subject.logout();
        UsernamePasswordToken token = new UsernamePasswordToken(map.get("account"), map.get("password"), "127.0.0.1");
        subject.login(token);
        HashMap<String, Object> loginMap = new HashMap<String, Object>();
        loginMap.put("token", subject.getSession().getId());
        loginMap.put("data", subject.getSession().getAttribute(ShiroEnum.USERINFO));
        loginMap.put("message", "登录成功");
        loginMap.put("success", true);
        return loginMap;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public Map logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
            subject.logout();
        HashMap<String, Object> loginMap = new HashMap<>();
        loginMap.put("success", true);
        loginMap.put("message", "退出成功");
        return loginMap;
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Map register(@RequestBody TAdmin tAdmin) {
        //第一步：验证
        //插入数据库
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) subject.logout();
        TAdmin register = loginService.register(tAdmin);
        UsernamePasswordToken token = new UsernamePasswordToken(register.getAccount(), tAdmin.getPassword());
        subject.login(token);
        HashMap<String, Object> loginMap = new HashMap<>();
        loginMap.put("token", subject.getSession().getId());
        loginMap.put("message", "注册成功");
        loginMap.put("success", true);
        loginMap.put("data", subject.getSession().getAttribute(ShiroEnum.USERINFO));
        return loginMap;
    }
    /**
     * 验证码图片
     */
    @GetMapping("/checkcodeImg/{uuid}")
    public void checkcodeImg(@PathVariable String uuid, HttpServletResponse response) throws IOException {
        // ImageIO.write(WhyImageUtil.getImageFromCode(100, 100, uuid), "jpg", response.getOutputStream());
    }
    /**
     * 验证码
     */
    @GetMapping("/checkcode")
    public String checkcode(HttpServletRequest request) {
        String randomString = StringUtilWHY.getRandomString(4);
        String uuid = StringUtilWHY.getUUID();
        Map<String, Object> hashtable = new Hashtable<String, Object>();
        hashtable.put("code", randomString);
        hashtable.put("time", new Date());
        request.setAttribute(uuid, hashtable);
        return uuid;
    }
}
