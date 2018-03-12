## Spring Ioc容器
##### BeanFactory
- 从一个 XML 文件中读取配置元数据，由这些元数据来生成一个被配置化的系统或者应用

##### ApplicationContext 
- 加载配置文件中定义的 bean，将所有的 bean 集中在一起，当有请求的时候分配 bean
- 从属性文件中解析文本信息和将事件传递给所指定的监听器
- ApplicationContext包含BeanFactory的所有功能，ApplicationContext会被推荐使用。但是在移动设备或applet的应用程序，可以选择BeanFactory

##### ApplicationContext常用接口实现
- FileSystemXmlApplicationContext  需要提供配置文件的完整路径
- ClassPathXmlApplicationContext  不需提供完成路径，只需正确配置CLASSPATH环境变量即可

##### 配置文件 bean 的相关属性
- class 必需，指定创建的bean类
- id/name 唯一标识符，使用id更好，同id会抛出异常
- scope 设置作用域
 - singleton 将bean的定义限制在每一个Spring Ioc容器中的一个单一实例,默认作用域始终是singleton，当仅仅需要bean的一个实例时可以使用
 - prototype 将单一bean的定义限制在任意数量的对象实例，满状态的bean使用prototype作用域和没有状态的bean使用singleton作用
 - request 限制为HTTP请求
 - session 限制为HTTP会话
 - global-session 限制为全局HTTP会话
 - 后面三个只在web-aware Spring Application的上下文中有效
 
##### Spring Bean的生命周期
- 为了定义安装和拆卸一个 bean，我们只要声明带有 init-method 或 destroy-method 的参数
- init-method 属性指定一个方法，在实例化 bean 时，立即调用该方法
- 同样，destroy-method 属性指定一个方法，只有从容器中移除 bean 之后，才能调用该方法
```
<bean id="exampleBean" class="examples.ExampleBean" init-method="init"/>
```
```
public class ExampleBean {
   public void init() {
      // do some initialization work
   }
}
```

##### Spring Bean 后置处理器
- BeanPostProcessor 接口定义回调方法，你可以实现该方法来提供自己的实例化逻辑，依赖解析逻辑
- 重载以下函数，在初始化 bean 的之前和之后实现相关函数
 - postProcessBeforeInitialization
 - postProcessAfterInitialization

##### Spring Bean的继承
- 指定抽象属性为true
```
   <bean id="beanTeamplate" abstract="true">
      <property name="message1" value="Hello World!"/>
      <property name="message2" value="Hello Second World!"/>
      <property name="message3" value="Namaste India!"/>
   </bean>

   <bean id="helloIndia" class="com.tutorialspoint.HelloIndia" parent="beanTeamplate">
      <property name="message1" value="Hello India!"/>
      <property name="message3" value="Namaste India!"/>
   </bean>
   ```
   
## Spring 依赖注入
##### 基于构造函数的依赖注入
##### 基于设置函数的依赖注入
##### 注入内部bean
```
<bean id="outerBean" class="...">
    <property name="target">
        <bean id="innerBean" class="..."/>
    </property>
</bean>
```
##### 注入集合
- Spring 提供了四种类型的集合的配置元素
 - list 允许重复
 ```
 <property name="listName">
	<list>
		<value>aa</value>
		<value>bb</value>
		<value>cc</value>
	</list>
 </property>
 ```
 - set  不允许重复
 ```
 <property name="setName">
	<set>
		<value>aa</value>
		<value>bb</value>
		<value>cc</value>
	</set>
 </property>
 ```
 - map  键值对
 ```
 <property name="mapName">
	<map>
	<entry key="" value=""/>
	<entry key="" value=""/>
	<entry key="" value=""/>
	</map>
 </property>
 ```
 - props 键值对，只能是字符串类型
 ```
 <property>
 	<props>
 		<prop key=""></prop>
 		<prop key=""></prop>
 		<prop key=""></prop>
 	</props>
 </property>
 ```

## Spring Beans 自动装配
##### byName
- 在 XML 配置文件中 beans 的 autowire 属性设置为 byName。然后，它尝试将它的属性与配置文件中定义为相同名称的 beans 进行匹配和连接。如果找到匹配项，它将注入这些 beans，否则，它将抛出异常.

##### byType
- 在 XML 配置文件中 beans 的 autowire 属性设置为 byType。然后，如果它的 type 恰好与配置文件中 beans 名称中的一个相匹配，它将尝试匹配和连接它的属性。如果找到匹配项，它将注入这些 beans，否则，它将抛出异常.

##### 由构造函数自动装配
- 在 XML 配置文件中 beans 的 autowire 属性设置为 constructor。然后，它尝试把它的构造函数的参数与配置文件中 beans 名称中的一个进行匹配和连线。如果找到匹配项，它会注入这些 bean，否则，它会抛出异常.

## Spring 基于注解的配置
##### @Required
- 注释应用于 bean 属性的 setter 方法，它表明受影响的 bean 属性在配置时必须放在 XML 配置文件中，否则容器就会抛出一个 BeanInitializationException 异常.

## Spring 框架的AOP
## Spring JDBC 框架
##### 常用spring JDBC的操作，主要使用JdbcTemplate 实现
- 数据访问对象接口文件DAO
- 对象模型文件(java bean)
- 建立RowMapper接口，实现mapRow方法，完成数据库的映射
- 定义DAO接口实现类文件
- 配置文件的定义，包括数据库的连接以及数据对象文件的配置

## Spring 事务管理
## Spring Web MVC 框架 

##### 页面重定向
##### 静态页面处理
##### 异常处理
##### 使用Log4J 记录日志
