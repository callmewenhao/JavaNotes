# StringBuffer 类

## 基本介绍

java.lang.StringBuffer 代表可变的字符序列，可以对字符串内容进行增删。

很多方法与 String 相同，但 StringBuffer 是可变长度的。String Buffer 是一个容器。

## String VS StringBuffer

String 保存的是字符串常量，里面的值不能更改，每次 String 类的更新实际上就是更改地址，效率较低 `//private final char valuell;`

`StringBuffer` 保存的是字符串变量，里面的值可以更改，每次 `StringBuffer` 的更新实际上可以更新内容，不用每次更新地址，效率较高

## 构造器的使用

- 创建一个大小为16的 `char[]`，用于存放字符内容 
  
  `StringBuffer stringBuffer = new StringBuffer();`

- 通过构造器指定 `char[]` 大小
  
  `StringBuffer stringBuffer = new StringBuffer(100);`

- 通过给一个 String 创建 StringBuffer，char[] 大小就是 str.length() + 16
  
  `StringBuffer hello = new StringBuffer("hello");`

## String -> StringBuffer

- 使用构造器
  
  `String s = "hello"; StringBuffer str = new  StringBuffer(s);`

- 使用 append()
  
  `String s = "hello"; StringBuffer sb = new StringBuffer(); sb.append(s);`

## StringBuffer -> String

- 使用 `toString()` 方法
  
  `StringBuffer sb = new StringBuffer("hello");  String s = sb.toString();`

- 使用构造器
  
  `String s1 = new String(sb);`

## StringBuffer 常用方法

- 增 append

- 删 delete(start, end)  // 删除 [start,end) 之间的字符

- 改 replace(start, end, string)  // 将 [start,end) 间的内容替换掉

- 查 indexOf("str")  // 查找子串在字符串第1次出现的索引，如果找不到返回-1

- 插 insert(start, string)  // 在start处插入string，原来内容自动后移

- 获取长度 length
