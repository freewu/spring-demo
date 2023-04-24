package org.bluefrog.controller;


import org.bluefrog.service.MQTTSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubscribeController {
    @Autowired
    private MQTTSubscribe client;

//    @Value("${spring.mqtt.client.id}")
//    private String clientId;

    @RequestMapping("/connect")
    @ResponseBody
    public String connect(){
        client.connect();
        return client.getClientId() + " 连接到服务器";
    }

    @RequestMapping("/dis-connect")
    @ResponseBody
    public String disConnect(){
        client.disConnect();
        return client.getClientId() + "与服务器断开连接";
    }
}

