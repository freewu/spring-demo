package org.bluefrog.controller;

import org.bluefrog.domain.PublishRequest;
import org.bluefrog.service.MQTTPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublishController {
    @Autowired
    private MQTTPublish publish;

    @RequestMapping(value = "/publish", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendMessage(@RequestBody PublishRequest publishRequest) {
        System.out.println(publishRequest.toString());
        //return publishRequest.toString();
        try {
            publish.publish(publishRequest.getQos(), publishRequest.isRetained(), publishRequest.getTopic(), publishRequest.getMessage());
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}
