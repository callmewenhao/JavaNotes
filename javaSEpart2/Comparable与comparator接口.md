# 比较器接口

## 比较器接口的分类
- 内部比较器：Comparable 接口
- 外部比较器：Comparator 接口


## 内部比较器

> 简单点说就是把比较器写在类的内部

类实现了 Comparable 接口，然后重写 compareTo 方法（这个方法可以看作比较器），这个类就拥有了内部比较器。注意，一旦实现了比较器，就说明这个类支持排序。


## 外部比较器

> 外部比较器用于比较目标类型

新定义一个类，类名随意，但这个类必须实现 Comparator 接口，重写 compare 方法，我们把这个称作外部比较器。

## 相关的排序接口

List 接口中的方法：`sort public void sort(Comparator<? super E> c)`

- Sorts this list according to the order induced by the specified Comparator.
- 根据外部比较器 `c` 比较 List 中的元素

Collections 工具类中的接口：
- `static <T extends Comparable<? super T>> void sort(List<T> list)`：对已经实现了内部比较器的元素进行排序
- `static <T> void	sort(List<T> list, Comparator<? super T> c)`：使用一个外部比较器 `c` 比较 List 中的元素

## 理解 compare 函数的返回值

> 一般会使用一个匿名函数 `(o1, o2) -> { return ?; }` 实现外部比较器

函数返回值：
- 返回正数：非目标顺序，交换前后参数位置
- 返回 0：相等，不交换位置
- 返回负数：目标顺序，不用交换参数位置

具体讨论：
- 目标顺序是升序：
    - 如果前面的参数大于后面的参数，不符合目标顺序，需要交换位置，返回正数
    - 如果前面的参数等于后面的参数，元素相等，不交换位置，返回 0
    - 如果前面的参数小于后面的参数，符合目标顺序，不需要交换位置，返回负数
    - 综上所述，升序可以使用 `return o1 - o2;`

- 反之，目标顺序是降序：可以使用 `return o2 - o1;`







