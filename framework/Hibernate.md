## Hibernate 介绍
- Hibernate 是一个高性能的对象关系型持久化存储和查询的服务

## Hibernate ORM 概览
- ORM (Object Relation Mapping,对象关系映射)
- 相较于JDBC的优点
 - 使用业务代码访问对象而不是数据库中的表
 - 实体是基于业务的概念而不是数据库的结构
 - 事务管理和键的自动生成

## Hibernate 简介
- Hibernate 将 java 类映射到数据库表中
- 优势
 - Hibernate 使用 XML 文件来处理映射 Java 类别到数据库表格中
 - 在数据库中或任何其它表格中出现变化，仅需要改变 XML 文件属性
 - 抽象不熟悉的 SQL 类型，并为我们提供工作中所熟悉的 Java 对象

## Hibernate 架构
- 配置对象
 - 经常只在应用程序初始化期间创造。代表了 Hibernate 所需一个配置或属性文件
 - 数据库连接: hibernate.cfg.xml 和 hibernate.properties
 - 类映射设置: java类和数据库表格之间的联系
- SessionFactory 对象
 - SessionFactory 是一个线程安全对象并由应用程序所有的线程所使用
 - SessionFactory 是一个重量级对象所以通常它都是在应用程序启动时创造然后留存为以后使用
 - 每个数据库需要一个 SessionFactory 对象使用一个单独的配置文件，如果是使用多种数据库，则需要多个 SessionFactory 对象
- Session 对象
 - 持久对象通过 Session 对象保存和检索
 - Session 对象不应该长时间保持开启状态因为它们通常情况下并非线程安全，并且它们应该按照所需创造和销毁
- Transaction 对象
 - 事务代表了与数据库工作的一个单元，在 Hibernate 中事务由底层事务管理器和事务（来自 JDBC 或者 JTA）处理
- Query 对象
 - Query 对象使用 SQL 或者 Hibernate 查询语言（HQL）字符串在数据库中来检索数据并创造对象
- Criteria 对象
 - Criteria 对象被用于创造和执行面向规则查询的对象来检索对象

## Hibernate 配置
- 与 MySQL 连接，需要在 hibernate.cfg.xml 文件中配置一下参数
```
<session-factory>
		<property name="myeclipse.connection.profile">mysqlhiber</property>
		<property name="connection.url">jdbc:mysql://127.0.0.1:3306/my_db</property>
		<property name="connection.username">root</property>
		<property name="connection.password">root</property>
		<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
		<property name="dialect">org.hibernate.dialect.MySQLDialect</property>

		<mapping resource="bean/Studentinfo.hbm.xml" />
</session-factory>
```
- 其中 mapping 标签里面的是映射文件，具体映射文件参考下面。

## Hibernate 会话
- 典型的事务例子
```
Session session = factory.openSession();
Transaction tx = null;
try {
   tx = session.beginTransaction();
   // do some work
   ...
   tx.commit();
}
catch (Exception e) {
   if (tx!=null) tx.rollback();
   e.printStackTrace(); 
}finally {
   session.close();
}
```
## Hibernate 持久化类
- 在 Hibernate 中，其对象或实例被存储在数据库表单中的 Java 类被称为持久化类
- 主要规则(非硬性)
 - 被持久化的 java 类需要一个默认的构造函数
 - 被持久化的属性应该申明为 private ，并且需要 getXX() 和 setXX() 方法

## Hibernate 映射文件
- 映射文件需要命名为 MyClassName.hbm.xml
```
<hibernate-mapping>
	<class name="bean.Studentinfo" table="studentinfo" catalog="my_db">
		<id name="name" type="java.lang.String">
			<column name="name" />
			<generator class="assigned" />    <!-- 主键 -->
		</id>
		<property name="age" type="java.lang.Integer">
			<column name="age" />
		</property>
		<property name="major" type="java.lang.String">
			<column name="major" />
		</property>
		<property name="sex" type="java.lang.String">
			<column name="sex" />
		</property>
		<property name="password" type="java.lang.String">
			<column name="password" />
		</property>
	</class>
</hibernate-mapping>
```

## Hibernate 对数据库进行查询操作
- Hibernate 查询语言
- Hibernate 标准查询
- 原生 SQL

## Hibernate 缓存
- 用于应用程序性能的优化，降低了应用程序对物理数据源访问的频次
- Hibernate 采用多级缓存

## Hibernate 批处理
- 用于处理大量数据
- 首先需要设置 hibernate.jdbc.batch_size 作为批处理的尺寸
```
<property name="hibernate.jdbc.batch_size"> 50 </property>
``` 
- 使用下面两个方法实现持续处理数据而不是在内存中缓存它们
```
session.flush();
session.clear();
```

## Hibernate 拦截器
- 主要用来实现一个对象在他不同的生命周期进行一些任务的方法
- 可以继承 EmptyInterceptor 类来实现一些方法，例如:
 - onDelete()
 - onFlushDirty()
 - onLoad()
 - onSave()
 - preFlush()
 - postFlush()
- 在创建会话时可以添加拦截器
```
Session session = factory.openSession( new MyInterceptor() );
```
