# Map 接口

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

