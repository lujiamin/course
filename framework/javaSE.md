- 按值传递和引用传递——String类型的传递属于按引用传递(×)
- 无修饰符就是默认权限，也叫包访问权限，只能被同一包内类访问.
- 1.静态代码块 2.构造代码块3.构造方法的执行顺序是 1 > 2 > 3
- PreparedStatement是预编译的,对于批量处理可以大大提高效率.PreparedStatement执行效率＞Statement
- 声明一个数组时，不能直接限定数组长度，只有在创建实例化对象时，才能对给定数组长度.
- String []a;
- String a[];
- String a[20];(×)

***

- 抽象类和接口都不能被实例化，优先选用接口.
- 装箱、拆箱操作发生在引用和值之间.
- HashMap通过开放地址法解决哈希冲突
- doGet/doPost 则是在 javax.servlet.http.HttpServlet 中实现的

***

- java不允许单独的方法，过程或函数存在,需要隶属于某一类中
- java语言中的方法属于对象的成员,而不是类的成员。不过，其中静态方法属于类的成员

***

- StringBuilder,StringBuffer 的容量初始都为16.当>16时返回实际容量大小,利用capacity()方法可以获得当前容量大小.
- 获取长度使用length()方法.

***
### java 基本数据类型
- 布尔型 boolean (1)
- 字符型 char (16)
- 整型 byte (8)  short(16) int(32) long(64)
- 浮点型 float(32) double(64)

### 访问修饰符
- private 类内部访问
- default 类内部访问 同一个包
- protected 类内部访问 同一个包 子类
- public 任意地方

### 非访问修饰符
##### static 
 - 变量 静态变量也被称为类变量，独立于对象，无论实例化多少变量，只有一份拷贝
 - 方法 静态方法不能使用类的静态变量

##### final
 - 变量 能被显式地初始化并且只能初始化一次。申明为常量
 - 方法 类中的 final 方法可以被子类继承，但是不能被子类修改。声明 final 方法的主要目的是防止该方法的内容被修改
 - 类 final 类不能被继承
##### abstract
 - 抽象类  抽象类不能用来实例化对象，声明抽象类的唯一目的是为了将来对该类进行扩充
 - 抽象方法  是一种没有任何实现的方法，该方法的的具体实现由子类提供。任何继承抽象类的子类必须实现父类的所有抽象方法，除非该子类也是抽象类
##### synchronized 声明的方法同一时间只能被一个线程访问
##### transient 用来修饰变量，在对象序列化的过程中，被标记的变量不会被序列化
##### volatile 变量修饰符 本意:不稳定的。被修饰的变量每次被线程访问都要到主存中进行读取。两个不同的线程看到某个成员变量的值是同一个。

### 基本数据类型之间的比较
- 基本类型之间的比较，会将低精度类型自动转为高精度类型再比较

### 位运算符
- & 按位与
- | 按位或
- ^ 按位疑惑
- ~ 按位补
- << 按位左移
- ">>" 按位右移
- ">>>" 按位右移补零

### 条件运算符 
- ? :

### 循环结构 
- break 跳出整个语句块
- continue 使程序立刻跳转到下一次循环的迭代
 
### 装箱和拆箱
```
Integer i=3;  //自动装箱，编译器自动执行以下语句:Integer i=Integer.valueOf(3);
i=i+3;    //自动拆箱    i.intValue();
```
### 基本类
- Number 类
- Character 类
- String 类, 有和字符串处理的相关方法
- StringBuffer 和 StringBuilder. StringBuilder相较于StringBuffer有速度优势，所以多数情况下建议使用StringBuilder类。然而在应用程序要求线程安全的情况下，则必须使用StringBuffer类，StringBuilder不是线程安全的(不能同步访问)

### 数组 
##### 定义首选风格，int [] a;
##### Arrays 类 
 - binarySearch() 对排序好的数组进行二分搜索
 - equals() 判断两个数组是否相等
 - fill() 给数组赋值
 - sort() 升序

### 时间日期
- Date 类 
 - DateFormat 类用于格式化输出日期时间
```
DateFormat.getDateInstance().format(new Date());   //2017-3-2
```
- Calendar
```
Calendar c=new Calendar();
c.get(Calendar.YEAR);   //2017
```
- GregorianCalendar 公历

