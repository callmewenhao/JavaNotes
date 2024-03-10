# String 类型

String 类型，也就是字符串类型，是 Redis 中最简单的存储类型

其 value 是字符串，不过根据字符串的格式不同，又可以分为3类
- string：普通字符串
- int：整数类型，可以做自增、自减操作
- float：浮点类型，可以做自增、自减操作

不管是哪种格式，底层都是字节数组形式存储，只不过是编码方式不同，字符串类型的最大空间不能超过512M

## String 的常用命令

| 命令         | 描述                                                      |
|--------------|-----------------------------------------------------------|
| SET          | 添加或者修改一个已经存在的 String 类型的键值对              |
| GET          | 根据 key 获取 String 类型的 value                              |
| MEST         | 批量添加多个 String 类型的键值对                            |
| MGET         | 根据多个key获取多个 String 类型的 value                      |
| INCR         | 让一个整形的 key 自增1                                      |
| INCRBY       | 让一个整形的 key 自增并指定步长值，例如：incrby num 2，让 num 值自增2 |
| INCRBYFLOAT  | 让一个浮点类型的数字自增并指定步长值                      |
| SETNX        | 添加一个 String 类型的键值对，前提是这个 key 不存在，否则不执行，可以理解为真正的新增 |
| SETEX        | 添加一个 String 类型的键值对，并指定有效期                  |

## Key 结构

Redis 没有类似 MySQL 中 Table 的概念，那么我们该如何区分不同类型的 Key 呢？

例如：需要存储用户、商品信息到 Redis，有一个用户的 id 是 1，有一个商品的 id 恰好也是 1，如果此时使用 id 作为 key，那么就回冲突，该怎么办？

我们可以通过给 key 添加前缀加以区分，不过这个前缀不是随便加的，有一定的规范 Redis 的 key 允许有多个单词形成层级结构，多个单词之间用 `:` 隔开，格式如下：

```java
项目名:业务名:类型:id
```

这个格式也并非是固定的，可以根据自己的需求来删除/添加词条，这样我们就可以把不同数据类型的数据区分开了，从而避免了 key 的冲突问题
例如我们的项目名叫 reggie，有 user 和 dish 两种不同类型的数据，我们可以这样定义 key
user 相关的 key：`reggie:user:1`
dish 相关的 key：`reggie:dish:1`

如果 value 是一个 Java 对象，例如一个 User 对象，则可以将对象序列化为 JSON 字符串后存储

| KEY             | VALUE                                   |
|-----------------|-----------------------------------------|
| reggie:user:1   | {"id":1, "name": "Jack", "age": 21}     |
| reggie:dish:1   | {"id":1, "name": "鲟鱼火锅", "price": 4999} |

并且在Redis的桌面客户端中，也会以相同前缀作为层次结构，让数据看起来层次分明，关系清晰

## 命令格式

设置 k-v

```bash
set key value
```

查看 k 对应的 v

```bash
get key
```

清除 k 和对应的 v

```bash
move key [idx]  # 数据库下标
```


追加字符串，如果 k 不存在就相当于 set key

```bash
append key "xxxxx"
```

获取字符串长度

```bash
strlen key
```

存储 int 时，也是以字符串的形式存的，但是可以实现 ++ 和 --

```bash
set x 0
incr x  # 加 1
incrby x 10 # 加 10
decr x # 减 1
decrby x 10 # 减 10
```

获取子字符串

```bash
getrange key start_idx end_idx
```

替换子字符串

```bash
setrange key offset value  # 将从 offset 起始，长度和 value 一样的部分替换为 value
```

- 如果存在就设置 setex

- 如果不存在就设置 setnx

- mset 和 mget 批量设置和获取

- getset key value 获取不到就使用 value 进行 set





