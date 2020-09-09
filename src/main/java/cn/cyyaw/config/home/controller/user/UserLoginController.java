package cn.cyyaw.config.home.controller.user;

import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.home.service.UUserService;
import cn.cyyaw.config.table.table.entity.user.UUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RequestMapping("/home/login")
@RestController
public class UserLoginController {


    @Autowired
    private UUserService uUserService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Map login(@RequestBody HashMap<String, String> map) {
        //第一步：验证验证码
        //第二步：登录
        String account = map.get("account");
        String password = map.get("password");
        UUser uUser = uUserService.findByAccountAndPassword(account, password);
        HashMap<String, Object> loginMap = new HashMap<String, Object>();
        loginMap.put("token", "123456");
        loginMap.put("data", uUser);
        loginMap.put("message", "登录成功");
        loginMap.put("success", true);
        return loginMap;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public Map logout() {
        HashMap<String, Object> loginMap = new HashMap<>();
        loginMap.put("success", true);
        loginMap.put("message", "退出成功");
        return loginMap;
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Map register(@RequestBody UUser uUser) {
        //第一步：验证
        //插入数据库
        uUser.setTid(StringUtilWHY.getUUID());
        uUser.setCreatetime(new Date());
        UUser user = uUserService.save(uUser);
        HashMap<String, Object> loginMap = new HashMap<>();
        loginMap.put("message", "注册成功");
        loginMap.put("success", true);
        loginMap.put("data", user);
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
