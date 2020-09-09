package cn.cyyaw.config.home.controller.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home/order")
@RestController
public class OrderController {


    /**
     * 获取我的订单列表
     */
    @PostMapping("/myOrderList")
    public void myOrderList(){

    }


    /**
     * 获取订单信息
     */
    @PostMapping("/myOrderInfo")
    public void  myOrderInfo(){


    }



    /**
     * 生成订单
     */
    @PostMapping("/createOrder")
    public void  createOrder(){


    }





}
