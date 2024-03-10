# docker 安装 Redis

## docker 下载安装并运行 redis 镜像

```bash
docker run -p 6379:6379 --name redis  -d redis redis-server --requirepass "123456"
```

- "123456" 是密码
- 后续使用名为 redis 的容器

## docker 运行 redis 容器

```bash
docker start redis
docker exec -it redis /bin/bash
```

## 连接 redis 数据库

```bash
redis-cli
```

## 授权密码

```bash
auth 123456
```

## docker 停止 redis 容器

```bash
docker stop redis
```











