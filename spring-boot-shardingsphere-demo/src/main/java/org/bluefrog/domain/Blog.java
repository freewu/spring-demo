package org.bluefrog.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Blog {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime updateTime;
    private String userName;
}