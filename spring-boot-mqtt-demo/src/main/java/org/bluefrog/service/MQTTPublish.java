package org.bluefrog.service;

import org.bluefrog.config.MQTTConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

// mqtt 发布者服务
@Service
public class MQTTPublish {

    /**
     * 客户端对象
     */
    private MqttClient client;

    // 统一配置
    @Autowired
    private MQTTConfig config;

    private String clientId = "publish-id";

    /**
     * 在bean初始化后连接到服务器
     */
    @PostConstruct
    public void init() {
        connect();
    }

    /**
     * 客户端连接服务端
     */
    public void connect() {
        try{
            //创建MQTT客户端对象
            client = new MqttClient(config.getHost(),clientId,new MemoryPersistence());
            //连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 是否清空session，设置false表示服务器会保留客户端的连接记录（订阅主题，qos）,客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
            // 设置为true表示每次连接服务器都是以新的身份
            options.setCleanSession(false);
            // 设置连接用户名
            options.setUserName(config.getUsername());
            // 设置连接密码
            options.setPassword(config.getPassword().toCharArray());
            // 设置超时时间，单位为秒
            options.setConnectionTimeout(config.getConnectionTimeout());
            // 设置心跳时间 单位为秒，表示服务器每隔 1.5*20秒的时间向客户端发送心跳判断客户端是否在线
            options.setKeepAliveInterval(config.getKeepalive());
            // 设置遗嘱消息的话题，若客户端和服务器之间的连接意外断开，服务器将发布客户端的遗嘱信息
            options.setWill("willTopic",(clientId + "与服务器断开连接").getBytes(),0,false);
            // 设置回调
            MQTTPublishCallBack callback = new MQTTPublishCallBack();
            callback.setClientId(clientId);
            client.setCallback(callback);

            client.connect(options);
        } catch(MqttException e){
            e.printStackTrace();
        }
    }

    // 发布消息
    public void publish(int qos,boolean retained,String topic,String message) {
        MqttMessage mqttMessage = new MqttMessage();
        // QoS 0 至多一次 这一级别会发生消息丢失或重复，消息发布依赖于底层TCP/IP网络。即：<=1
        //      p ---- PUBLISH ---> b
        // QoS 1 最少一次 承诺消息将至少传送一次给订阅者
        //      p ---- PUBLISH ---> b
        //      p <--- PUBACK ----- b
        // QoS 2 只有一次 保证消息仅传送到目的地一次。为此，带有唯一消息 ID 的消息会存储两次，首先来自发送者，然后是接收者。QoS 级别 2 在网络中具有最高的开销，因为在发送方和接收方之间需要两个流
        //      p ---- PUBLISH ---> b
        //      p <---- PUBREC ---- b
        //      b <---- PUBREL ---- s
        //      b ----- PUBCOMP --> s
        mqttMessage.setQos(qos);
        // # retained = true
        //  当消息发布到MQTT服务器时，我们需要保留最新的消息到服务器上，以免订阅时丢失上一次最新的消息；
        //  当订阅消费端服务器重新连接MQTT服务器时，总能拿到该主题最新消息， 这个时候我们需要把retained设置为true;
        // # retained = false
        //  当消息发布到MQ服务器时，我们不需要保留最新的消息到服务器上；
        //  当订阅消费端服务器重新连接MQTT服务器时，不能拿到该主题最新消息，只能拿连接后发布的消息，这个时候我们需要把  retained设置为false;
        mqttMessage.setRetained(retained);
        mqttMessage.setPayload(message.getBytes());
        // 主题的目的地，用于发布/订阅信息
        MqttTopic mqttTopic = client.getTopic(topic);
        // 提供一种机制来跟踪消息的传递进度
        // 用于在以非阻塞方式（在后台运行）执行发布是跟踪消息的传递进度
        MqttDeliveryToken token;
        try {
            // 将指定消息发布到主题，但不等待消息传递完成，返回的 token 可用于跟踪消息的传递状态
            // 一旦此方法干净地返回，消息就已被客户端接受发布，当连接可用，将在后台完成消息传递。
            token = mqttTopic.publish(mqttMessage);
            token.waitForCompletion();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
