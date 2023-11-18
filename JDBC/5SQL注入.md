# SQL 注入

## 什么是 SQL 注入

输入的密码参与了 SQL 语句的编译：

```java
String usr = "root";
String pwd = "root' or '1' = '1"
String sql = "select * from users where usr = '" + usr + "' and pwd = '" + pwd + "'";
```

主要是由于后面这条语句 `"root' or '1' = '1"` 恒成立，参与了编译，导致查询出错

## 解决 SQL 注入

保证传给 DBMS 的只有值，不参与原有 sql 语句的编译

使用接口 java.sql.PreparedStatement，他继承了 java.sql.Statement，预先对 SQL 语句的框架进行编译，然后再给 SQL 语句传值







