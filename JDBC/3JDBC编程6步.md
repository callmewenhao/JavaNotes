# JDBC 编程 6 步

背过这六步代码：
- 第一步：注册驱动（作用：告诉 Java 程序，即将要连接的是哪个品牌的数据库）
- 第二步：获取连接（表示 jvm 的进程和数据库进程之间的通道打开了，这属于进程之间的通信，使用完之后一定要关闭）
- 第三步：获取数据库操作对象（专门执行 sql 语句的对象）
- 第四步：执行 SQL 语句
- 第五步：处理查询结果集（只有当第四步执行的是 select 语句的时候，才有这第五步处理查询结果集）
- 第六步：释放资源（使用完资源之后一定要关闭资源。Java 和数据库属于进程间的通信，开启之后一定要关闭）

## 注册驱动

常用方式：

非常经典的一行代码

```java
Class.forName("com.mysql.cj.jdbc.Driver");  // 利用反射机制加载类 而且我们不需要返回值
```

原因在于：这个 `com.mysql.cj.jdbc.Driver` 类中有一段静态代码块，这个代码块里面含有注册代码。所以只需要加载这个类就可以执行这段静态代码，完成数据库注册。**反射** 就是最好的加载这个类的选择！

```java
static {
    try {
        DriverManager.registerDriver(new Driver());
    } catch (SQLException var1) {
        throw new RuntimeException("Can't register driver!");
    }
}
```


下面这种方式一般不用

文档 `java.sql` 中有一个类：DriverManager，这类有一个方法：`registerDriver(Driver driver)`，其中的 `Driver` 是指这个接口：`java.sql.Driver`

MySQL 的这个 `com.mysql.cj.jdbc` 包中有一个 **Driver** 类，继承了 `java.sql.Driver` 接口

```java
import java.sql.Driver;
import java.sql.DriverManager;

try {
    Driver driver = new com.mysql.cj.jdbc.Driver();
    DriverManager.registerDriver(driver);
} catch (SQLException e) {
    throw new RuntimeException(e);
}
```

## 获取连接

DriverManager 这个类有这样一个静态方法：`getConnection` 连接数据库

```java
public static Connection getConnection(String url,
                                       String user,
                                       String password)
                                throws SQLException
```

- url："jdbc:mysql://localhost:3306/sql_store"。url：统一资源定位符，网络中某个资源的绝对路径，url 包含：协议、IP 地址、端口、资源名称
    - 协议：jdbc:mysql
    - IP 地址：localhost 或者 127.0.0.1 都是本机 IP。指定哪台机器即可 
    - 端口：3306
    - 资源名称：数据库名称 sql_store
- user：用户 root
- password：密码 123456

## 获取数据库操作对象

`Connection` 接口有一个 `createStatement` 方法，创建一个 `Statement` 接口实现类对象来将 SQL 语句发送到数据库

```java
Statement createStatement()
                   throws SQLException
```

## 执行 SQL 语句

`Statement` 接口含有方法：
- `int executeUpdate(String sql)`：执行 DML 语句（insert、delete、update），返回影响的记录条数
- `ResultSet executeQuery(String sql)`：执行 SQL 语句，返回一个 ResultSet 对象（结果集合）
    - `next()` 方法：将光标指向下一行。如果新行有效，则返回 true；否则 false
    - `getString("查询结果中的列名" or "列下标")` 方法：返回这条记录对应列值的 String 值
    - `getInt()` 方法：返回 int 值，要求这一列在 SQL 中的类型就是 int
    - `getDouble()` 方法：返回 double 值，要求这一列在 SQL 中的类型就是 double

```java
String sql = "SELECT * FROM orders";  // SQL 语句不用写分号
ResultSet resultSet = statement.executeQuery(sql);

while(resultSet.next()){
    String order_id = resultSet.getString("order_id");
    String customer_id = resultSet.getString("customer_id");
    String order_date = resultSet.getString("order_date");
    String status = resultSet.getString("status");
    String comments = resultSet.getString("comments");
    String shipped_date = resultSet.getString("shipped_date");
    String shipper_id = resultSet.getString("shipper_id");

    System.out.println(order_id + " " + customer_id + " " + order_date + " "
        + status + " " + comments + " " + shipped_date + " " + shipper_id);
}
```

## 释放资源

为了保证资源一定释放，在 fina11y 语何块中关闭资源，并且要遵循从小到大依次关闭

```sql
resultSet.close();
statement.close();
connection.close();
```



