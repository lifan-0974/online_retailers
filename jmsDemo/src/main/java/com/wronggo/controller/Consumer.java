package com.wronggo.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Consumer {

    @JmsListener(destination="hp_map")
    public void readMessage(Map map){
        System.out.println(map);
    }

}