package org.bluefrog.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private LocalDateTime createTime;
}