# Bean 加载与控制

## Controller 加载控制与业务 bean 加载控制

因为功能不同，如何避免 Spring 错误的加载到 SpringMVC 的 bean？
- 加载 Spring 控制的 bean 的时候排除掉 SpringMVC 控制的 bean

![14](figure/m14.png)

- 建议采用第一种方法
- 第二种方法在 springboot 中有使用
- 第三种可能偶尔使用

## 方法二

![15](figure/m15.png)

## 目前加载 spring 和 springMVC 的方式

![16](figure/m16.png)

## 简化开发

![17](figure/m17.png)


