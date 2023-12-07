# AOP 总结

- 概念：AOP（Aspect Oriented Programming）面向切面编程，一种编程范式
- 作用：在不惊动原始设计的基础上为方法进行功能增强
- 核心概念
    - 代理（Proxy）：SpringA0P 的核心本质是采用代理模式实现的
    - 连接点（JoinPoint）：在 SpringAOP 中，理解为任意方法的执行
    - 切入点（Pointcut）：匹配连接点的式子，也是具有共性功能的方法描述
    - 通知（Advice）：若干个方法的共性功能，在切入点处执行，最终体现为一个方法
    - 切面（Aspect）：描述通知与切入点的对应关系
- 目标对象（Target）：被代理的原始对象成为目标对象




