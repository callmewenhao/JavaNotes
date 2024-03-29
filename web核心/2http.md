# HTTP

概念：HyperText Transfer Protocol，超文本传输协议，规定了浏览器和服务器之间数据传输的规则

HTTP 协议特点：
1. 基于TCP协议：面向连接，安全
2. 基于请求-响应模型：一次请求对应一次响应
3. HTTP 协议是无状态的协议：对于事务处理没有记忆能力。每次请求-响应都是独立的
    - 缺点：多次请求间不能共享数据。Java 中使用会话技术（Cookie、Session）来解决这个问题
    - 优点：速度快

## http 请求数据格式

请求数据分为 3 部分：
1. 请求行：请求数据的第一行。其中 GET 表示请求方式，/ 表示请求资源路径，HTTP/1.1 表示协议版本
2. 请求头：第二行开始，格式为 key:value 形式。
3. 请求体：POST 请求的最后一部分，存放请求参数

常见的 HTTP 请求头：
- Host：表示请求的主机名
- User-Agent：浏览器版本，例如 Chrome 浏览器的标识类似 Mozilla/5.0 Chrome/79
- Accept：表示浏览器能接收的资源类型，如 `text/*` `image/*` 或者 `*/*` 表示所有
- Accept-Language：表示浏览器偏好的语言，服务器可以据此返回不同语言的网页 
- Accept-Encoding：表示浏览器可以支持的压缩类型，例如 gzip deflate 等

GET 请求和 POST 请求区别：
- GET 请求参数在请求行中，没有请求体。POST 请求参数在请求体中
- GET 请求参数大小有限制，POST 没有


## http 响应数据格式

响应数据分为 3 部分：
1. 响应行：响应数据的第一行。其中 HTTP/1.1 表示协议版本，200 表示响应状态码，OK 表示状态码描述
2. 响应头：第二行开始，格式为 key:value 形式 
3. 响应体：最后一部分。存放响应数据

## 状态码大类

状态码分类
- 1xx：响应中。临时状态码，表示请求已经接受，告诉客户端应该继续请求或者如果它已经完成则忽略它
- 2xx：成功。表示请求已经被成功接收，处理已完成
- 3xx：重定向。重定向到其它地方：它让客户端再发起一个请求以完成整个处理
- 4xx：客户端错误。处理发生错误，责任在客户端，如：客户端的请求一个不存在的资源，客户端未被授权，禁止访问等
- 5xx：服务器端错误。处理发生错误，责任在服务端，如：服务端抛出异常，路由出错，HITP 版本不支持等

## 常见的响应状态码

- **200 OK** 客户端请求成功，即处理成功，这是我们最想看到的状态码
- 302 Found 指示所请求的资源已移动到由 Location 响应头给定的 URL，浏览器会自动重新访问到这个页面
- 304 Not Modified 告诉客户端，你请求的资源至上次取得后，服务端并未更改，你直接用你本地缓存吧。隐式重定向
- 400 Bad Request 客户端请求有语法错误，不能被服务器所理解
- 403 Forbidden 服务器收到请求，但是拒绝提供服务，比如：没有权限访问相关资源
- **404 Not Found** 请求资源不存在，一般是 URL 输入有误，或者网站资源被删除了
- **500 Internal Server Error** 服务器发生不可预期的错误。服务器出异常了，赶紧看日志去吧
- 503 Service Unavailable 服务器尚未准备好处理请求，服务器刚刚启动，还未初始化好
- 511 Network Authentication Required 客户端需要进行身份验证才能获得网络访问权限


