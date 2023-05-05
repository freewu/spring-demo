package org.bluefrog.controller;

import io.swagger.annotations.*;
import org.bluefrog.domain.ResultResponse;
import org.bluefrog.domain.User;
import org.bluefrog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户接口")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="获取指定用户的信息",notes="指定用户ID为数字")
    @ApiResponses({
        @ApiResponse(code=200,message="请求成功"),
        @ApiResponse(code=400,message="请求参数缺失"),
        @ApiResponse(code=500,message="注册处理内部处理失败")
    })
    @RequestMapping(method= RequestMethod.GET,path = "/user/{id}")
    public ResultResponse<User> getOne(
            // 通过 uri path 获取参数
            @ApiParam(name="id",value="用户id",required=true,type = "int") @PathVariable("id") int id
    ) {
        return ResultResponse.success(userService.getOne(id));
    }

    @RequestMapping(method= RequestMethod.GET,path = "/users")
    public ResultResponse<List<User>> getAll() {
        return ResultResponse.success(userService.getList());
    }

}
