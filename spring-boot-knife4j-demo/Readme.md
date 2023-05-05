## 运行
```
# 访问 
    
    http://127.0.0.1:8082/doc.html
```

## Swagger2注解
```
# @Api: 定义接口分组名称

    用在请求的类上，表示对类的说明
    tags 说明该类的作用，可以在UI界面上看到的注解

    @RestController
    @RequestMapping("/banner")
    @Api(tags = "banner(主页轮播)")
    public class BannerController {
        ...
    }
    
# @ApiParam:参数注释
    
    用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等）
    name：参数名
    value：参数说明
    required：是否必填
    
     // 用在方法上
    public User getUserInfo(
        @ApiParam(name="id",value="用户id",required=true) Long id,
        @ApiParam(name="username",value="用户名") String username) {
        ....
    }
    
    // 字段说明
    @ApiModelProperty("主键ID")
    @TableId
    @ApiParam(required = true,name="id",value="用户id")
    private String id; 

# @ApiImplicitParam: 单个参数注释 & @ApiImplicitParams:多个参数注释

    @ApiImplicitParams：用在请求的方法上，表示一组参数说明
    @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面

        name：参数名
        value：参数的汉字说明、解释
        required：参数是否必须传
        paramType：参数放在哪个地方
        
            header --> 请求参数的获取：@RequestHeader
            query --> 请求参数的获取：@RequestParam
            path（用于restful接口）--> 请求参数的获取：@PathVariable
            body（不常用）
            form（不常用）

        dataType：参数类型，默认String，其它值dataType="Integer"
        defaultValue：参数的默认值


    @ApiImplicitParams({
        @ApiImplicitParam(name="mobile",value="手机号",required=true,paramType="form"),
        @ApiImplicitParam(name="password",value="密码",required=true,paramType="form"),
        @ApiImplicitParam(name="age",value="年龄",required=true,paramType="form",dataType="Integer")
    })
    @RequestMapping("/register")
    public String register() {
        ...
    }

# @ApiOperation:接口定义

    用在请求的方法上，说明方法的用途、作用
    
        value="说明方法的用途、作用"
        notes="方法的备注说明"
    
    @ApiOperation(value="用户注册",notes="手机号、密码都是必输项，年龄随边填，但必须是数字")
    @RequestMapping("/register")
    public String register() {
        ...
    }

# @ApiResponse:响应码 & @ApiResponses:多个响应码
    @ApiResponses 用在请求的方法上，表示一组响应
    @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
    
        code：数字，例如400
        message：信息，例如"请求参数没填好"
        response：抛出异常的类
    
    @ApiResponses({
        @ApiResponse(code=400,message="请求参数缺失"),
        @ApiResponse(code=500,message="注册处理内部处理失败")
    })
    @ApiOperation(value="用户注册",notes="手机号、密码都是必输项，年龄随边填，但必须是数字")
    @RequestMapping("/register")
    public String register() {
        ...
    }
    
# @ApiModel:实体类定义 & @ApiModelProperty:实体属性定义

    @ApiModel 用于响应类上，表示一个返回响应数据的信息
    这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用 @ApiImplicitParam 注解进行描述的时候）
    
       value            String      类名              为模型提供备用名称
       description      String      ""               提供详细的类描述 
       parentClassw<?>  parent      Void.class       为模型提供父类以允许描述继承关系
       discriminatory   String      ""               支持模型继承和多态，使用鉴别器的字段的名称，可以断言需要使用哪个子类型
       subTypes         Class<?>[]  {}               从此模型继承的子类型数组
       reference        String      ""               指定对应类型定义的引用，覆盖指定的任何其他元数据
    
    @ApiModelProperty：用在属性上，描述响应类的属性等
        
        value           String      ""               属性简要说明
        name            String      ""               运行覆盖属性的名称。重写属性名称
        allowableValues String      ""               限制参数可接收的值，三种方法，固定取值，固定范围
        access          String      ""               过滤属性，参阅: io.swagger.core.filter.SwaggerSpecFilter
        notes           String      ""               目前尚未使用 (可以自己拓展)
        dataType        String      ""               参数的数据类型，可以是类名或原始数据类型，此值将覆盖从类属性读取的数据类型
        required        boolean     false            是否为必传参数,false:非必传参数; true:必传参数
        position        int         0                允许在模型中显示排序属性
        hidden          boolean     false            隐藏模型属性，false:不隐藏; true:隐藏
        example         String      ""               属性的示例值
        readOnly        boolean     false            指定模型属性为只读，false:非只读; true:只读
        reference       String      ""               指定对应类型定义的引用，覆盖指定的任何其他元数据
        allowEmptyValue boolean     false            允许传空值，false:不允许传空值; true:允许传空值

    @Data
    @ApiModel("UserModel")
    public class UserModel extends BaseEntity {
    
        @ApiModelProperty(value="用户名称",required = true)
        private String username;
    
        @ApiModelProperty("密码")
        private String password;
    
        @ApiModelProperty("年龄")
        private int age;
    }
    
# @ApiIgnore 忽略在swagger文档中显示

    @PostMapping
    @ApiOperation("add")
    public ResponseResult<List<ClientArticleComment>> add(
        @RequestBody @Validated ClientArticleComment comment,
        // 因为用户Id是在token里获取的，不需要传，不需要在接口文档里显示
        @JwtToken @ApiIgnore String userId) {
    }

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



