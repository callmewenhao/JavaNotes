# Arrays 类

## Arrays 类常见方法

Arrays 里面包含了一系列静态方法，用于管理或操作数组（比如：排序和搜索）

- `toString` 返回数组的字符串形式，`Arrays.toString(arr)`

- sort 排序（自然排序和定制排序）`Integer arr[]= {1, -1, 7, 0, 89, 3);` 

- binarySearch 通过二分搜索法进行查找，要求必须排好序，`int index = Arrays.binarySearch(arr, 3);`

```java
public class Arrays_ {
    public static void main(String[] args) {
        Integer[] arr = {-1, 3, 2, 9, 5};
        Arrays.sort(arr); // 默认由小到大
        System.out.println(Arrays.toString(arr));

        // 自定义排序
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; // 由大到小
            }
        });
        System.out.println(Arrays.toString(arr));
        // 二分搜索, return -(low+1)
        int[] arr1 = {-100, 1, 2, 13, 44, 71};
        int index = Arrays.binarySearch(arr1, 2);
        System.out.println(index);
        // copyOf 方法
        Integer[] integers = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(integers    )
        // fill 方法替换数组元素
        Integer[] i1 = {1, 2, 4};
        Arrays.fill(i1, 0);
        System.out.println(Arrays.toString(i1)););
        // equals 方法
        System.out.println(Arrays.equals(arr, i1));
        // asList 方法
        List<Integer> integers1 = Arrays.asList(1, 2, 3, 4);
        System.out.println(integers1);    
    }
}
```
