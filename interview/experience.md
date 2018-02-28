### 广东涌浪网络技术有限公司
- 数据库组合索引，搜索其中一个字段会有作用吗？
  - (xx,yy,zz)建立组合索引，在进行查询操作时，where语句中只有先出现组合索引左边的列后，搜索右边的列才能起到作用。越离散的字段放在前面。
- 结构化程序设计
  - 顺序，选择，循环三种结构
- 多重继承？
  - 只能继承一个父类，但是可以利用接口与实现多继承功能
- break，continue，return区别    
- java是否会有内存泄露？
  - 静态集合类，由于是静态的，生命周期和程序一致，程序结束前不会被释放
  - 各种连接，数据库网络连接，不显示关闭会造成内存无法被回收
  - 变量不合理的作用域
  - 单例模式造成内存泄露
- 垃圾回收机制
  - 引用计数法
  - 压缩回收
  - 按代回收
  - 追踪回收(遍历对象的应用图)  
- get，post区别
  - post是加密的，不是明文显示的
  - get数据量小，通常在1024字节
- linux命令  
### 南京智数科技有限公司
- 系统定时器
  - js实现，setInterval()，方法会不停的调用函数，知道clearInterval()被调用
  - java Timer单线程，最小堆，不断轮询
  - mysql定时器event
- MySQL如何优化？
  - 索引
  - 语句的优化
    - where子句要将可以过滤掉大部分记录的条件写在最右(原因是从右向左解析)，过滤掉大量数据后可以提高执行效率
    - 减少使用 * 代替列名
    - 删除所有记录同truncate替代delete
    - where语句替代having
    - 多使用内部函数提高SQL效率
    - 表名，列名过长使用别名
  - 尽量多提交commit，会释放回滚点
- 数据库时区的转换  
  - 使用datetime，存时间的时候一律用程序生成UTC时间存入，不是local时区的时间
  - 显示所在地时区显示时间，用js处理时间的显示
- MyBatis如何进行批量操作？(需要详细研究常见MyBatis的面试题)
  - 配置批量执行器
  ```
  <settings>
      <setting name="defaultExecutorType" value="BATCH"> 
  </settings>
  ```
  - java代码实现批量执行器的使用
  ```
  sqlSessionFactory.openSession(ExecutorType.BATCH);
  ```
  - 一旦使用了批量执行器，在默认情况下在commit后才方法送SQL到数据库
  - settings是MyBatis中最复杂的配置，它会改变MyBatis运行时的行为，作用十分必要
- java设计模式？适配器模式？(详细研究常见设计模式)
  - 单例模式
  - 工厂模式
  - 适配器模式——使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
  - 观察者模式
- 简述几个排序的实现
  - 冒泡排序
  - 堆排序
  - 快排
  - 归并排序  
- 如何给用户推荐商品？
  - 推荐系统，相似度算法  