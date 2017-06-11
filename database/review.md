## 第1章	
#### 基本概念
- DB
- DBMS
- DBS
- DBA
- 三层模式和两级映像
 - DB的数据结构
   - 逻辑模式
   - 外模式
   - 内模式
 - 两级映像

## 第2章
#### 基本概念
- 关键码
 - 超键
 - 候选键
 - 主键
 - 外键
- 关系模型的3类完整性规则
 - 实体完整性
 - 参照完整性
 - 用户定义的完整性规则
- 关系模型的3层体系结构
 - 关系模式
 - 子模式
 - 存储模式 

#### 关系代数
- 5个基本操作
 - 并
 - 差
 - 笛卡尔积
 - 投影
 - 选择
- 4个组合操作
 - 交
 - 连接
 - 自然连接
 - 除法

#### 启发式算法
- 3个规则
 - 尽可能早地执行选择操作
 - 尽可能早地执行投影操作
 - 避免直接做笛卡尔积

## 第3章
#### SQL
- 组成（4部分）
 - DDL（定义模式、基本表、视图、索引）
 - DML（查询、修改、删除、插入）
 - DCL（授权、完整性约束、事务）
 - 嵌入式SQL语言
- SQL数据定义
 - 模式的撤销——drop schema xxx [cascade|restrict].
 - 基本数据类型
 - 基本表
  - create table xxx();
  - alter table xxx [add|drop|modify] yyy;
  - drop table xxx [cascade|restrict];
 - 索引
  - create index xxx on ...;
  - drop index xxx;
- SQL数据查询
 - 基本句型——select A1,A2... from R1,R2... where ... having ... group by ... order by ..[asc|desc]
 - select 后的内容必须是group by后面内容的子集
 - where 子句中不能直接使用聚合函数
 - and | or | not
 - in | not in 
 - exists | all | some | unique
 - 语句嵌套
 - 聚合函数——count(*),count(xx),sum(xx),avg(xx),max(xx),min(xx)
 - 聚合函数中除了count(*)以外，其余都会跳过值
 - 导出表的使用
- SQL数据插入
 - insert into xxx values(...);
- SQL数据删除
 - delete from xxx where ...;
- SQL数据修改
 - update xxx set ... where ...;
- 视图
 - create view xxx(...) as select ...;
 - drop view xxx;
 - 定义时加上 with check option后才允许用户更新视图

## 第4章
#### 函数依赖(FD)
- 函数依赖的定义
- 推理规则
 - 自反性、增广性、传递性、合并性、分解性、伪传递性、复合性
- 非平凡的FD
- FD和关键码的联系
 - 超键（能退出完整属性集）
 - 候选键（任一真子集都无法推出完整的属性集）
- 属性集的闭包
 - X→Y能用 FD 推理规则推出的充分必要条件是Y∈X+
- FD推理规则的完备性
 - 正确性保证了推出的所有FD是正确的，完备性保证了可以推出所有被蕴涵的FD
- FD集的最小依赖集
 - 每个FD右边都是单属性
 - 没有冗余的FD
 - FD左边没有冗余的属性

#### 关系模式的分解特性
- 无损分解
 - 损失分解指丢失信息，产生寄生元组，而不是元组的丢失
- 判断无损分解的测试方法
 - 表格法
 - p={R1，R2}是无损分解的充分必要条件是 （R1 ∩ R2 → R1-R2 或者 R1 ∩ R2 → R2-R1 ）
- 保持函数依赖的分解

#### 范式
- 1NF
- 2NF——每个非主属性完全依赖于候选键
 - 局部依赖的定义
 - 分解成2NF的算法——例：关系模式R(ABC)，F={AB->C,A->C}分解成p={AC,AB}
- 3NF——每个非主属性都不传递依赖于候选键
 - 对于每个FD X->Y，都有X是R的超键或者Y的每个属性都是主属性，则R是3NF
 - 分解成3NF的算法——1)求最小依赖集，并将左部相同的合并. 2)将最小依赖集中 X->Y 构成一个模式 XY. 3)如果每个模式中都不包含候选键，则将候选键作为一个模式放入模式集中.
- BCNF——每个属性都不传递依赖于候选键
 - 对于每个FD X->Y，都有X是R的超键，则R是BCNF

## 第5章
#### 数据库设计
- 生存周期
 - 规划
 - 需求分析
 - 概念分析
 - 逻辑分析
 - 物理分析
 - 数据库实现
 - 数据库运行和维护
