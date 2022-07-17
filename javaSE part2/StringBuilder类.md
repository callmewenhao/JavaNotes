# StringBuilder 类

## 基本介绍

- 一个可变的字符序列。此类提供一个与 StringBuffer 兼容的 API，但不保证同步。该类被设计用作 StringBuffer 的一个简易替换，用在字符串缓冲区被**单个线程**使用的时候。如果可能，建议优先采用该类，因为在大多数实现中，它比 StringBuffer 要快。

- 在 StringBuilder 上的主要操作是 append 和 insert 方法，可重载这些方法，以接受任意类型的数据。

## 常用方法

StringBuilder 和 StringBuffer 均代表可变的字符序列，方法是样的，所以使用和 StringBuffer 一样

注意：StringBuilder 的方法，没有做互斥的处理，即没有 synchronized 关键字，因此在单线程的情况下使用 StringBuilder

## String、StringBuffer 和 StringBuilder 的比较

1) StringBuilder 和 StringBuffer 非常莫似，均代表可变的字符序列，而且方法也一样
2) String：不可变字符序列，效率低，但是复用率高
3) StringBuffer：可变字符序列、效率较高（增删）、线程安全
4) StringBuilder：可变字符序列、效率最高、线程不安全

结论：如果对**字符串**做大量修改，不要使用 String，要使用 StringBuffer

## 使用原则

1. 如果字符串存在大量的修改操作，一般使用 StringBuffer 或 StringBuilder

2. 如果字符串存在大量的修改操作，并在单线程的情况，使用 StringBuilder

3. 如果字符串存在大量的修改操作，开仕多线程的情况，使用 StringBuffer

4、如果我们字符串很少修改，被多个对象引用，使用String，比如配置信息等

StringBuilder 的方法使用和 StringBuffer 一样，不再说
