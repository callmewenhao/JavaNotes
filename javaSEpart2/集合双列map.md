[TOC]

# Map 接口

**继承实现关系图**

<div align="center"> 
    <img src="images/c1.png" width=500>
</div>

## Map 接口实现类的特点

注意：这里讲的是 JDK8 的 Map 接口特点

1. Map 与 Collection 并列存在。用于保存具有映射关系的数据：Key-Value

2) Map 中的 key 和 value 可以是任何引用类型的数据，会封装到 HashMap$Node 对象中
3) Map 中的 key 不允许重复，原因和 HashSet 一样，前面分析过源码
4) Map 中的 value 可以重复
5) Map 的 key 可以为 null，value 也可以为 null，注意 key 为 null，只能有一个 value为 null，可以多个
6) 常用 String 类作为 Map 的 key
7) key 和 value 之间存在单向一对一关系，即通过指定的 key 总能找到对应的 value

8. Map 存放数据的 key-value 示意图，一对 k-v 是放在一个 Node 中的，有因为 Node 实现了 Entry 接口，有些书上也说一对 k-v 就是一个 Entry

## Map 接口常用方法

1. put：添加

2) remove：根据键删除映射关系
3) get：根据键获取值
4) size：获取元素个数
5) isEmpty：判断个数是否为
6) clear：清除
7) containsKey：查找键是否存在

```java
public class HashMap_ {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        // put 添加or赋值
        map.put("zhao", "wenhao");
        map.put(null, "zhaowenhao");
        map.put("zhaowenhao", null);
        // remove 删除
        map.remove(null);
        // get
        String val = map.get("zhao");
        // size
        System.out.println(map.size());
        // isEmpty
        System.out.println(map.isEmpty());
        // clear 清除
//        map.clear();
        //
        System.out.println(map.containsKey("zhaowenhao"));
    }
}
```

## 遍历方法

```java
Map<String, String> map = new HashMap<>();
// put 添加or赋值
map.put("zhao", "wenhao");
map.put("z", "haowenhao");
map.put("zhaowenha", "o");
map.put("zhaowen", "hao");

// 遍历
// 方式一：先取出所有的 key 再通过 key 得到 value
Set<String> keys = map.keySet();
for (String key :keys) {
    System.out.println(map.get(key));
}
// 方式二：迭代器
Iterator<String> iterator = keys.iterator();
while(iterator.hasNext()) {
    String key = iterator.next();
    System.out.println(map.get(key));
}
// 方式三：直接取值，然后使用增强for 迭代器遍历
Collection<String> values = map.values();
for (String value : values) {
    System.out.println(value);
}
// 方式四：通过 EntrySet 遍历
Set<Map.Entry<String, String>> entries = map.entrySet();
// enhance for
for (Map.Entry<String, String> entry : entries) {
    //            Map.Entry<String, String> entry = (Map.Entry<String, String>) obj;
    System.out.println(entry.getValue());
}
// iter
Iterator<Map.Entry<String, String>> iterator = entries.iterator();
while(iterator.hasNext()) {
    String key = iterator.next().getKey();
    System.out.println(iterator.next().getValue());
}
```

## HashMap

### 基本介绍

- Map 接口的常用实现类：HashMap、Hashtable 和 Properties

- HashMap 是 Map 接口使用频率最高的实现类

- HashMap 是以 key-val 对的方式来存储数据

- key 不能重复，但是是值可以重复，允许使用 null 建和 null 值。

- 如果添加相同的 key，则会覆盖原来的 key-val，等同于修改（key不会替换，val会替换）

- 与 HashSet 一样，不保证映射的顺序，因为底层是以 hash 表的方式来存储的

- HashMap 没有实现同步，因此是线程不安全的

### HashMap 底层扩容机制

- HashMap 底层维护了 Node 类型的数组 table，默认为 null

- 当创建对象时，将加载因子（loadfactor）初始化为0.75

