# 事务相关配置

![t7](figure/t7.png)

Spring 的事务默认回滚的情况：
- error，如：内存溢出
- 运行时异常：NullPointException

遇到其他的异常不会回滚，如：IOException

案例：转账业务追加日志

![t8](figure/t8.png)

![t9](figure/t9.png)

log 事务不应该加入事务 T，而应该是一个新的事务，这样才能实现出现异常，log 仍能够添加

![t10](figure/t10.png)

## 事务的传播行为

![t11](figure/t11.png)

![t12](figure/t12.png)




