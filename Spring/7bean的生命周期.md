# bean 生命周期

生命周期：从创建到消亡的完整过程

bean 生命周期：bean 从创建到销毁的整体过程 

## bean 生命周期控制

在bean 创建后到销毁前做一些事情

### 方法一

提供生命周期控制方法

```java
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("book dao save ...");
    }

    //表示bean初始化对应的操作
    public void init(){
        System.out.println("init...");
    }

    //表示bean销毁前对应的操作
    public void destroy(){
        System.out.println("destory...");
    }
}
```

配置生命周期控制方法

```xml
<bean id="bookDao"
    name="dao"
    class="org.zwh.dao.impl.BookDaoImpl"
    init-method="init"
    destroy-method="destroy">
</bean>
```

### 方法二

接口控制

实现 InitializingBean DisposableBean 接口

```java
public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {

    private BookDao bookDao;
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }

    public void setBookDao(BookDao bookDao) {
        System.out.println("set .....");
        this.bookDao = bookDao;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("service destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }
}
```

配置生命周期控制方法

```xml
<bean id="bookDao"
    name="dao"
    class="org.zwh.dao.impl.BookDaoImpl">
</bean>
```

## bean生命周期

初始化容器

1. 创建对象（内存分配）
2. 执行构造方法
3. 执行属性注入（set操作）
4. 执行 bean 初始化方法

使用 bean
1. 执行业务操作

关闭/销毁容器
1. 执行bean销毁方法


## bean 销毁时机
容器关闭前触发 bean 的销毁

关闭容器方式
- 手工关闭容器
    - ConfigurableApplicationContext 接口 close() 操作
- 注册关闭钩子，在虚拟机退出前先关闭容器再退出虚拟机
    - ConfigurableApplicationContext 接口 registershutdownHook() 操作
