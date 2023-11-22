# MyBatis核心配置文件

mybatis-config.xml 文件，具体查看[文档](https://mybatis.org/mybatis-3/zh/configuration.html)

MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：
- configuration（配置）
- properties（属性）
- settings（设置）
- typeAliases（类型别名）
- typeHandlers（类型处理器）
- objectFactory（对象工厂）
- plugins（插件）
- environments（环境配置）
- environment（环境变量）
- transactionManager（事务管理器）
- dataSource（数据源）
- databaseIdProvider（数据库厂商标识）
- mappers（映射器）

注意：配置各个标签时，需要遵守前后顺序。约束大于配置


