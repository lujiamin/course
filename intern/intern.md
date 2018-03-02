### March 2nd
- morining
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