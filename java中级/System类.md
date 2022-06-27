```java
public class System_ {
    public static void main(String[] args) {
        // 退出程序      
        System.out.println("ok!");
        System.exit(0);
        System.out.println("ok!!!!!!");

        // 数组拷贝
        int[] arr = {1, 2, 3, 4};
        int[] arr2 = new int[4];
        System.out.println(Arrays.toString(arr2));
        System.arraycopy(arr, 0, arr2, 0, 4);
        System.out.println(Arrays.toString(arr2));
        // 返回时间
        long t = System.currentTimeMillis();
        System.out.println(t);
    }
}
```


