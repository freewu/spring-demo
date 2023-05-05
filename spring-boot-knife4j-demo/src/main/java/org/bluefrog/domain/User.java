package org.bluefrog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@ApiModel("用户模型")
public class User {
    @ApiModelProperty("用户编号")
    private int id;
    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
