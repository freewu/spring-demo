package org.bluefrog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("接口统一响应")
public class ResultResponse<T> {
    @ApiModelProperty("错误码")
    private int code;
    @ApiModelProperty("错误信息")
    private String msg;
    @ApiModelProperty("返回数据")
    private T data;

    static public <T extends Object> ResultResponse<T> success(T data) {
        ResultResponse<T> r = new ResultResponse<>();
        r.setCode(0);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }
}
