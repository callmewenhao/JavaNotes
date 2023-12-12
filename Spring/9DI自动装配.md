# 依赖自动装配

IoC 容器根据 bean 所依赖的资源在容器中自动查找并注入到 bean 中的过程称为自动装配

自动装配方式：
- 按类型(常用)
- 按名称
- 按构造方法
- 不启用自动装配

配置中使用 bean 标签 autowire 属性设置自动装配的类型

```xml
<bean id="bookService" class="org.zwh.service.impl.BookServiceImpl" autowire="byType"/>

<bean id="bookService" class="org.zwh.service.impl.BookServiceImpl" autowire="byName"/>
```

依赖自动装配特征

- 自动装配用于引用类型依赖注入，不能对简单类型进行操作
- 使用按类型装配时 (byType) 必须保障容器中相同类型的 bean 唯一， 推荐使用
- 使用按名称装配时 (byName) 必须保障容器中具有指定名称的 bean，因变量名与配置耦合，不推荐使用
- 自动装配优先级低于 setter 注入与构造器注入，同时出现时自动装配配置失效





