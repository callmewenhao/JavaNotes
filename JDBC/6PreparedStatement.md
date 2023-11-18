# PreparedStatement

## 对比 Statement 和 PreparedStatement
- Statement 存在 SQL 注入；PreparedStatement 解决 SQL 注入问题
- Statement 是编译一次，执行一次；PreparedStatement 是编译一次，执行 n 次，效率高一些
- PreparedStatement 会在编译阶段进行类型的安全检查
- 业务上 90% 的场景使用 PreparedStatement
- Statement 是对语句进行拼接；PreparedStatement 是对语句进行传值

## 使用 Statement 的场景

业务要求必须支持 SQL 注入。有些场景确实需要 SQL 注入：
- 实现页面的排序时：需要注入 DESC 或者 ASC，如果使用 PreparedStatement 会在 关键字两侧加上单引号，这样就会报错！










