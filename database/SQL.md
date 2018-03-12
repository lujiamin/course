#### SQL 语法
- SQL 对大小写不敏感
- 在每条 SQL 语句的末端使用分号
- 重要的 SQL 命令
 - select
 - update
 - delete
 - insert into
 - create database
 - alter database
 - create table
 - alter table
 - drop table
 - create index
 - drop index
 
#### SQL 通用数据类型
- 不同的数据库对数据类型的定义提供不同的选择，注意检查文档

#### select 语句
```
select * from student;
select name,sex from student;
```
#### select distinct
- 返回唯一不同的值
```
select distinct name from student;
```		
#### where 
- 指定提取记录需要满足的条件
```
select name,sex from student where name='jamy';
```
- where 子句中可以使用的运算符
 - = , > , < , >=  , <=
 - <> 或者 != 不等于
 - between (在某个范围内) like (搜索某种模式) , in (指定针对某个列的多个可能值) 

#### and or 
```
select * from student where name='' and age=20;
select * from student where name='' or name='';
```
#### order by
```
select * from student order by name;
select * from student order by name desc;
```
#### insert into 
```
insert into student (name,age)values('jamy',22);
```
#### update
```
update student set age=18 where name='jamy';
// where 子句不可少
```
#### delete
```
delete from student where name='jamy';
```
#### limit 
```
select * from student limit 5;
// 返回规定数目的记录
```
#### like
```
select * from student where name like '%a%';
```
- like 语句中结合通配符使用
 - % : 替代 0 个或多个字符
 - _ : 替代一个字符
 - [abc] : 字符列中的任何单一字符
 - [^abc] : 不在字符列中的任何单一字符

####  in
```
select * from student where name in ('jamy','lujiamin');
// 允许在 where 子句中规定多个值
```
#### between  和 not between
```
select * from student where age between 20 and 25;
```
#### join 
- 用于把来自两个或多个表的行结合起来
- 不同类型
 - inner join 和 join 等价
 - left join
 - right join
 - full outer join : 返回左表和右表中所有的行

#### union 
- 合并两个或多个 select 语句的结果
- 默认选取不同的值，如果允许重复，使用union all

#### select into 
- 从一个表复制数据，然后插入到另一个表中

#### insert into select
- 从一个表复制信息到另一个表

#### 撤销索引、表和数据库
- 撤销索引
```
alter table student drop index index_name;
```
- 删除表
```
drop table student;
```
- 删除数据库
```
drop database database_name;
```
- 仅仅删除表内的数据
```
truncate table student;
```
#### 创建数据库、表
```
create database database_name;
create table table_name;
```
#### 别名
- as

#### 约束
- 用于限制表中数据的类型，保证了数据库中数据的精确性和可靠性
- not null , 强制不接受 null 值
- default 
 - 用于向列中插入默认值
 - 如果没有规定其他的值，会将默认值添加到所有的新纪录
- unique , 唯一标识数据库表中的每条记录
```
alter table student add unique(name);
```
- primary key
 - 唯一标识数据库表中的每条记录
 - 主键必须包含唯一的值
 - 主键列不能包含 null 值
 - 每个表都应该有且只有一个主键
```
alter table student add primary key(id);
```
- foreign key
 - 一个 foreign 指向另一个表中的 primary key
 - 用于预防破坏表之间连接的行为
 - 防止非法数据插入外键列
- check 约束
 - 用于限制列中的值的范围

#### 克隆数据表
- 查看原始表的完整结构
```
show create table student;
```
- 改变表名，穿件新表
- 复制原始表的数据 
```
insert into ... select
```
#### 索引
- 指向表中数据的指针
- 索引能够提高 select 和 where 子句的速度，但是降低了 update 或 insert 语句的速度
```
create index index_name on student xxx;
```
- 避免索引的情况
 - 小的数据表不应当使用索引
 - 需要频繁进行大批量的更新或者插入的表
 - 列中包含大数或者 null 值
 - 频繁操作的列

#### 子句查询
- 在 select 子句中进行子查询
```
select * from student where id in (select id from student where score > 90);
``` 
#### alter table
```
alter table student add text varchar(100);
```
#### 使用视图
- 一种虚拟的表
- 视图可以包含表中的所有列，或者仅包含所选定的列
```
create view student_view as select name,age from student;
select * from student_view;
```
#### SQL注入问题
#### having 子句
- 能够指定过滤条件，对 group by 子句所产生的组施加条件
```
select name,id,age from student group by age having count(age)>=2;
// 筛选出现次数大于等于2的记录
```
#### 事务
- 事务的属性
 - 原子性
 - 一致性
 - 隔离性
 - 持久性
- 事务控制
 - commit 提交更改
 - rollback 回滚更改
 - savepoint 在事务内部创建一系列可以rollback的还原点，使用 release savepoint 删除先前创建的保存点
 - set transaction 命名事务

####　临时表
- 用于保存临时数据，会在当前的终端会话结束后被删除

#### SQL 日期函数
#### SQL 函数
- avg()
```
select avg(price) as priceAvg from products;
```
- count()
```
select count(*) from students;
```
- field()
```
select field('w','a','d','w');
// return 3 . 如果不存在则返回 0
```
- max()
- min()
- sum()
- lower() / lcase()
- upper() / ucase()
- mid()
 - 用于从文本字段中提取字符
```
select mid(name,1,4) as shortname from student;
// 第1-第4个字符
``` 
- length()  <MySQL 为length()函数>
 - 返回文本字段的长度
```
select length(name) as namelength from student;
``` 
- round()
```
select round(price,2) from products;
// 保留 2 位小数
```
- now()
 - 返回系统时间日期
- format()
 - 用于格式化输出文本
- sqrt()
- rand()
- concat()
- trim() 去除字符串头尾空格
