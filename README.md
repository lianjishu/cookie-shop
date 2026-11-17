# Cookie Shop SSM

基于 Spring + SpringMVC + MyBatis (SSM) 框架开发的轻量级电商网站系统。

## 项目简介

Cookie Shop 是一个功能完善的网上购物平台，采用经典的 SSM 架构构建。系统提供完整的商品展示、购物车、订单管理、用户中心等功能，并包含后台管理系统用于管理商品、订单、用户和商品分类。

## 技术栈

- **后端框架**: Spring + SpringMVC + MyBatis
- **数据库**: MySQL
- **前端模板**: JSP + jQuery + Bootstrap
- **支付集成**: 支付宝
- **项目管理**: Maven

## 功能模块

### 前台功能
- **用户模块**: 用户注册、登录、密码修改、收货地址管理
- **商品模块**: 商品列表、商品搜索、商品详情、热门推荐、新品推荐
- **购物车模块**: 添加商品、修改数量、删除商品
- **订单模块**: 提交订单、订单列表、订单详情、订单取消
- **评价模块**: 商品评价、评分

### 后台管理
- **商品管理**: 商品增删改查、商品推荐设置（轮播、热销、新品）
- **订单管理**: 订单列表、订单状态修改、订单删除
- **用户管理**: 用户增删改查、密码重置
- **分类管理**: 商品分类增删改查

## 项目结构

```
cookie-shop-ssm/
├── cookie-shop-common/          # 公共模块
│   └── src/main/java/
│       └── io/github/lianjishu/
│           ├── handler/         # 全局异常处理
│           ├── properties/      # 配置属性类
│           └── utils/           # 工具类
├── cookie-shop-pojo/            # 实体类模块
│   └── src/main/java/
│       └── io/github/lianjishu/entity/
│           ├── Cart.java        # 购物车
│           ├── Goods.java       # 商品
│           ├── Order.java       # 订单
│           ├── OrderItem.java   # 订单项
│           ├── Review.java      # 评价
│           ├── Type.java        # 分类
│           ├── User.java        # 用户
│           ├── Recommend.java   # 推荐
│           └── Page.java        # 分页
├── cookie-shop-web/             # Web模块
│   └── src/main/
│       ├── java/
│       │   └── io/github/lianjishu/
│       │       ├── config/      # 配置类
│       │       ├── controller/  # 控制器
│       │       ├── interceptor/ # 拦截器
│       │       ├── mapper/      # MyBatis Mapper接口
│       │       └── service/     # 业务服务
│       ├── resources/           # MyBatis映射文件
│       ├── webapp/              # Web资源
│       │   ├── admin/           # 后台管理页面
│       │   ├── css/             # 样式文件
│       │   ├── js/              # JavaScript文件
│       │   └── *.jsp            # JSP页面
│       └── test/                # 测试类
└── pom.xml                      # Maven配置
```

## 快速开始

### 环境要求
- JDK 1.8+
- Maven 3.x
- MySQL 5.7+

### 配置步骤

1. **创建数据库**

```bash
mysql -u root -p < sql/cookieshop.sql
```

2. **配置数据库连接**

复制配置示例文件并修改数据库连接信息：

```bash
cp cookie-shop-web/src/main/resources/jdbc.properties.example \
   cookie-shop-web/src/main/resources/jdbc.properties
```

编辑 `jdbc.properties` 文件，配置正确的数据库用户名和密码：

```properties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/cookieshop?useSSL=false&serverTimezone=UTC
jdbc.username=root
jdbc.password=your_password
```

3. **配置支付宝（如需使用支付功能）**

编辑 `cookie-shop-common/src/main/java/io/github/lianjishu/utils/AlipayConfig.java`，配置支付宝相关参数：

```java
public static String APPID = "your_app_id";
public static String RSA_PRIVATE_KEY = "your_private_key";
public static String ALIPAY_PUBLIC_KEY = "alipay_public_key";
public static String notify_url = "http://yourdomain/alipay/notify";
public static String return_url = "http://yourdomain/alipay/return";
```

4. **编译运行**

```bash
mvn clean package
```

将生成的 WAR 包部署到 Tomcat 服务器，或者使用 Maven 直接运行：

```bash
mvn tomcat7:run -pl cookie-shop-web
```

5. **访问系统**

- 前台地址: http://localhost:8080/
- 后台地址: http://localhost:8080/admin/

## 默认账号

- **管理员账号**: admin / admin
- **普通用户**: 需要自行注册

## 项目截图

系统包含以下页面：
- 首页轮播图和商品展示
- 商品列表页（支持分类筛选）
- 商品详情页
- 购物车页面
- 订单确认和提交
- 用户中心
- 后台管理系统

## 许可证

MIT License