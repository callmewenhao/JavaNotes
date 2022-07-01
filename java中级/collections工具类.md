# Collections 工具类

> 针对 Collection、Map 接口实现类的工具类

## Collections工具类介绍

Collections 是一个操作 Set、List 和 Map 等集合的工具类

Collections 中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作

## 排序操作：（均为 static 方法）

`reverse(List)`：反转 `List` 中元素的顺序

`shuffle(List)`：对 `List` 集合元素进行随机排序

`sort(List)`：根据元素的自然顺序对指定 `List` 集合元素按升序排序

`sort(List, Comparator)`：根据指定的 `Comparator` 产生的顺序对 `List` 集合元素进行排序

`swap(List, int, int)`：将指定 `List` 集合中的 `i` 处元素和 `j` 处元素进行交换

```java
public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("1");
    list.add("a");
    list.add("wenhao");
    list.add("100");
    list.add("xiaobendan");
    
    // reverse
    Collections.reverse(list);
    System.out.println(list);
    // shuffle
    Collections.shuffle(list);
    System.out.println(list);
    // sort
    Collections.sort(list);
    // 按照指定要求排序
    Collections.sort(list, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    System.out.println(list);
    // swap
    Collections.swap(list, 0, 4);
    System.out.println(list);
}
```

## 查找、替换

`Object max(Collection)`：根据元素的自然顺序，返回给定集合中的最大元素

`Object max(Collection, Comparator)`：根据 `Comparator` 指定的顺序，返回给定集合中的最大元素

`Object min(Collection)`

`Object min(Collection,Comparator)`

`int frequency(Collection, Object)`：返回指定集合中指定元素的出现次数

`void copy(List dest,List src)`：将 `src` 中的内容复制到 `dest` 中

`boolean replaceAll(List list, Object oldVal, Object newVal)`：使用新值替换 `List` 对象的所有旧值

```java
// max
String s = Collections.max(list, new Comparator<String>() {
	@Override
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}
});
System.out.println(s);
//min
String s1 = Collections.min(list, new Comparator<String>() {
	@Override
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}
});
System.out.println(s1);
// frequency
int f = Collections.frequency(list, "a");
System.out.println(f);
// copy
ArrayList<String> des_list = new ArrayList<>();
for (int i = 0; i < list.size(); i++) { // 为了防止异常
	des_list.add("");
}
Collections.copy(des_list, list);
System.out.println(des_list);
// replaceAll
Collections.replaceAll(list, "a", "b");
System.out.println(list);
```

