### March 2nd
- morning
  - eclipse使用maven搭建项目，并添加web支持
  - Spring MVC大概轮廓熟悉
- afternoon
  - MyBatis自动映射功能——可以在settings中配置autoMappingBehavior属性值
    JavaBean
    ```
    package com.pojo;
    public class Role{
        private Long id;
        private String roleName;// 驼峰命名
    }
    ```
    数据库字段
    ```
    ID，ROLE_NAME // 下划线分割
    ```
    Mapper映射文件可以使用别名
    ```
    <select parameterType="id" id="getRole" resultType="com.pojo.Role">
        select id,role_name as roleName from role where id=#{id}
    </select>
    ```
    RoleDao接口
    ```
    public Role getRole(Long id);
    ```
  - <select></select>传递多个参数，可以定义简单的JavaBean，或者使用注解@Param，或者使用Map传递参数  
  - <sql></sql>元素可以定义SQL语句的组成部分，其他的语句可以通过引用来使用它，达到重用的功能。
  - MyBatis中的级联问题
    - association，一对一，两张表有公共的列(例：学生和学号)
    ```
    <association property="" column="" select=""/>
    ```
    - collection，一对多，学生和多门课程之间的关系
    ```
    <collection property="" column="" />
    ```
    - discriminator，鉴别器，男女学生的区分
  - 缓存cache
    - 一级缓存——在参数和SQL完全一样的情况下，使用同一个SqlSession对象调用同一个Mapper方法时，往往只执行一次SQL，第一次执行的时候就会将其放在缓存中。在缓存没有超时的情况下，不会再发送SQL到数据库。
    - 二级缓存——在映射xml文件配置开启缓存
    ```
    <cache eviction="LRU" flushInterval="1000" size="1024" readOnly="true"/>
    //eviction代表的是缓存回收策略：LRU,FIFO...;size是引用数目，代表缓存最多可以存储多少个对象
    ```
  - 动态SQL——通常在xml文件中进行配置
    - if元素：常用的判断语句，常常与test配合使用
    ```
    <select id="findRoles" parameterType="" resultMap="roleResultMap">
        select role_no,role_name,note from t_role where 1=1
        <if test="roleName != null and roleName != '' ">
            and role_name like concat('%',#{roleName},'%')
        </if>
    </select>        
    ```    
    - choose,when,otherwise元素：实现类似switch的功能
    ```
    <select id="" parameter="" resultMap="">
        select role_no,role_name,note from t_role
        where 1=1
        <choose>
            <when test="">
                AND role_no = #{roleNo}
            </when>
            <when test="">
                AND role_name like concat('%',#{roleName},'%')
            </when>
            <otherwise>
                AND note is not null
            </otherwise>
        </choose>
    </select>
    ```
    - bind实现模糊查询，由于MySQL和Oracle对于模糊查询的语句不一致
    ```
    <select>
        <bind name="pattern" value="'%' + _parameter + '%'"/>  <!-- 这里的_pattern代表传进来的参数 -->
        select id,role_name 
        where role_name like #{pattern}
    </select>
    ```

### March 5th
#### Spring事务管理
- 事务隔离级别(性能依次下降)
  - 脏读——事务A未提交，事务B就读取
  - 读写提交——事务A在T1时刻先读，事务B在T2时刻进行写并提交，事务A在T3时刻处理出现错误
  - 可重复读——出现幻读的可能，事务A在T1时刻读，事务B在T2时刻进行写并提交，事务A在T3时刻读，出现幻读的情况(针对的是插入、删除记录)
  - 序列化——所有操作按顺序进行    
- 传播行为——指的是方法之间的调用
  - 无效的传播行为
  ```
  @Service
  public class RoleServiceImpl{
      @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
      public int insertRoleList(List<Role> roleList){
          this.insertRole(role);
      }

      @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED_NEW)
      public int insertRole(Role role){
          try{
              return roleDAO.insertRole(role);
          } catch(Exception e){
              e.printStackTrace();     
          }
          return 0; 
      }
      // 这里的自调用是没有代理对象，独立事务无从谈起。只能独立写一个类，再去调用insertRole方法            

  }
  ```
#### Spring依赖注入(示例)
- 编写功能类
```
import org.springframework.stereotype.Service;
@Service   // 注解说明当前类是Spring管理的一个Bean
public class FunctionService{
    public String sayHello(String word){
        return "hello "+word;
    }
}
```
- 使用功能类
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UseFunctionService{
    @Autowired // 将FunctionService的实体Bean注入到当前Bean中，让其具备FunctionService的功能。
    FunctionService functionService;

    public String sayHello(String word){
        return functionService.sayHello(word);
    }
}
```
- 配置类
```
import org.springframework.context.annotation.CompoentaScan;
import org.springframework.context.annotation.Configuration;
@Configuration // 声明当前类是一个配置类
@ComponentScan("com.example") // 自动扫描包名下所有使用@Service,@Component,@Repository,@Cotroller的类，并注册为Bean
public class DiConfig{
    
}
```
- 运行
```
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main{
    public static void main(String[] args){
        // 作为Spring容器，接收输入一个配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        // 获得声明配置的Bean
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        // 调用方法
        useFunctionService.sayHello("di");
        // 关闭容器
        context.close();
    }
}
```
- 以上声明Bean均是通过注解(@Service...)声明，也没有使用@Autowired(成员变量之上)注解注入Bean。以下使用java配置，目前更加常用，主要是配置类的修改
```
// 功能类中增加setFunctionService()方法
FunctionService functionService;
public void setFunctionService(FunctionService functionService){
    this.functionService = functionService;
}
```
```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration // 声明当前类是一个配置类

public class JavaConfig{
    @Bean
    public FunctionService functionService(){
        return new FunctionService();
    }

    @Bean
    public UseFunctionService useFunctionService(){
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService());
        return useFunctionService();
    }
}
```
#### Spring AOP(面向切面编程)
- 目的是为了解耦，可以让一组类共享相同的行为。在OOP(面向对象编程)中，只能用过继承和接口，且是单继承，阻碍更多行为添加到一组类上

#### Bean的Scope
- Scope描述的是Spring容器如何新建Bean的实例的
  - Singleton——一个Spring容器只有一个Bean实例。从Spring容器中获取2次Bean，两者相等
  - Prototype——每次调用新建一个Bean文件，两者不相等

#### Spring MVC简单搭建
- maven配置
- 日志配置
- 演示页面(index.jsp)
- Spring MVC配置
```
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebMvc // 会开启一些默认配置
@ComponentScan("com.example")
public class MvcConfig{
    @Bean
    public InteternalResourceViewResolver viewResolver(){
        InteternalResourceViewResolver viewResolver = new InteternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    } 
}
```  
- Web配置
```
public class WebIntializer implements WebApplicationIntializer{
    @Override
    public void onStartup(ServletContext servletContext)throw ServletException{
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MvcConfig.class);
        ctx.setServletContext(servletContext);

        Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadingOnStartup(1);
    }
}
```
- 简单控制器
```
@Controller // 声明是控制器
public class HelloController{
    @RequestMapping("/index"); // 配置URL和方法之间的映射
    public String hello(){
        return "index";

    }
}
```