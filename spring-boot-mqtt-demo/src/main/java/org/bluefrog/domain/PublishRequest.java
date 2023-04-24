package org.bluefrog.domain;

import lombok.Data;

@Data
public class PublishRequest {
    int qos;
    boolean retained;
    String topic;
    String message;
}
