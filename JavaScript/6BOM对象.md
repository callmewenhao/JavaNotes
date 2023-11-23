# BOM 对象

Browser Object Model 浏览器对象模型

JavaScript 将浏览器的各个组成部分封装为对象

组成：
- Window：浏览器窗口对象
- Navigator：浏览器对象
- Screen：屏幕对象
- History：历史记录对象
- Location：地址栏对象

## Window 浏览器窗口对象

获取：直接使用 window，其中 window. 可以省略

```javascript
window.alert("abc");
alert("abc");
```

属性：获取其他 BOM 对象
- history 对 History 对象的只读引用。请数 History 对象
- Navigator 对 Navigator 对象的只读引用。请参数 Navigator 对象
- Screen 对 Screen 对象的只读引用。请参数 Screen 对象
- location 用于窗口或框架的 Location 对象。请参阅 Location 对象

方法：
- alert() 显示带有一段消息和一个确认按钮的警告框
- confirm() 显示带有一段消息以及确认按钮和取消按钮的对话框
- setInterval() 按照指定的周期(以毫秒计)来调用函数或计算表达式
- setTimeout() 在指定的毫秒数后调用函数或计算表达式

## History 历史记录

获取：使用 window.history 获取，其中 window. 可以省略

```javascript
window.history.方法();
history.方法();
```

方法
- back() 加载 history 列表中的前一个 URL
- forward() 加载 history 列表中的下一个 URL

# Location 地址栏对象

获取：用 window.location 获取，其中 window. 可以省略

```javascript
window.location.方法();
location.方法();
```

属性 
- href 设置或返回完整的 URL，实现网页跳转

