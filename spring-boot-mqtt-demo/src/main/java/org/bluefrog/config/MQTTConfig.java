package org.bluefrog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(MQTTConfig.PREFIX)
public class MQTTConfig {
    // 指定配置文件 application.yml 中的属性名前缀
    public static final String PREFIX = "mqtt";
    private String host;
    private String clientId;
    private String username;
    private String password;
    private boolean cleanSession;
    private String defaultTopic;
    private int timeout;
    private int keepalive;
    private int connectionTimeout;
}