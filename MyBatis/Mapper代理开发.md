# Mapper 代理开发

Mapper 代理开发是企业中最为主流的开发方式

步骤：
- 编写接口方法
- 编写 SQL
- 执行方法

快速入门中执行 sql 语句时还是有硬编码的问题

```java
// 2. 获取 SqlSession 对象 用它来执行 SQL 语句
List<User> users = null;
// 👇这种 try() 的写法可以自动释放资源
// 3. 执行 sql 语句
try (SqlSession session = sqlSessionFactory.openSession()) {
    users = session.selectList("user.selectAll");  // 命名空间.sql语句id
}
```

Mapper 代理开发的目的
- 解决原生方式中的硬编码
- 简化后期执行SQL

```java
// 获取接口代理对象
UserMapper userMapper = sqlSession.getMapper(UserMapper.class)
// 执行方法，其实就是执行 sql 语句
List<User> users = userMapper.selectAll();
```

使用 Mapper 代理方式完成入门案例
1. 定义与 SQL 映射文件同名的 Mapper 接口，并且将 Mapper 接口和 SQL 映射文件放置在同一目录下
2. 设置 SQL 映射文件的 namespace 属性为 Mapper 接口全限定名
3. 在 Mapper 接口中定义方法，方法名就是 SQL 映射文件中 sql 语句的 id，并保持参数类型和返回值类型一致
4. 编码
    - 通过 SqlSession 的 getMapper 方法获取 Mapper 接口的代理对象
    - 调用对应方法完成 SQL 的执行

细节：如果 Mapper 接口名称和 SQL 映射文件名称相同，并在同一目录下，则可以使用包扫描的方式简化 SQL 映射文件的加载





