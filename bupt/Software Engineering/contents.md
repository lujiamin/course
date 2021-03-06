## software engineering
#### 概述
- 软件=程序+文档
- 按所处层次
  - 系统软件
  - 中间件软件：为了解决分布异构系统集成问题而开发的软件
  - 应用软件
- 软件危机——指计算机软件在开发和维护过程中所遇到的一系列严重问题：开发成本难以估算，无法制定合理的开发计划，用户需求表达不明，维护性差，缺乏文档资料
- 软件工程三要素：方法、工具、过程
#### 软件生命周期模型
- 软件工程过程：在软件工具的支持下由软件工程师完成的一系列软件工程活动。 
- 软件生命周期
  - 制定计划
  - 需求分析
  - 设计
  - 程序编码
  - 测试
  - 运行维护
- 软件过程模型，也叫软件生命周期模型
- 传统软件生命周期模型
  - 瀑布模型
  - V模型和W模型
  - 原型方法
  - 演化模型
  - 增量模型
  - 螺旋模型
  - 喷泉模型
  - 构件组装模型
  - 快速应用开发模型
- 新型软件生命周期模型
  - RUP(Rational Unified Process)——统一软件开发过程
  - 敏捷模型
#### 系统需求分析与可行性分析
- 可行性分析
  - 经济可行性：货币的时间价值(复利)
  - 技术可行性
  - 法律可行性
  - 实施方案的选择
#### 软件需求分析
#### 结构化需求分析
- 数据建模
  - 概念模型，常用实体关系模型表示(ER图)
  - 数据对象：属性，关键字，对象之间的关系
  - 关系规范化的程度——范式(Normal Form)，描述属性间的依赖程度
    - 第一范式~第六范式，范式级别越高，冗余度越小。数据库一般只需满足第三范式
    - 第一范式(1NF)：关系中所有属性都是“单纯域”，每个属性值都是不可再分的
    - 第二范式(2NF)：非主属性完全依赖于关键字
    - 第三范式(3NF)：满足3NF基础上，要求非主属性相互独立，即任何非主属性间不存在函数依赖  
- 功能建模
  - 功能建模工具——数据流图
  - 数据流图的元素：加工，外部实体，数据流，数据存储
- 数据词典
  - 数据流词条、数据元素词条、数据文件词条、加工词条、外部实体       
- 行为建模
  - 状态迁移图
  - Petri网(PNG):主要用于处理并发系统中同步问题、资源竞争以及死锁问题
#### 软件设计
- 软件设计过程
  - 系统结构设计
  - 数据设计
  - 过程设计
- 软件概要设计步骤
  - 制定设计规范
  - 软件系统结构的总体设计
  - 处理方式设计
  - 数据结构设计
  - 可靠性设计
  - 编写概要设计说明书
  - 概要设计评审    
- 软件设计原则
  - 抽象化：过程抽象、数据抽象、控制抽象
  - 模块化：高内聚，低耦合，模块独立性越强
- 软件设计基础 
- 不同软件风格的优缺点
#### 结构化软件设计
#### 面向对象基础
- 面向对象基本思想
  - 一切都看成对象
- 面向对象的主要概念和基本原则
  - 主要概念：对象，类，关联和链，继承，聚合
  - 基本原则：分类，封装和信息隐藏，消息通信，多态化
- 经典的面向对象的分析和设计方法
  - OOA/OOD(Object-Oriented Analysis/Object-oriented Design)
  - Booch
  - 对象建模技术(OMT)
  - 面向对象软件工程方法(OOSE)
    - 需求模型
    - 分析模型
    - 设计模型
    - 实现模型
    - 测试模型
- 统一建模语言UML简介
  - 是一种可视化的建模语言
  - 组成：用例视图，逻辑视图，并发视图，构件视图，部署视图
#### 面向对象分析
#### 面向对象设计
- 模型的层次化
#### 软件设计
#### 软件测试
- 两个阶段：单元测试、集成测试
- 测试流程
  - 理解测试需求
  - 编写测试计划
  - 设计测试方案
  - 开发测试用例 
  - 执行软件测试 
  - 评估测试效果 
  - 编写测试文档 
- 软件测试方法
  - 静态
  - 动态
    - 黑盒测试：不考虑程序内部结构和内部特征
    - 白盒测试：根据软件产品的内部工作过程，在计算机上进行测试
  - 基本白盒测试技术    
    - 逻辑覆盖：语句覆盖，判定覆盖，条件组合覆盖
    - 基本路径测试
    - 控制结构测试
  - 黑盒测试技术
    - 等价类划分
    - 边界值分析
    - 错误推测法
    - 因果图法
- 软件测试过程
  - 单元测试
  - 集成测试
  - 确认测试
  - 系统测试
- 面向对象的测试方法  
- 软件测试工具