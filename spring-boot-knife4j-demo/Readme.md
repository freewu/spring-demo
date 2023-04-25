## 运行
```
# 访问 
    
    http://127.0.0.1:8082/doc.html
```

## Swagger2注解
```
@Api: 定义接口分组名称
@ApiImplicitParam: 单个参数注释
@ApiImplicitParams:多个参数注释
@ApiModel:实体类定义
@ApiModelProperty:实体属性定义
@ApiOperation:接口定义
@ApiParam:参数注释
@ApiResponse:响应码
@ApiResponses:多个响应码
```

## Swagger3	注解说明
```
@Tag(name = “接口类描述”)	Controller 类
@Operation(summary =“接口方法描述”)	Controller 方法
@Parameters	Controller 方法
@Parameter(description=“参数描述”)	Controller 方法上 @Parameters 里Controller 方法的参数
@Parameter(hidden = true) @Operation(hidden = true)@Hidden	排除或隐藏api
@Schema	DTO实体DTO实体属性
```



