# 参数传递

MyBatis 接口方法中可以接收各种各样的参数，MyBatis 底层对于这些参数进行不同的封装处理方式

MyBatis 底层使用 ParamNameResolver 类来进行参数封装

单个参数：
- POJO 类型：直接使用，使用时注意：参数名和 SQL 中参数占位符名称一致
- Map 集合：直接使用，使用时注意：键值名和 SQL 中参数占位符名称一致
- Collection：封装为 Map 集合
    - map.put("arg0", collection 集合)
    - map.put("collection", collection 集合)
    - 建议使用 @Para 注解，改变键值
- List：封装为 Map 集合
    - map.put("argo", List 集合)
    - map.put("collection", list 集合)
    - map.put("list", list 集合)
    - 建议使用 @Para 注解，改变键值
- Array：封装为 Map 集合
    - map.put("arg", 数组)
    - map.put("array", 数组)
    - 建议使用 @Para 注解，改变键值
- 其他类型：int 等类型，SQL 中参数占位符名称无所谓

多个参数：
- 需要使用 @Para 注解定义名称
- MyBatis 会使用 Map 封装参数，但是键值默认是：param1 ... parami 或者使用 arg0 ... arg1
- 使用 @Para 注解设置一个新键值，用于替代 argi 键值，parami 还会保留

建议：将来都使用 @Param 注解来修改 Map 集合中默认的键名，并使用修改后的名称来获取值，这样可读性更高!

