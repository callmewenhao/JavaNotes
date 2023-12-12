# bean 的实例化

## 构造方法实例化

bean 本质上就是对象，创建 bean 使用**无参的构造方法**完成

构造方法

```java
public class BookDaoImpl implements BookDao {
    public BookDaoImpl() {
        System.out.println("book dao constructor is running ....");
    }

    public void save() {
        System.out.println("book dao save ...");
    }
}
```

配置

```xml
<bean id="bookDao" name="dao" class="org.zwh.dao.impl.BookDaoImpl" scope="prototype"/>
```

无参构造方法如果不存在，将抛出异常 `BeancreationException`

## 静态工厂实例化

静态工厂

```java
//静态工厂创建对象
public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        System.out.println("factory setup....");
        return new OrderDaoImpl();
    }
}
```

配置

```xml
<bean id="orderDao" class="org.zwh.factory.OrderDaoFactory" factory-method="getOrderDao"/>
```

## 实例工厂实例化

实例工厂

```java
public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
```

配置

```xml
<bean id="userFactory" class="org.zwh.factory.UserDaoFactory"/>
<bean id="userDao" factory-bean="userFactory" factory-method="getUserDao"/>
```

## FactoryBean<T> 接口实例化

以后的框架都在使用这种方法和 Spring 打交道

FactoryBean 类

```java
//FactoryBean创建对象
public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    //代替原始实例工厂中创建对象的方法
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    // 返回创建对象的类型信息
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    // 控制是否单例对象 通常都是单例对象
    @Override
    public boolean isSingleton() {
        return false;
    }
}
```

配置

```xml
<bean id="userDao" class="org.zwh.factory.UserDaoFactoryBean"/>
```








