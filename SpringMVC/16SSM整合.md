# SSM 整合

## 整合配置、功能模块开发、接口测试

![ssm1](figure/ssm1.png)

## 表现层与前端数据传输协议定义

![ssm2](figure/ssm2.png)

![ssm3](figure/ssm3.png)

## 设置统一返回结果类

![ssm4](figure/ssm4.png)

![ssm5](figure/ssm5.png)

![ssm6](figure/ssm6.png)

## 异常处理器

![ssm7](figure/ssm7.png)

![ssm8](figure/ssm8.png)

各个层级均出现异常，异常处理代码书写在哪一层
- 所有的异常均抛出到表现层进行处理

表现层处理异常，每个方法中单独书写，代码书写量巨大且意义不强，如何解决
- AOP思想

### 异常处理器

![ssm9](figure/ssm9.png)

![ssm10](figure/ssm10.png)

![ssm11](figure/ssm11.png)

![ssm12](figure/ssm12.png)

### 项目异常

![ssm13](figure/ssm13.png)

![ssm14](figure/ssm14.png)


























