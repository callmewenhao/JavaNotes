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

### 通过 Stream 类的 of() 方法

> 用的比较少

- `static <T> Stream<T> of(T t)`: Returns a sequential Stream containing a single element.

- `static <T> Stream<T> of(T... values)`: Returns a sequential ordered stream whose elements are the specified values.

```java
var s = Stream.of(1, 2, 3, 4, 5);
```

### 创建无限流

> 了解一下，当需要造一些数据时使用，比较省事

- 迭代：`static <T> Stream<T>	iterate(T seed, UnaryOperator<T> f)`，如果中间操作不加限制，程序就会一直迭代下去

- 生成：`static <T> Stream<T>	generate(Supplier<T> s)`，同样的，如果中间操作不加限制，程序就会一直迭代下去

## intermediate operations（中间操作）

### 筛选与切片

- `filter(Predicate p)`：接收 lambda 从流中排除某些元素。只留下 p 为返回值为真的元素
- `distinct()`：筛选，通过流所生成元素的 `hashCode()` 和 `equals()` 去除重复元素
- `limit(long maxSize)`：截断流，使其元素不超过给定数量
- `skip(long n)`：跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 `limit(n)` 互补

```java
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] a = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
        IntStream s = Arrays.stream(a);
        s.filter(x -> x % 2 == 0).distinct().forEach(System.out::print);  // 注意，流 s 一旦执行终止操作，就无法再使用了
        System.out.println();  // 2468

        // s.filter(x -> x % 2 == 0).distinct().limit(2).forEach(System.out::print);
        // System.out.println();  // 24
        
        String[] str = {"1", "2", "2", "3", "3"};
        Arrays.stream(str).distinct().forEach(System.out::print); // 123
    }
};
```

### 映射

- `map(Function f)`：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
- `mapTolnt(TolntFunction f)`：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream
- `mapToDouble(ToDoubleFunction f)`：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream
- `mapToLong(ToLongFunction f)`：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream
- `flatMap(Function f)`：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流

```java
List<String> list = Arrays.asList("aa", "bb", "cc", "dd"); // a list view of the specified array
list.stream().map(String::toUpperCase).forEach(System.out::print); // AABBCCDD
```

### 排序



## terminal operation（终端操作）


