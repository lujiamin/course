## Struts 2 配置文件
#### web.xml
- 配置 Struts 2 过滤器
```
<filter>
	<filter-name>Struts 2</filter-name>
	<filter-class>org.apache.struts2.dispatcher.FilterDispatcher</filter-class>    //过滤器实现类
</filter>
<filter-mapping>
	<filter-name>Struts 2</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```
#### Struts.xml
- 常用配置代码
```
<package name="" extends="struts-default">
	<action name="" class="" method="">
	<result name=""></result>
	<result name=""></result>
	...
	</action>
</package>
```
- 使用<struts></struts>标签导入配置文件
```
<struts>
     <include file="my-struts1.xml"/>
     <include file="my-struts2.xml"/>
</struts>
```
#### struts-config.xml
- 该配置文件是Web Client中View和Model组件之间的链接，并不常用。

#### struts.properties
- 这个配置文件提供了一种机制来改变框架的默认行为。实际上，struts.properties配置文件中包含的所有属性也可以在web.xml中配置使用init-param，以及在struts.xml配置文件中使用constant标签。 但如果你想保持事件独立以及保留更多struts细节，那么你可以在WEB-INF/classes文件夹下创建这个文件.

## Action 动作
- 唯一要求使必须有一个无参数方法返回 String 或 Result 对象，并且必须是 POJO.可以使用 ModelDriven 的方式，但必须实现 getModel() 方法.
```
public class Example extends ActionSupport implements ModelDriven<XX>{
	
	private XX xx=new XX();   //对象实例
	
	public String execute() throws Exception{
		...
		return "success";
	}
	
	public XX getModel(){
		return this.xx;
	}
}
```
- 一个类文件里面可以定义多个 action 类

## 拦截器
#### 使用拦截器实现以下操作
- 在调用action之前提供预处理逻辑
- 在调用action后提供后处理逻辑
- 捕获异常，以便可以执行备用处理

#### 如何使用拦截器
- 在 <action>标签中使用<interceptor-ref name="timer" />就可以在日志中输出action执行的时间，这是由timer拦截器实现的.
- 可以在<package>标签内注册多个拦截器，同时可以在<action>标签内调用多个拦截器，也可以用不同的action调用同一个拦截器.

#### 创建自定义拦截器
- 拦截器实现类
```
public class MyInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String result = arg0.invoke();
		return result;
	}
}
//每次调用invoke()时，ActionInvocation都会查询其状态，并执行下一个拦截器。当所有配置的拦截器都被调用时，invoke()将使得action本身被执行
```
- 拦截器注册
```
<interceptors>
         <interceptor name="myinterceptor" class="" />
</interceptors>
<action name="" class="" method=""> 
         <interceptor-ref name="myinterceptor" />
         <result name="success"></result>
</action>
```
#### 拦截器堆栈
- 使用拦截器堆栈来管理拦截器
```
<interceptor-stack name="basicStack">
   <interceptor-ref name="exception"/>
   <interceptor-ref name="servlet-config"/>
   <interceptor-ref name="prepare"/>
   <interceptor-ref name="checkbox"/>
   <interceptor-ref name="params"/>
   <interceptor-ref name="conversionError"/>
</interceptor-stack>
```
- 在action中应用
```
<action name="" class="">
   <interceptor-ref name="basicStack"/>
   <result></result>
</action>
```
## 结果类型
#### dispatcher, 默认的结果类型
#### FreeMaker 是一个流行的模板引擎
#### redirect 结果类型调用标准的response.sendRedirect()方法，使得浏览器向给定的位置创建一个新请求
```
<result name="success" type="redirect">
    <param name="location">/New.jsp</param>
</result>
```

## 值栈/OGNL
- 值栈是一组对象
- OGNL(Object-Graph Navigation Language，对象图导航语言)
- 需要结合标签来学习

## 文件上传
- fileUpload 预定义拦截器
- 必须加上defaultStack，而且defaultStack要放在最后
- 使用表单上传文件，必须将 enctype 设置为 multipart/form-data
- 添加拦截器
```
<interceptor-ref name="fileUpload">
	<param name="allowedTypeSet">application/zip,application/pdf,image/gif</param>    //设置上传文件的属性
	<param name="maxinumSize"></param>  //设置上传文件最大值
</interceptor-ref>
<interceptor-ref name="defaultStack"></interceptor-ref>
```
- 默认情况下，fileupload拦截器提供三个参数
 - 文件: xx
 - 文件名: xxFileName
 - 文件类型: xxContentType

## 验证框架
 - 客户端验证通常使用JavaScript实现，例如 jQuery 框架 Validate.但是应用程序的其他级别也需要引入验证. 

 #### 使用 action 的 validate 方法进行验证。
 ```
 @Override
	public void validate() {
		if (name == null || name.trim().equals("")) {
			addFieldError("name", "the name is required");
		}
		if (age < 0 || age > 100) {
			addFieldError("age", "the age is invalid");
		}
	}
	//一旦 if 语句条件满足，就会在form字段上方添加错误信息。
	// addFieldError() 方法有两个参数。
	//框架会返回input值，所以需要在配置文件中配置值为input的result
 ```
#### 使用xml验证
- 在action类旁边放置一个xml文件
- xml文件需要命名为'[action-class]'-validation.xml 例如(xx-validation.xml)
```
<!DOCTYPE validators PUBLIC 
"-//OpenSymphony Group//XWork Validator 1.0.2//EN"
"http://www.opensymphony.com/xwork/xwork-validator-1.0.2.dtd">

<validators>
   <field name="name">
      <field-validator type="required">
         <message>
            The name is required.
         </message>
      </field-validator>
   </field>

   <field name="age">
     <field-validator type="int">
         <param name="min">29</param>
         <param name="max">64</param>
         <message>
            Age must be in between 28 and 65
         </message>
      </field-validator>
   </field>
</validators>
```
## Struts 2 异常处理
#### 使用exception拦截器来处理特定异常
- 在action配置中加入异常处理，就可以处理该action出现的异常
```
<exception-mapping exception="java.lang.NullPointerException" result="error" />
```
#### 全局异常映射
- 在<package>标签中添加全局异常处理
```
<global-exception-mappings>
         <exception-mapping exception="java.lang.NullPointerException" result="error" />
</global-exception-mappings>
```

## Struts 2 数据库访问
- 主要使用 jdbc 实现

## Struts 2 发送电子邮件
## 本地化/国际化(i18n)
## 主题和模板
## 注释 
- 将配置文件转化为注释，但是不利于后期维护

## Struts 2 标签 
#### 控制标签
- 控制标签是一组可以方便的控制页面执行的流程的标签

#### 数据标签
#### 表单标签
#### Ajax标签
- 需要导入struts2-dojo-plugin-2.2.3.jar包
- 需要在jsp页面中添加sx前缀 <%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
- 需要加入<sx:head/> 标签，初始化dojo框架。如果没有框架将无法工作.

## Struts2 和Spring集成
- 需要导入struts2-spring-plugin-x.y.z.jar到WEB-INF/lib文件夹中。(可能会出现异常)
- web.xml 文件设置，需要添加监听器
```
<listener>
      <listener-class>
         org.springframework.web.context.ContextLoaderListener
      </listener-class>
</listener>
```
## Struts2 和 Hibernate 集成
- 分别导入相关的jar包
- 对 hibernate.cfg.xml 和 struts.xml 进行配置，前者主要是对数据库的相关操作，后者则是对action的部署.
