package org.bluefrog.service;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.kerberos.KerberosTicket;

@Configuration
@Data
public class MQTTPublishCallBack implements MqttCallback {
//    @Value("${mqtt.client-id}")
//    private String clientId;

    private String clientId;

    // 与服务器断开的回调
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("publish-id" + " 与服务器断开连接");
    }

    // 消息到达的回调
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

    }

    // 消息发布成功的回调
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        IMqttAsyncClient client = iMqttDeliveryToken.getClient();
        System.out.println(client.getClientId() + " 发布消息成功！");
    }
}
