# Set 类型

Redis 的 Set 结构与 Java 中的 HashSet 类似，可以看做是一个 value 为 null 的 HashMap。因为也是一个hash 表，因此具备与 HashSet 类似的特征：
- 无序
- 元素不可重复
- 查找快
- 持交集、并集、差集等功能

## Set的常见命令

| 命令              | 描述                             |
|-------------------|----------------------------------|
| SADD key member …| 向 set 中添加一个或多个元素         |
| SREM key member …| 移除 set 中的指定元素               |
| SCARD key        | 返回 set 中元素的个数               |
| SISMEMBER key member| 判断一个元素是否存在于 set 中      |
| SMEMBERS         | 获取 set 中的所有元素               |
| SINTER key1 key2 …| 求 key1 与 key2 的交集               |
| SUNION key1 key2 …| 求 key1 与 key2 的并集               |
| SDIFF key1 key2 …| 求 key1 与 key2 的差集               |


