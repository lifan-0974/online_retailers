package com.wronggo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class QueueController {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/send")
    public void send(String text){
        Map map=new HashMap<>();
        map.put("mobile", "13900001111");
        map.put("content", "恭喜获得10元代金券");
        jmsMessagingTemplate.convertAndSend("hp_map",map);
    }
}