- 当添加 key-val 时，通过 key 的哈希值得到在 table 的索引。然后判断该索引处是否有元素，如果没有元素直接添加。如果该索引处有完素，继续判断该元素的 key 是否和准备加入的 key 相等，如果相等，则直接替换 val；如果不相等需要判断是树结构还是链表结构，做出相应处理。如果添加时发现容量不够，则需要扩容

- 第1次添加，则需要扩容 table 容量为16，临界值（threshold）为12

- 以后再扩容，则需要扩容 table 容量为原来的 2 倍，临界值为原来的 2 倍，即24，依次类推

- 在Java8中，如果一条链表的元素个数超过 TREEIFY_THRESHOLD（默认是8），并且table 的大小 >= MIN_TREEIFY_ CAPACITY（默认64），就会进行树化（红黑树）

## Hashtable

### 基本介绍

- 存放的元素是键值对：即K-V

- hashtable 的键和值都不能为 null，否则会抛出NullPointerException

- hashtable 使用方法基本上和 HashMap 一样

- hashtable 是线程安全的，hashMap 是线程不安全的

### Hashtable 和 HashMap 对比

|           | 版本 | 线程安全 | 效率 | 允许空值 |
| :-------- | :--: | :------: | :--: | :------: |
| HashMap   | 1.2  |  不安全  |  高  |   可以   |
| Hashtable | 1.0  |   安全   |  低  |  不可以  |

## Properties

### 基本介绍

- Properties 类继承自 Hashtable 类并且实现了 Map 接口，也是使用一种键值对的形式来保存数据

- 他的使用特点和 Hashtable 类似

- Properties 还可以用于从 xxx.properties 文件中，加载数据到 Properties 类对象，并进行读取和修改

- 说明：工作后 xxx.properties 文件通常作为配置文件，这个知识点在IO流举例，有兴趣可先看文章

## TreeMap

> TreeMap、TreeSet 中存放的对象，要实现 comparable 接口（重写 compareTo函数），否则报错

```java
// 匿名内部类排序
TreeMap<String, Integer> tm = new TreeMap<>(new Comparator<String>() {
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2); // 由该函数判断是否相同，如果等于0，加入不了
	}
});
// put
tm.put("a", 1);
tm.put("j", 9);
tm.put("z", 26);
tm.put("c", 5);
// print
System.out.println(tm);
```

## 开发中如何选择集合实现类

在开发中，选择什么集合实现类，主要取决于业务操作特点，然后根据集合实现类特性进行选择，分析如下

1. 先判断存储的类型（一组对象（单列）或一组键值对（双列））

2. 一组对象[单列]：Collection接口

    - 允许重复：List

        - 增删多：LinkedList （底层维护了一个双向链表）

        - 改查多：ArrayList（底层维护Object类型的可变数组）

    - 不允许重复：Set

        - 无序：HashSet（底层是 HashMap，维护了一个哈希表即（数组+链表+红黑树））

        - 排序：TreeSet

        - 插入和取出顺序一致：LinkedHashSet，维护数组+双向链表

3. 一组键值对：Map
    - 键无序：HashMap（底层是：哈希表，jdk7:数组+链表，jdk8:数组+链表+红黑树）
    - 键排序：TreeMap     
    - 键健插入和取出顺序一致：LinkedHashMap 
    - 读取文件 Properties

## 课后题

试分析 HashSet 和 TreeSet 分别如何实现去重的

1. HashSet 的去重机制：hashCode() + equals()，底层先通过存入对象，进行运算得到一个 hash 值，通过 hash 值得到对应的索引，如果发现 table 索引所在的位置，没有数据，就直接存放，如果有数据，就进行 equals 比较[遍历比较]，如果比较后，不相同,就加入，否则就不加入

2. TreeSet 的去重机制：如果你传入了一个 Comparator 匿名对象，就使用实现的 compare 去重，如果方法返回 0，就认为是相同的元素/数据，就不添加；如果你没有传入一个 Comparator 匿名对象，则以你添加的对象实现的 Compareable 接口的compareTo 去重
