# SpringDataRedis 客户端

SpringData 是 Spring 中数据操作的模块，包含对各种数据库的集成，其中对 Redis 的集成模块就叫做 SpringDataRedis

官网地址：https://spring.io/projects/spring-data-redis
- 提供了对不同Redis客户端的整合（Lettuce 和 Jedis）
- 提供了 RedisTemplate 统一 API 来操作 Redis
- 支持 Redis 的发布订阅模型
- 支持 Redis 哨兵和 Redis 集群
- 支持基于 Lettuce 的响应式编程
- 支持基于 JDK、JSON、字符串、Spring 对象的数据序列化及反序列化
- 支持基于 Redis 的 JDKCollection 实现

SpringDataRedis 中提供了 RedisTemplate 工具类，其中封装了各种对 Redis 的操作。并且将不同数据类型的操作API封装到了不同的类型中：

| API                              | 返回值类型       | 说明                     |
|----------------------------------|------------------|--------------------------|
| redisTemplate.opsForValue()      | ValueOperations  | 操作 String 类型数据        |
| redisTemplate.opsForHash()       | HashOperations   | 操作 Hash 类型数据          |
| redisTemplate.opsForList()       | ListOperations   | 操作 List 类型数据          |
| redisTemplate.opsForSet()        | SetOperations    | 操作 Set 类型数据           |
| redisTemplate.opsForZSet()       | ZSetOperations   | 操作 SortedSet 类型数据     |
| redisTemplate                    |                  | 通用的命令                |


## 快速入门

注：SpringBoot 已经提供了对 SpringDataRedis 的支持，使用起来非常简单

1. 导入依赖

```java
<!--redis依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<!--common-pool-->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
<!--Jackson依赖-->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
<!--lombok-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

2. 配置Redis

```java
spring:
  redis:
    host: 101.42.225.160
    port: 6379
    password: root
    lettuce:
      pool:
        max-active: 8
        max-idle: 8
        min-idle: 0
        max-wait: 100ms
```

3. 注入 RedisTemplate。因为有了SpringBoot的自动装配，我们可以拿来就用

```java
@Autowired
private RedisTemplate redisTemplate;
```

4. 编写测试方法

```java
@Test
void stringTest(){
    redisTemplate.opsForValue().set("username","David");
    String username = (String) redisTemplate.opsForValue().get("username");
    System.out.println(username);
}
```

## 自定义序列化

RedisTemplate 可以接收任意 Object 作为值写入 Redis

只不过写入前会把 Object 序列化为字节形式，默认是采用 JDK 序列化，得到的结果是这样的:

```java
\xAC\xED\x00\x05t\x00\x06\xE5\xBC\xA0\xE4\xB8\x89
```

缺点：
- 可读性差
- 内存占用较大

我们可以自定义 RedisTemplate 的序列化方式，代码如下：

1. 在com.blog.config包下编写对应的配置类

```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置连接工厂
        template.setConnectionFactory(connectionFactory);
        // 创建JSON序列化工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer();
        // 设置Key的序列化
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        // 设置Value的序列化
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        // 返回
        return template;
    }
}
```

2. 我们编写一个 User 类，并尝试将其创建的对象存入 Redis，看看是什么效果

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
}
```

3. 测试方法

```java
@Test
void stringTest(){
    redisTemplate.opsForValue().set("userdata", new User("张三", 18));
}
```

4. 这里采用了 JSON 序列化来代替默认的 JDK 序列化方式。最终结果如下：

```java
{
  "@class": "com.blog.entity.User",
  "name": "张三",
  "age": 18
}
```

- 整体可读性有了很大提升，并且能将 Java 对象自动的序列化为 JSON 字符串，并且查询时能自动把 JSON 反序列化为 Java 对象。不过，其中记录了序列化时对应的 class 名称，目的是为了查询时实现自动反序列化。这会带来额外的内存开销。

所以肯定会有更好的方法


