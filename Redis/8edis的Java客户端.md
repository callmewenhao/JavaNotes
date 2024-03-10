# Redis 的 Java 客户端

目前主流的 Redis 的 Java 客户端有三种
- Jedis 和 Lettuce：这两个主要是提供了 Redis 命令对应的 API，方便我们操作 Redis，而 SpringDataRedis 又对这两种做了抽象和封装，因此我们后期会直接以 SpringDataRedis 来学习。
Redisson：是在 Redis 基础上实现了分布式的可伸缩的 java 数据结构，例如 Map、Queue 等，而且支持跨进程的同步机制：Lock、Semaphore 等待，比较适合用来实现特殊的功能需求。

## Jedis客户端

使用 Jedis 的步骤

1. 导入Jedis的maven坐标

```java
<!--jedis-->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.7.0</version>
</dependency>
<!--单元测试-->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.7.0</version>
    <scope>test</scope>
</dependency>
```

2. 建立连接。新建一个单元测试类

```java
private Jedis jedis;

@BeforeEach
void setUp() {
    //1. 建立连接
    jedis = new Jedis("101.42.225.160", 6379);
    //2. 设置密码
    jedis.auth("root");
    //3. 选择库
    jedis.select(0);
}
```

3. 测试

```java
@Test
void testString(){
    jedis.set("name","Kyle");
    String name = jedis.get("name");
    System.out.println("name = " + name);
}

@Test
void testHash(){
    jedis.hset("reggie:user:1","name","Jack");
    jedis.hset("reggie:user:2","name","Rose");
    jedis.hset("reggie:user:1","age","21");
    jedis.hset("reggie:user:2","age","18");
    Map<String, String> map = jedis.hgetAll("reggie:user:1");
    System.out.println(map);
}
```

4. 释放资源

```java
@AfterEach
void tearDown(){
    if (jedis != null){
        jedis.close();
    }
}
```

## 连接池

Jedis 本身是线程不安全的，并且频繁的创建和销毁连接会有性能损耗，因此我们推荐大家使用 Jedis 连接池代替 Jedis 的直连方式。

新建一个 com.blog.util，用于存放我们编写的工具类

**但后面我们使用 SpringDataRedis 的时候，可以直接在 yml 配置文件里配置这些内容**

```java
public class JedisConnectionFactory {

    private static JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);
        // 创建连接池对象，参数：连接池配置、服务端ip、服务端端口、超时时间、密码
        jedisPool = new JedisPool(poolConfig, "101.42.225.160", 6379, 1000, "root");
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
```

之后我们的测试类就可以修改为如下:


```java
@SpringBootTest
class RedisTestApplicationTests {

    private Jedis jedis = JedisConnectionFactory.getJedis();

    @Test
    void testString(){
        jedis.set("name","Kyle");
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash(){
        jedis.hset("reggie:user:1","name","Jack");
        jedis.hset("reggie:user:2","name","Rose");
        jedis.hset("reggie:user:3","name","Kyle");
        jedis.hset("reggie:user:1","age","21");
        jedis.hset("reggie:user:2","age","18");
        jedis.hset("reggie:user:3","age","18");
        Map<String, String> map = jedis.hgetAll("reggie:user:1");
        System.out.println(map);
    }

    @AfterEach
    void tearDown(){
        if (jedis != null){
            jedis.close();
        }
    }
}
```





