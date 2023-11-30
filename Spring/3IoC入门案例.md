# IoC 入门案例

## 思路分析
- 管理什么（Service 与 Dao）
- 如何将被管理的对象告知 IoC 容器（配置）
- 被管理的对象交给 IoC 容器，如何获取到 IoC 容器（接口）
- IoC 容器得到后，如何从容器中获取 bean（接口方法）
- 使用 Spring 导入哪些坐标（pom.xml）

## 步骤

1. 导入 Spring 坐标

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.10.RELEASE</version>
</dependency>
```

2. 定义 Spring 管理的类(接口)

```java
public interface BookService {
    public void save();
}

public class BookServiceImpl implements BookService {
    //5.删除业务层中使用new的方式创建的dao对象
    private BookDao bookDao = new BookDaoImpl();

    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
    //6.提供对应的set方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
```

3. 创建 Spring 配置文件，配置对应类作为 Spring 管理的 bean

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<!--    导入 spring 的坐标 spring-context 对应版本是 5.2.10.RELEASI -->
<!--    配置 bean-->
<!--    id 属性表示给 bean 起名字-->
<!--    class 属性表示给 bean 定义类型-->
    <bean id="bookDao" class="org.zwh.dao.impl.BookDaoImpl"/>
    <bean id="bookService" class="org.zwh.service.impl.BookServiceImpl"/>
</beans>
```

4. 初始化 IoC 容器（Spring 核心容器 / Spring 容器），通过容器获取 bean

```java
// 获取 IoC 容器
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
// 获取 bean
// BookDao bookDao = (BookDao) ctx.getBean("bookDao");
// bookDao.save();
BookService bookService = (BookService) ctx.getBean("bookService");
bookService.save();
```