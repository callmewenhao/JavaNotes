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

- sorted()，默认排序，使用默认排序接口
- sorted(Comparator<? super T> comparator)，自定义排序

```java
// 默认排序
int[] a = {9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1};
IntStream s = Arrays.stream(a);  // 注意 IntStream 无法进行自定义排序
s.distinct().sorted().forEach(System.out::print);
System.out.println();  // 123456789
```

## terminal operation（终端操作）

### 匹配与查找

- `allMatch(Predicate p)`：检查是否匹配所有元素，返回 boolean
- `anyMatch(Predicate p)`：检查是否至少匹配一个元素，返回 boolean
- `noneMatch(Predicate p)`：检查是否没有匹配所有元素，返回 boolean
- `findFirst()`：返回第一个元素
- `findAny()`：返回当前流中的任意元素
- `count()`：求元素个数
- `max(Comparator<? super T> comparator)`：返回最大值
- `min(Comparator<? super T> comparator)`：返回最小值
- `forEach(Consumer<? super T> action)`：内部迭代，处理每个元素

### 归约

- `reduce(T iden, BinaryOperator b)`：可以将流中元素反复结合起来，得到一个值。返回 `T` 
- `reduce(BinaryOperator b)`：可以将流中元素反复结合起来，得到一个值。返回 `Optional<T>`

```java
// 归约求和举例
int[] a = {1, 2, 3, 4, 5};
int s = Arrays.stream(a).reduce(0, Integer::sum); // 0 表示初始值
System.out.println(s);  // 15
```

备注：map 和 reduce 的连接通常称为 **map-reduce** 模式，因 Google 用它来进行网络搜索而出名

### 收集

- `collect(Collector c)`：将流转换为其他形式。接收一个 Collector 接口的实现，用于给 Stream 中元素做汇总的方法

```java
Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
List<Integer> collect = Stream.of(a).filter(x -> x % 2 == 0).collect(Collectors.toList());
List<Integer> collect = Stream.of(a).filter(x -> x % 2 == 0).toList();
Set<Integer> collect = Stream.of(a).filter(x -> x % 2 == 0).collect(Collectors.toSet());
```

Collector 接口中方法的实现决定了如何对流执行收集的操作（如收集到 List、Set、Map)

另外，Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例，具体方法与实例如下表：