### 流(Stream)、文件(File)、IO
- java 的控制台输入有System.in完成
```
BufferedReader br = new BufferedReader(new InputStream(System.in)) ;
br.read();  //读取字符
br.reanLine();   //读取字符串
```
- 控制台输出
```
System.out.write();  //也可以进行写操作
```
- FileInputStream 用于从文件读取数据
```
InputStream f=new FileInputStream("C:/java/...");
```
- FileOutputStream 用于创建一个文件并向文件中写数据
```
OutputStream f=new FileOutputStream("C:/java/...");
```
- File 类,主要同于文件和目录的创建、文件的查找和删除
- Scanner 类
```
Scanner s=new Scanner(System.in);
s.next();
s.nextLine();  
```
### 异常处理 
- 可以自定义异常 通过 extends Exception 实现

### java 面向对象
##### 继承
##### Override Overload
 - Override (重写), 子类对父类的允许访问的方法的实现过程进行重新编写，返回值和形参都不能改变.需要调用父类中被重写的方法时，可以使用 super.xx();
 - Override (重载), 一个类里面，方法名字相同，而参数不同
##### 抽象类 
 - 不能被实例化，被继承才能使用，类的其他功能依然存在。
 - 使用 abstract class 来定义

### java高级教程
#### 数据结构 
##### Enumeration
 - 该接口中定义了一些方法，通过这些方法可以枚举（一次获得一个）对象集合中的元素
##### BitSet
##### Vector 
 - Vector 实现了一个动态数组
 - 是同步访问的
 - 支持 4 种构造方法
##### Stack 
 - Stack 是 Vector 的一个子类，实现了标准的后进先出的栈
 - 堆栈之定义了一个默认的构造函数，用来创建一个空栈
##### Dictionary / Map 
 - 实际中使用 Map，键值对
 - 常用方法
  - entrySet()
  - get()
  - hashCode()
  - isEmpty()
  - keySet()
  - put()
  - values()
##### Hashtable
##### Properties
 - 继承于 Hashtable ，表示一个持久的属性集。属性列表中每个键及其对应值都是一个字符串

#### 集合框架
##### 集合接口
 - Collection
 - List 继承于Collection
 - Set 继承于Collection
 - SortedSet
 - Map
 - Map.Entry
 - SortedMap  是Key保持在升序排列
 - Enumeration
##### 集合类
 - AbstractCollection
 - AbstractList
 - AbstractSequentialList
 - LinkedList 实现了链表
 - ArrayList  实现动态数组
 - AbstractSet
 - HashSet 使用了哈希表
 - LinkedHashSet
 - TreeSet  使用元素的自然顺序进行排序‘
##### 使用迭代器来遍历一个集合中的元素
```
Iterator iterator = xx.iterator();
```
### java 实例
#### 字符串操作
##### 比较 
 - compareTo() ,返回字符串中第一个字母ASCII的差值.
 - compareToIgnoreCase()
##### 查找字符串最后一次出现的位置
 - lastIndexOf()
##### 删除字符串中某一部分
 - 利用subString() 来实现
##### 替换
 - replace()
 - replaceFirst()
 - replaceAll()
##### 反转
 - 利用reverse()实现
##### 字符串搜索
 - indexOf(),不存在子串则返回-1
##### 字符串分割
 - split(),出现特殊字符，需要使用转义
##### 大小写转换
 - toUpperCase()
 - toLowerCase()
##### 测试两个字符串区域是否相等
 - regionMatches()
##### 字符串创建性能问题
 - 通过 String 关键词创建字符串 : 6 毫秒  通过 String 对象创建字符串 : 14 毫秒
##### 字符串连接
 - + 直接使用加号进行字符串连接
 - StringBuffer类的 append() 函数进行连接
 
#### 数组操作
##### 数组排序和查找
 - sort()
 - binarySearch()
##### 数组填充
 - Arrays.fill()
##### 删除数组元素
 - remove()
##### 数组差集
 - objArray1.removeAll(objArray2)
##### 数组交集
 - objArray1.retainAll(objArray2)
##### 查找指定元素
 - contains()
##### 数组并集
 - 利用 Set 确保元素的唯一性,去除相同元素
