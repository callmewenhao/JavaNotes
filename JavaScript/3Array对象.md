# Array 对象

JavaScript Array 对象用于定义数组

方式一
```javascript
var 变量名 = new Array(元素列表);

var arr = new Array(1, 2, 3);
```

方式二
```javascript
var 变量名 = [元素列表];

var arr = [1,2,3];
```

访问
```javascript
arr[索引] = 值;
arr[0] = 1;
```

注意：JS 数组类似于 Java 集合，长度，类型都可变

特点：
- JavaScript 数组相当于 Java 中集合。**变长变类型**
- 有 length 属性
- push 方法：追加元素
- splice(start, n)：从 start 处开始删除元素，一共删除 n 个




