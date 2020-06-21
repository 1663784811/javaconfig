package cn.cyyaw.config.admin.controller;

import cn.cyyaw.config.admin.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shiro/admin/chat")
@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    /**
     * 获取用户聊天列表
     */
    @GetMapping(value = "/userList")
    public void userList(){
        // chatService.userList();

    }
}
