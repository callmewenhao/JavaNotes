# 常用命令

1. 查看数据库中的 k

```bash
keys *
```

2. 查看 k 是否存在

```bash
exists key
```

3. 查看 k 对应的 v 的类型

```bash
type key
```

4. 查看 k 的剩余时间，以秒为单位

```bash
ttl key
```

5. 用于在 key 存在时删除 key

```bash
del key
```

# 其他命令

切换数据库

```bash
select 0-15 # 一共 16 个数据库
```

清除当前数据库中的缓存

```bash
flushdb
```

清除所有数据库的缓存

```bash
flushall
```

设置 k 的过期时间

```bash
expire key time # 时间单位默认是 s
```





