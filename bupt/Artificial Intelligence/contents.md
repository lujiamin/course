## Artificial Intelligence  
#### chapter 1  绪论  
- 定义
- 起源与发展
  - 1956年前：孕育期
  - 1956-1970：形成期
  - 1970-：发展期
- 三种观点
  - 符号主义——认为人类的智能的基本单元是符号。思维就成了符号运算
  - 联结主义——又称仿生学派，原理主要为神经网络及神经网络间的连接机制与学习算法
  - 行为主义——又称进化主义，其原理为控制论及感知-动作型控制系统
- 应用领域
  - 问题求解
  - 逻辑推理与定理证明
  - 自然语言的理解  
    - 问答
    - 文摘生成
    - 释译
    - 翻译
  - 自动程序设计：随情况变化的
  - 机器学习——机器自动获取新的事实和新的推理算法以达到具有智能的行为
  - 专家系统
  - 人工神经网络
  - 机器人学
  - 模式识别
  - 机器视觉
    - 底层视觉
    - 高层视觉
  - 智能控制
  - 智能检索
  - 智能调度与指挥  
#### chapter 2  知识表示方法  
- 状态空间法
  - 问题的状态空间——是一个表示该问题全部可能状态及其关系的图(下棋、迷宫)
- 问题归约法
  - 将一个初始问题划分为子问题
  - 与或图
- 谓词逻辑法
  - 把逻辑论证符号化
  - 谓词演算
    - 基本符号：谓词符号、变量符号、函数符号、常量符号、括号和逗号
    - 连词：与(︿)，或(﹀)，蕴含(=>)，非(~)
    - 量词：全称量词，存在量词
  - 合式公式
    - 真值表
    - 等价：真值表一样
  - 等价关系
    - 否定之否定
    - 德摩根律
    - 分配律
    - 交换律
    - 结合律
    - ...
  - 置换
  - 合一      
- 语义网络法  
#### chapter 3  搜索推理技术
- 图搜索策略
- 盲目搜索
  - 宽度优先搜索
  - 深度优先搜索
  - 等代价搜索
- 启发式搜索
  - 有序搜索：总是选择"最有希望"的节点作为下一被拓展节点
  - 估价函数：确定哪一个节点最有可能通向目标
- 消解原理
  - 子句集的求解
  - 消解推理
  - 反演证明(反演树)
  - 反演求解(重言式反演求解)
- 规则演绎系统
  - 规则正向演绎系统：事实->目的
  - 规则逆向演绎系统：目标->事实
  - 规则双向演绎系统
- 产生式系统
  - 组成：产生式规则、总数据库、控制策略
  - 产生式系统的推理：正向推理、逆向推理、双向推理
- 不确定性推理  
  - 知识不确定、证据不确定、结论不确定
- 概率推理
- 主观贝叶斯
- 可行度方法
- 证据理论
#### chapter 4  计算智能(1):神经计算 模糊计算
- 概述
  - 计算智能系统
  - 人工智能系统
- 神经计算
  - 主要算法
    - 监督学习：能够根据期望输出和实际输出进行调整
    - 非监督学习：无需知道期望输出
    - 强化学习：例子(遗传算法Genetic Algorithm)
  - BP算法
- 模糊计算
  - 模糊集合
  - 模糊集的运算
    - 并
    - 交
    - 补
  - 笛卡尔积(直积×)
  - 模糊关系—— U,V是两个非空模糊集合，则U×V中的模糊子集R称为从U到V的模糊关系
  - 复合关系—— U×V的模糊关系R，V×W的模糊关系S，则R▫S是一个U到W的模糊关系
  - 模糊逻辑推理
  - 模糊判决方法
    - 重心法
    - 最大隶属度法
    - 系数加权平均法
    - 隶属度限幅元素平均法
#### chapter 5  计算智能(2):进化计算 人工生命
- 遗传算法——是模仿生物遗传学和自然选择机理，通过人工方式所构造的一类优化搜索算法
  - 霍兰德的遗传算法通常称为简单遗传算法
  - 编码和解码
  - 适应度函数：对于优化问题，适应度函数就是目标函数
  - 遗传操作：选择，交叉，变异
- GA与进化计算的发展
  - 进化计算
  - 灵感计算
  - 自然计算
- 进化策略——是一类模仿自然进化原理以求解参数优化问题的算法
- 遗传算法和进化策略都是一类模仿自然进化原理的算法，但是两者的研究领域不同。进化策略是一种数值优化的方法。遗传算法是一种自适应搜索技术  
- 进化编程，又称进化规划  
- 人工生命：视图通过人工方法建造具有自然生命特征的人造系统  
#### chapter 6  专家系统
- 概述
  - 是一个含有大量的某个领域专家水平的知识与经验智能计算机程序系统
  - 特点：启发性，透明性，灵活性
  - 类型
    - 解释专家系统(语音理解、图像分析、系统监视)
    - 预测专家系统(气象预报)
    - 诊断专家系统(医疗诊断)
    - 设计专家系统
    - 规划专家系统
    - 监视专家系统
    - 控制专家系统
    - 调试专家系统
    - 教学专家系统
    - 修理专家系统
  - 专家系统主要组成部分：知识库，综合数据库，推理机，解释器，人机接口
- 基于规则的专家系统
- 基于框架的专家系统
- 基于模型的专家系统：神经网络专家系统
- 新型专家系统：分布式专家系统，协同式专家系统
- 设计
- 专家系统的开发工具
  - 骨架型工具
  - 语言型工具
  - 构造辅助工具
  - 支撑环境
#### chapter 7  机器学习
- 定义：是一门研究机器获取新知识和新技能，并识别现有知识的学问
- 主要策略
  - 机械学习(死记硬背)
  - 传授学习(灌输，推理)
  - 类比学习(模仿)
  - 示例学习(举一反三)
- 机械学习
  - 存储组织信息(索引，排序，杂凑) 
- 归纳学习
  - 示例学习
  - 观察发现学习：发现规律，产生定理或规则
- 类比学习——通过对相似事物加以比较所进行的一种学习
- 解释学习
- 神经学习
  - 基于反向传播网络的学习(back-propagation,BP)
  - 基于Hopfield网络的学习：反馈神经网络，是一种动态反馈系统  
- 知识发现
  - 统计方法
  - 机器学习方法
    - 规则归纳
    - 决策树
    - 范例推理
    - 贝叶斯信念网络
    - 科学发现
    - 遗传算法
  - 神经计算方法
  - 可视化方法
#### chapter 8  自动规划
- 任务规划
- 路径规划
#### chapter 9  Agent(艾真体)
- 分布式人工智能的特点
- 艾真体系统是个高度开放的智能系统，相当于一个独立的功能模块
- 艾真体的结构分类
  - 反应式艾真体
  - 慎思式艾真体
  - 跟踪式艾真体
  - 基于目标
  - 基于效果
  - 复合式
- 艾真通信
#### chapter 10  自然语言理解  
- 领域: 文字识别、语音识别、机器翻译、自动文摘、句法分析、文本分类、信息检索、信息获取、自然语言生成、中文自动分词、语音合成、 问答系统
- 词法分析
- 句法分析
- 语义分析  
#### chapter 11  人工智能的争论和展望   
- 主要学派
  - 符号主义(主流)：又称逻辑主义、计算机学派
  - 连结主义：又称仿生学派，神经网络
  - 行为主义：又称进化主义，控制论