- 各种模式的区别和对应关系
 - 关系模式 <——> 逻辑模式 <——> 模式名、属性名
 - 子模式   <——> 外模式(view) <——> 用户对数据库进行操作的权限
 - 存储模式 <——> 内模式(index) <——> 存储关系

#### ER图
- 基本元素
 - 实体（矩形框）
 - 联系（菱形框）
 - 属性（椭圆形）
- 关系模式
 - M:N或者M:N:P需要单独转化为关系模式

## 第7章
#### 事务
- 事务的ACID性质 
 - 原子性（A）
 - 一致性（C）
 - 隔离性（I）
 - 持久性（D）
- 事务结束的方法
 - commit
 - rollback

#### 数据库的恢复
- 故障类型
 - 事务故障
 - 系统故障
 - 介质故障
- 检查点技术
 - 只有在检查点的时候才真正将修改写入磁盘
 - redo
 - undo

#### 数据库的并发控制
- 并发带来的3个问题
 - 丢失更新问题
 - 读脏数据问题
 - 不可重复读问题

- 封锁技术
 - 排他型封锁（X锁）——不允许其他事务再对该数据加任何类型的锁
 - 共享型封锁（S锁）——允许其他事务对该数据加S锁，但不允许任何事务对该数据加X锁
 - 使用S锁的操作有3个：
  - 申请S锁
  - 升级和写操作
  - 解除S锁
 - 封锁的粒度——封锁对象的大小称为封锁的粒度
 - 封锁协议
  - 一级封锁协议——防止丢失更新
  - 二级封锁协议——防止丢失更新、防止读脏数据
  - 三级封锁协议——防止丢失更新、防止读脏数据、防止不可重复读

- 封锁带来的问题
 - 活锁——使某个事务永远处于等待状态
 - 饿死——某事务永远轮不上封锁的机会
 - 死锁——几个事务都处于等待状态，且需要另一个事务解除封锁才能够继续执行

- 并发操作的调度
 - 串行调度——事务串行调度的结果都是正确的
 - 可串行化的调度——一个并发调度的执行结果和某一个串行调度的执行结果等价
 
- SQL对事务并发处理的支持
 - 事务的存取模式
  - read only（只读型）——事务对数据库的操作只能是读操作。
  - read write（读写型）——事务对数据库的操作可以是读操作，也可以是写操作
  - SQL定义语句：set transaction read only
 - 事务的隔离级别 （从高到低）
  - serializable（可串行化）
  - repeatable read（可重复读）
  - read committed（读提交数据）
  - read uncommitted（可以读未提交数据）
  - SQL语句：set transaction isolation level serializable

#### 数据库的完整性
- 完整性概念
 - 正确性
 - 有效性
 - 相容性
 - 防止错误的数据进入数据库
- SQL中的完整性约束
 - 域约束
  - create domain xxx char(6) default '??' constraint yyy check();
 - 基本表约束
  - 候选键（unique）和主键（primary key）
  - 外键参照动作
   - no action
   - cascade 
   - restrict
   - set null
   - set default 
  - 检查约束的定义
   - check语句 
 - 断言
  - SQL语句：create assertion ass1 check（...）;

#### 数据库的安全性
- 安全性级别
 - 环境级
 - 职员级
 - os级
 - 网络级
 - DBS级
- 访问数据的权限（4个）
 - 读（read）
 - 插入（insert）
 - 修改（update）
 - 删除（delete）
- 修改数据库模式的权限（4个）
 - 索引（index）
 - 资源（resource）
 - 修改（alteration）
 - 撤销（drop）
- SQL中的安全机制
 - 视图
 - 权限
  - 授权语句
   - grant select，update on S to  Wang with grant option
   - 把对关系S的查询、修改权限授权给用户Wang，并且Wang还可以把这些权限转授给其他用户
   - to public 表示授权给所有用户
   - references （T#）引用其他关系的主键作为外键
  - 回收语句
   - revoke select，update on S from Wang cascade
   - 从用户Wang回收对关系S的查询、修改权，并且是级联回收
   - revoke grant option for references（C#）on C from BAO
   - 从用户BAO回收对关系C中主键C#引用的转授权
   - revoke grant option for 用于回收转授权
  - 谁授权，由谁回收
  - 权限操作中，查询不能加列名表，更新(update)可以加列名表
 - 角色
 - 审计——用于安全性目的的数据库日志称为审计追踪

## 第10章
#### ODBC句柄
- ODBC有3个句柄
 - 环境句柄——定义了一个数据库环境（只有1个）
 - 连接句柄——定义了一个数据库连接
 - 语句句柄——定义了一条SQL语句