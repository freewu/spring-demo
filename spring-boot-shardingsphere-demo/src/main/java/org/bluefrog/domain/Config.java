package org.bluefrog.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Config {
    private Long id;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime lastModifyTime;
}