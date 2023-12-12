# DI 入门案例

## 思路分析
1. 基于 IoC 管理 bean
2. Service 中使用 new 形式创建的 Dao 对象是否保留？（否）
3. Service 中需要的 Dao 对象如何进入到 service 中？（提供方法）
4. Service 与 Dao 间的关系如何描述？（配置）

## 步骤
1. 删除使用 new 的形式创建对象的代码
2. 提供依赖对象对应的 setter 方法

```java
public class BookServiceImpl implements BookService {
    //5.删除业务层中使用new的方式创建的dao对象
    private BookDao bookDao;
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

3. 配置 service 与 dao 之间的关系

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<!--    导入 spring 的坐标 spring-context 对应版本是 5.2.10.RELEASI -->
<!--    配置 bean-->
<!--    id 属性表示给 bean 起名字-->
<!--    class 属性表示给 bean 定义类型-->
    <bean id="bookDao" class="org.zwh.dao.impl.BookDaoImpl"/>
    <bean id="bookService" class="org.zwh.service.impl.BookServiceImpl">
        <!--7.配置server与dao的关系-->
        <!--property标签表示配置当前bean的属性
        name属性表示配置哪一个具体的属性
        ref属性表示参照哪一个bean-->
        <property name="bookDao" ref="bookDao"/>
    </bean>
</beans>
```








