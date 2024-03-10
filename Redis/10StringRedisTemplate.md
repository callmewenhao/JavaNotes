# StringRedisTemplate

- 为了节省内存空间，我们可以不使用 JSON 序列化器来处理 value，而是统一使用 String 序列化器，要求只能存储 String 类型的 key 和 value。当需要存储 Java 对象时，手动完成对象的序列化和反序列化。
- 因为存入和读取时的序列化及反序列化都是我们自己实现的，SpringDataRedis 就不会将 class 信息写入 Redis 了
- 这种用法比较普遍，因此 SpringDataRedis 就提供了 RedisTemplate 的子类：StringRedisTemplate，它的key 和 value 的序列化方式默认就是 String 方式。源码如下：

```java
public class StringRedisTemplate extends RedisTemplate<String, String> {
    public StringRedisTemplate() {
        this.setKeySerializer(RedisSerializer.string());
        this.setValueSerializer(RedisSerializer.string());
        this.setHashKeySerializer(RedisSerializer.string());
        this.setHashValueSerializer(RedisSerializer.string());
    }
```

省去了我们自定义 RedisTemplate 的序列化方式的步骤（可以将之前配置的 RedisConfig 删除掉），而是直接使用：

```java
@Test
void stringTest() throws JsonProcessingException {
    //创建对象
    User user = new User("张三", 18);
    //手动序列化
    String json = mapper.writeValueAsString(user);
    //写入数据
    stringRedisTemplate.opsForValue().set("userdata", json);
    //获取数据
    String userdata = stringRedisTemplate.opsForValue().get("userdata");
    //手动反序列化
    User readValue = mapper.readValue(userdata, User.class);
    System.out.println(readValue);
}
```

存入 Redis 中是这样的：

```java
{
  "name": "张三",
  "age": 18
}
```


