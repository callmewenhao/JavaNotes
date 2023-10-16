# 函数式接口和lambda 表达式

## 函数式接口

函数式接口就是为了配合 lambda 表达式而出现的，都用注解 `@FunctionalInterface` 表明。

`java.util.function` 包下定义了丰富的函数式接口

四大函数式接口（函数式接口的对象的类型）：
- `Consumer<T>`：`void (T)`
- `Supplier<T>`：`T ()`
- `Function<T, R>`：`R (T)`
- `Predicate<T>`：`boolean (T)`
- 也有其他的接口，以及这 4 大接口的子接口：`IntFunction<R>`、`UnaryOperator<T>` 等等

## lambda 表达式

形式：`(参数列表) -> { lambda 体 }`

lambda 表达式本质是：函数式接口（只有一个函数的接口，会有一个 `@FunctionalInterface` 的注解）的**实例对象**（万物皆对象），没了接口，lambda 也就没意义了，借助接口存在



书写的 lambda 表达式的样式（参数、返回值）要和接口里面的那个函数一致，例如接口：`Interface ToIntFunction<T>`，它里面有个函数：`int applyAsInt(T value)`，在书写 lambda 表达式时应注意参数和返回值一致

之前写的匿名内部类，现在都可以使用 lambda 表达式代替

### 无参，无返回值

```java
Runnable r = () -> { System.out.println("I love Java!"); };
r.run();  // 调用接口定义的方法
```

### 多个参数，无返回值

- 数据类型可以省略，编译器会进行推断
- 只有一个参数，() 可以省略
- lambda 体的 {} 可以省略

```java
Consumer<String> c = str -> System.out.println(str);
c.accept("I love Java!");  // 调用接口定义的方法
```

### 自定义返回值

```java
Comparator<Integer> c = (o1, o2) -> { return o1.compareTo(o2) }; 
c.compare(1, 10);
```

### 默认返回值

```java
Comparator<Integer> c = (o1, o2) -> o1.compareTo(o2); 
c.compare(1, 10);
```

