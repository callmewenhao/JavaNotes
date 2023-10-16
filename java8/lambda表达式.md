# lambda 表达式

形式：`(参数列表) -> { lambda 体 }`

lambda 表达式本质是：接口的实例（万物皆对象），没了接口，lambda 也就没意义了，借助接口存在

书写的 lambda 表达式的样式（参数、返回值）要和接口里面的那个函数一致，例如接口：`Interface ToIntFunction<T>`，它里面有个函数：`int applyAsInt(T value)`，在书写 lambda 表达式时应注意参数和返回值一致

## 无参，无返回值

```java
Runnable r = () -> { System.out.println("I love Java!"); };
r.run();  // 调用接口定义的方法
```

## 多个参数，无返回值

- 数据类型可以省略，编译器会进行推断
- 只有一个参数，() 可以省略
- lambda 体的 {} 可以省略

```java
Consumer<String> c = str -> System.out.println(str);
c.accept("I love Java!");  // 调用接口定义的方法
```

## 自定义返回值

```java
Comparator<Integer> c = (o1, o2) -> { return o1.compareTo(o2) }; 
c.compare(1, 10);
```

## 默认返回值

```java
Comparator<Integer> c = (o1, o2) -> o1.compareTo(o2); 
c.compare(1, 10);
```

