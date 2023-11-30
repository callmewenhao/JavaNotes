# 注解开发定义bean

使用 `@Component` 定义 bean

```java
@Component("bookDao")
public class BookDaoImpl implements BookDao {
    // 实现类
}

@Component // 不写 bean 名称 后面使用类型加载
public class BookServiceImpl implements BookService {
    // 实现类
}
```

核心配置文件中通过组件扫描加载 bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    
    <context:component-scan base-package="org.zwh"/>
</beans>
```

Spring 提供 `@Component` 注解的三个衍生注解 
- `@Controller`：用于表现层 bean 定义
- `@Service`：用于业务层 bean 定义
- `@Repository`：用于数据层 bean 定义

```java
@Repository("bookDao" )
public class BookDaoImpl implements BookDao {}

@Service
public class BookServiceImpl implements BookService {}
```

# 纯注解开发

Spring3.0 升级了纯注解开发模式，使用 **Java 类替代配置文件**，开启了 Spring 快速开发赛道

Java 类代替 Spring 核心配置文件

```java
@Configuration  // 对应配置文件
@ComponentScan("org.zwh") // 对应扫描
public class SpringConfig {}
```

- `@Configuration` 注解用于设定当前类为配置类
- `@ComponentScan` 注解用于设定扫描路径，此注解只能添加一次，多个数据请用数组格式

```java
@ComponentScan({"fcom.itheima.service", "com.itheima.dao"})
```

读取 Spring 核心配置文件初始化容器对象切换为**读取 java 配置类**初始化容器对象

```java
// 加载配置文件初始化容器
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

//加载配置类初始化容器
ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class),
```

# bean 管理

使用 `@Scope` 定义 bean 作用范围

```java
@Repository
@Scope("singleton")
public class BookDaoImpl implements BookDao {}
```

使用 `@PostConstruct`、`@PreDestroy` 定义 bean 生命周期

````java
@Repository("bookDao")
@Scope // 默认单例 or prototype 非单例
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("book dao save ...");
    }

    @PostConstruct
    public void init() {
        System.out.println("init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy...");
    }
}
```
