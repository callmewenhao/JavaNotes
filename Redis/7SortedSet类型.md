# SortedSet 类型

Redis 的 SortedSet 是一个可排序的 set 集合，与 Java 中的 TreeSet 有些类似，但底层数据结构却差别很大。

SortedSet 中的每一个元素都带有一个 score 属性，可以基于 score 属性对元素排序，底层的实现是一个跳表（SkipList）加 hash 表。

SortedSet 具备下列特性：
- 可排序
- 元素不重复
- 查询速度快

因为 SortedSet 的可排序特性，经常被用来实现排行榜这样的功能。

SortedSet的常见命令有：

| 命令                         | 描述                                                     |
|------------------------------|----------------------------------------------------------|
| ZADD key score member        | 添加一个或多个元素到 sorted set，如果已经存在则更新其 score 值 |
| ZREM key member              | 删除 sorted set 中的一个指定元素                             |
| ZSCORE key member            | 获取 sorted set 中的指定元素的 score 值                        |
| ZRANK key member             | 获取 sorted set 中的指定元素的排名                           |
| ZCARD key                    | 获取 sorted set 中的元素个数                                 |
| ZCOUNT key min max           | 统计 score 值在给定范围内的所有元素的个数                   |
| ZINCRBY key increment member| 让 sorted set 中的指定元素自增，步长为指定的 increment 值     |
| ZRANGE key min max           | 按照 score 排序后，获取指定排名范围内的元素                 |
| ZRANGEBYSCORE key min max   | 按照 score 排序后，获取指定 score 范围内的元素               |
| ZDIFF、ZINTER、ZUNION        | 求差集、交集、并集                                         |





注意：所有的排名默认都是升序，如果要降序则在命令的 Z 后面添加 REV 即可，例如：
- 升序获取 sorted set 中的指定元素的排名：`ZRANK key member`
- 降序获取 sorted set 中的指定元素的排名：`ZREVRANK key memeber`

