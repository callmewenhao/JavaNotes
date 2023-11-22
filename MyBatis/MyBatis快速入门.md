# MyBatis 快速入门

需求：查询 user 表中所有数据
1. 创建 user 表，添加数据
2. 创建模块，导入坐标
3. 编写 MyBatis 核心配置文件 -> 替换连接信息，解决硬编码问题
4. 编写 SQL 映射文件 -> 统一管理 SQL 语句，解决硬编码问题
5. 编码
    - 定义 POJO 类
    - 加载核心配置文件，获取 SqlSessionFactory 对象
    - 获取 SqlSession 对象，执行 SQL 语句
    - 释放资源

具体查看 idea 项目代码































