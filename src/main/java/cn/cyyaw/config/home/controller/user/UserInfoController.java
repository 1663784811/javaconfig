package cn.cyyaw.config.home.controller.user;


import cn.cyyaw.config.home.service.UUserService;
import cn.cyyaw.config.table.table.entity.user.UUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home/user")
@RestController
public class UserInfoController {

    @Autowired
    private UUserService uUserService;


    /**
     * 获取我的基本信息
     */
    @PostMapping("/myBaseInfo")
    public UUser myBaseInfo(){


        UUser user = uUserService.findId(1);
        return user;
    }


    /**
     * 获取个人中心信息：  余额、购物卡 、积分 、优惠券
     */
    public void getUserCenterInfo(){

    }

}
