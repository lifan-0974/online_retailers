package com.wronggo.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.wronggo.service.SendSmsService;
import com.wronggo.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendSmsController {
    @Autowired
    private SendSmsService sendSmsService;

    @RequestMapping(value = "/sendSms")
    public R sendSms() {
        boolean sendFlag = sendSmsService.sendSms("18690042735","123456");
        if (sendFlag){
            return R.ok();
        }else {
            return R.error().message("发送消息{123456}失败！");
        }
    }
}
