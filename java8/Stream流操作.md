# Stream 流操作

## Stream 流的必要性

实际开发中，项目中多数数据源都来自于 Mysql，Oracle 等。但现在数据源可以更多了，有 MongDB，Radis 等，而这些 NoSQL 的数据就需要 Java 层面去处理。

Stream 和 Collection 集合的区别: Collection 是一种静态的内存数据结构，而 Stream 是有关计算的。前者是主要面向内存，存储在内存中后者主要是面向 CPU，通过 CPU 实现计算。

## 注意
- Stream 自己不会存储元素
- Stream 不会改变源对象。相反，他们会返回一个持有结果的新 Stream。
- Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行

## Stream 的操作三个步骤
- 创建 Stream：一个数据源（如：集合、数组），获取一个流
- 中间操作：一个中间操作链，对数据源的数据进行处理
- 终止操作(终端操作)：一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用

## Stream 实例化

**四**种实例化的方式

### 通过集合创建

Methods inherited from interface java.util.Collection: parallelStream, stream 
- `default Stream<E> stream()`：返回一个顺序 Stream 与此集合作为其来源。 
- `default Stream<E> parallelStream()`：返回可能并行的 Stream 与此集合作为其来源。 该方法允许返回顺序流。 

### 通过数组创建

Java8 中的 Arrays 的静态方法 stream() 可以获取数组流
- `public static <T> Stream<T> stream(T[] array)`: 返回一个流

有三种重载形式：
- `public static IntStream stream(int[] array)`
- `public static LongStream stream(long[] array)`
- `public static DoubleStream stream(double[] array)`

## Java8 Stream 流简介

Java8 中有两大最为重要的改变。第一个是 lambda 表达式；另外一个则是 Stream API。

Stream APl(java.util.stream) 把真正的函数式编程风格引入到 Java 中。这是目前为止对 Java 类库最好的补充，因为 Stream API 可以极大提供 Java 程序员的生产力，让程序员写出高效率、干净、简洁的代码。

Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。 

使用 Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。也可以使用 Stream API来并行执行操作。简言之，Stream API 提供了一种高效且易于使用的处理数据的方式。

