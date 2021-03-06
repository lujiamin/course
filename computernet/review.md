---
title: the review of Computer Network
date: 2017-6-16 19:00:00
tags: review
---
### 试卷附录（掌握各字段含义）
- 以太网MAC帧结构
- IPv4数据报格式
- UCP和TCP报文段格式
- HDLC三种帧类型
- 汉明码

## 第1章——计算机网络体系结构
- 计算机网络的概念
  - 把分布在不同地点且具有独立功能的多个计算机，通过通信设备和线路连接起来，在功能完善的网络软件运行环境下，以实现网络中资源共享为目标的系统
<!-- more -->  
- 计算机网络的组成
  - 以资源共享
    - 通信子网
    - 终端系统
- 网络的类型及其特征
  - 拓扑结构
    - 星型结构
    - 树型结构
    - 总线型结构
    - 环型结构
    - 网状结构
  - 网络覆盖范围
    - 个人区域网PAN
    - 局域网LAN
    - 城域网MAN
    - 广域网WAN
    - 因特网Internet
- 网路体系结构的概念
- 协议的概念和要素
  - 协议是指通信双方必须遵循的、控制信息交换的规则的集合
  - 组成
    - 语法
    - 语义
    - 同步
  - 特点 
    - 层次性
    - 可靠性
    - 有效性
- OSI-RM
  - 应用层
  - 表示层
  - 会话层
  - 传输层
  - 网络层
  - 数据链路层
  - 物理层
- TCP/IP
  - 应用层
  - 传输层
  - 互连网络层
  - 网络接入层
- 两者的比较
  - 出发点不同
    - OSI-RM：国际标准
    - TCP/IP：军用网
  - 层次间的关系
  - 面向连接
    - OSI-RM：开始只提供面向连接的服务
    - TCP/IP：面向连接和无连接的服务并重

## 第2章——物理层
- 数据、信息、信号
- 传输媒体
  - 双绞线
  - 同轴电缆
  - 光缆
  - 无线电波
  - 地面微波
  - 卫星微波
  - 红外线技术
- 数据传输速率
  - 传码率 N
  - 传信率 R
  - R = N*log2(M)，M：电平数
- 信道带宽
- 误码率
- 时延
  - 总时延 = 发送时延+传输时延+处理时延
- 奈奎斯特定理
  - 无噪声
  - 最大传输速率C=2W*log2(M)，W：带宽，M：电平数
- 仙农公式
  - 有噪声
  - 最大传输速率C=W*log(1+S/N)，S/N：信噪比，常用分贝表示
  - S/N dB = 10*log10(s/n)
- 数字信号编码
  - 归零码和不归零码
  - 差分编码
    - 1：电平变化
    - 0：电平不变化
  - 曼切斯特编码
    - 0:01
    - 1:10
- 传输技术
  - 基带传输
  - 频带传输
    - 模拟传输
    - ASK（幅移键控，数字调幅）
    - PSK（相移键控，数字调相）
    - FSK（频移键控，数字调频）
  - 脉冲编码调制(PCM)
    - 数字传输
    - 取样——取样频率>=2Fmax
    - 量化
    - 编码
- 多路复用技术——一条信道中传输多路信号，以提高传输媒体利用率的技术
  - 时分复用（TDMA）
  - 频分复用
  - 波分复用
  - 码分复用（码分多址复用CDMA）
    - 每比特时间分为m个切片
- 数据交换技术
  - 电路交换
    - 实时交换
    - 电路利用率低
    - 没有任何差错控制
  - 报文交换
    - 报文头
    - 具有差错控制
    - 过负荷时存在报文延迟
  - 分组交换
    - 虚电路
    - 数据报
    - 优点
      - 能够实现不同类型的数据终端之间的通信
      - 分组多路通信功能
      - 数据传输质量高，可靠性高
      - 经济性好
    - 缺点
      - 延迟达几百毫秒
      - 需要交换机分析处理，增加开销
      - 技术比较复杂
- 差错控制技术
  - 原理
  - 方式
    - 自动重传
    - 前向纠错
    - 混合方式
    - 信息反馈
  - 奇偶检验码
    - 只能检出奇数个错码
  - 汉明码
    - 能够检错并且纠错
    - 会判断出错的位置并纠正
  - 循环冗余检验码
    - 根据多项式，利用除法取余求循环冗余码
- 数据通信接口特性
  - 机械特性
  - 电气特性
  - 功能特性
  - 规格特性

## 第3章——数据链路层
- 基本概念
  - 数据电路（物理链路、链路）
  - 数据链路（逻辑链路）
  - 数据链路是在数据电路上增加了传输控制功能实现的
  - 连路上的节点称为“站”
  - 数据链路基本结构
    - 点到点
    - 多点
  - 数据链路层以帧为单位传输数据
- 数据链路层的功能（7个）
  - 链路管理
  - 帧定界（帧同步）
  - 流量控制
  - 差错控制
  - 数据和控制信息的识别
  - 透明传输
  - 寻址
- 流量控制
  - 目的——现代数据通信传输，大多数采用了存储转发的分组交换技术，由于通信的随机性和突发性，当接收方的处理能力小于发送方的发送量时，必须采用流量控制
- 停止—等待流量控制
  - 种类
    - 开关式
    - 协议式
  - 停止—等待协议
    - 几种情况
      - 无差错
      - 数据帧出错
      - 数据帧丢失
      - 确认帧丢失
    - 超时重发——稍大于2倍的传输时间（从发送端到接收端的时间的两倍）
    - 帧的编号，以0和1交替编号，以区分两个帧
    - 优点：控制简单
    - 缺点：每次只能发送一个帧，信道利用率不高
- 滑动窗口协议
  - 解决停止—等待协议信道利用率低的问题
  - 当发送完一个数据帧后，发送方不是停下来等待确认帧，而是一边等待，一边继续发送若干数据帧。继续发送的数据帧数量，受到滑动窗口的限制
  - 发送窗口
    - 发送窗口尺寸——发送方为收到确认而允许连续发送帧的最大数目
  - 接收窗口
    - 接收窗口尺寸——最多允许接收的帧的数目
  - 最大窗口尺寸的确定，既要考虑流量控制，又要考虑链路的利用率
- 连续ARQ协议
  - ARQ(Automatic Repeat reQuest，自动重传请求)
  - 返回N帧的ARQ协议（Go-Back-N ARQ）
  - 当发送方得不到应答而超时后，必须重发出错的帧及其以后的所有帧
  - 连续ARQ的最大窗口，接受窗口固定设置为1，发送窗口为2^n-1。（模-1）
  - 如果最大发送窗口和序号空间的大小一致，会出现帧序号混乱，无法区分新帧还是旧帧
- 选择ARQ协议
  - 只重传出错帧
  - 接收方允许接受多个不严格按照顺序的帧
  - 接收窗口的最大值W<=2^(n-1)
  - 发送窗口一般和接受窗口保持一致
  - 如编号为0~7，则接收窗口最大为4
- 点到点信道的数据链路层协议
  - 面向字符（PPP）
  - 面向比特（HDLC，高级数据链路控制）
  - HDLC
    - 帧结构
      - 长帧：F+A+C+I+FCS+F
      - 短帧：F+A+C+FCS+F
      - F：标志字段（开始和结束），共8位（01111110）
      - 0比特插入/删除法——未避免两个F之间的比特组合被误认为是帧边界，需要在5个连续的1后面插0后再发送，接收方将连续5个1后的0删除还原成原来的比特流
      - 0比特插入/删除法可以实现任意比特组合的传输，即认为实现了透明传输
      - A：地址字段
      - C：控制字段，将帧分为信息帧，监督帧，无编号帧
      - I：信息字段
      - FCS（Frame Check Sequence）:帧校验序列，16位
    - 帧类型——由控制字段决定（b7b6b5b4b3b2b1b0）
      - 信息帧(I)——b0=0，N(S)——b3b2b1:表示当前正在发送的帧的编号，N(R)——b7b6b5:表示N(R)之前的帧都已正确接收，希望接下来接收第N(R)帧
      - 监督帧(S)——b1b0=01，只作为应答用，没有N(S)，只有N(R)
        - b3b2=00：RR，接收准备好
        - b3b2=01：RNR，接收未准备好——本站“忙”
        - b3b2=10：REJ，拒绝——用于连续ARQ方式，要求重发N(R)及以后的帧
        - b3b2=11：SREJ，选择拒绝——用于选择ARQ方式，要求只重传N(R)指定的帧
      - 无编号帧(U)——b1b0=11，没有N(R)和N(S)，主要用于链路的管理和异常情况的处理
  - PPP——(Point-to-Point Protocol，点到点协议)
    - 面向字符，长度为整数字节，面向连接的协议
    - 组成
      - 将IP数据报封装到串行链路的方法
      - LCP（链路控制协议）
      - NCP（网络控制协议）
    - 帧格式
      - F+A+C+协议+信息+FCS+F
      - F：标志字段(7E)
      - A：地址字段(FF)
      - C：控制字段(0x03)
      - 协议：2字节，0x0021——I信息字段为IP数据报，0xC0021——链路控制数据，0x8021——网络控制数据
      - FCS：2字节 
    - 同步通信——0比特插入删除法
    - 异步通信——字符填充法
      - 信息字段的7E变为7D 5E
      - 信息字段的7D变为7D 5D
      - 信息字段小于0x20，在该字符前加7D
- 多路访问信道的数据链路层
  - 信道共享技术
    - 信道划分
    - 轮询
    - 随机访问
  - 所有站点都连接到一个共享信道上，所用的接入和共享信道的技术称为多点接入控制方法，又称介质访问控制(MAC)
  - 竞争系统的介质访问控制
    - ALOPHA
      - 纯ALOPHA——用户有数据要发送就让他们传输，信道利用率最大为18.4%
      - 时隙ALOPHA——将时间分成离散的时隙，只允许每一帧在时隙开始的时传输，减少了两帧部分重叠引起的冲突，最大利用率36.8%
    - CSMA(Carries Sense Multiple Access，载波监听多路访问)——发送前检测网络状态，忙则不发送
    - 非坚持性——发现信道忙则不再持续侦听，等待一个随机长的时间后，再重新开始侦听/发送过程
    - 1-坚持性——发现信道忙则持续侦听，直至信道空闲;等到信道空闲后发送数据（概率为1）
    - p-坚持性——发现信道忙则持续侦听，直至信道空闲;等到信道空闲后，以概率 P 发送数据，或以(1－P)概率推迟发送
    - 以上3种CSMA机制仍然不能完全避免多个站点发送数据的冲突
    - CSMA/CD(Collision Detection，冲突检测)
      - 载波监听
      - 冲突检测
      - 多路访问
      - 帧开始发送后就开始检测有无冲突发生——边发边听，检测到冲突，冲突方必须停止发送
  - 环型网介质访问方法
    - 无冲突的介质访问控制方法，属于分布式轮询控制方式
    - 令牌环
    - 环接口：监听方式和收发方式
    - 特点：各个站点串成闭合环路，同一时刻环中只有一个令牌，环内令牌数据单向传输，站点获得空闲令牌才能发送数据帧，分散控制，无冲突
    - 如何防止数据帧在环上无休止循环？设置监控器;在帧结构中留一个标志
    - 如何监测令牌出错？无令牌，多个令牌，忙令牌死循环
    - 时隙环
    - 寄存器插入环
  - 令牌总线介质访问方法
    - IEEE 802.4
    - 物理上总线型，逻辑上是环型
    - 具有总线网络的可靠性，具有令牌网络的优点，但是算法复杂，维护困难
  - 无线局域网介质访问方法(CSMA/CA)

## 第4章——局域网和广域网
#### 局域网基本概念
- LAN——将分散在一个局部地理范围内的多台计算机通过传输媒体链接起来的通信网络
- 特点
  - 具有专用性质
  - 高速传输
  - 拓扑结构简单
- 传输媒体
  - 有线
  - 无线
- 传输技术
  - 基带传输，通常采用
  - 宽带传输
- 介质访问控制技术
  - 采用随机访问控制的方法，CSMA/CD
- IEEE 802系列标准，将局域网数据链路层分为2个子层：逻辑链路控制子层(LLC)和媒体介质控制子层(MAC)
  - LLC与传输媒体无关

#### 以太网
- 概述
  - 是以CSMA/CD方式工作的一种总线式局域网
- 以太网MAC层
  - MAC地址(硬件地址，物理地址，网卡地址)：48位，分为机构唯一标识符和拓展标识符
  - MAC帧格式——DA(6)+SA(6)+T(2)+DATA+PAD+FCS(4)
    - DA:目的地址，SA:源地址，各6个字节
    - T:类型字段
    - DATA:数据字段，46~1500字节
    - PAD:填充字段，<=46字节，采用填充字符来保证MAC帧长不小于64字节
    - FCS:帧校验序列，4个字节
    - 在发送一个MAC帧之前，还会发送7字节前导符和1字节帧开始符。帧长不包括这些
  - CSMA/CD工作过程
    - 载波监听，冲突检测，多次访问
    - 发送方工作过程
      - MAC协议将LLC帧封装在用户数据字段，形成MAC帧
      - 监听传输媒体，检测是否有信号正在传输
      - 有信号则继续监听，否则发送数据，同时继续监听
      - 发送过程无冲突，则发送完成，否则立即终止本次发送
      - 发送次数小于阈值，退避算法计算退避时间，重新发送。否则停止发送，报告网络故障
    - 接收方工作过程
      - 监听传输媒体，有信号则接收信息，得到MAC帧，对于残帧不予理会
      - 分析目的地址，为本站则复制该帧，否则丢弃
      - 组播和广播的数据帧会有多个站点接收
  - 争用期（2τ）——端到端往返时延，实际所取的争用期大于端到端往返时延
  - 以太网的连接方法
    - 双绞线
    - 集线器(HUB)——用于连接网络工作站，实现集中汇接，工作在物理层
    - 10BASE-T(双绞线)——10：10Mb/s，T：双绞线，BASE:电缆上的信号是基带信号，曼切斯特编码
    - 10BASE-F(光纤)

#### 局域网的拓展
- 物理层拓展，集线器连接，造成更大的冲突域，最大吞吐量没有提高
  - 扩大了局域网覆盖的地理范围，增加用户数
  - 冲突概率增加，CSMA/CD机制失效
- 链路层拓展
  - 网桥——根据MAC帧的目的地址对收到的帧进行转发
  - 网桥具有过滤帧的功能——先检查帧的目的MAC地址，然后转发
  - 网段A——网桥——网段B，目的MAC地址属于同一网段，丢弃；属于另一网段，向特定端口转发；地址表中没有或者为广播地址，则向其他端口广播
  - 优点
    - 过滤通信量
    - 扩大物理范围
    - 提高可靠性
    - 互连不同物理层、不同MAC子层和不同速率的局域网
  - 缺点
    - 查找存储转发表，导致转发延时
    - 在MAC子层没有流量控制功能
    - 不同MAC子层的网桥连接在一起，协议转换增加处理时延
    - 适合规模不太大的局域网
  - 透明网桥
    - 指局域网上的站点并不知道锁发送的帧经过哪几个网桥
    - 
  - 源路由网桥
    - 缺乏透明性
  - 以太网交换机——交换式集线器，第二层交换机，工作在数据链路层
    - 实质是多端口网桥
    - 用户在通信时独占带宽
    - 工作方式（3种）
      - 直通交换方式——接收到数据帧就立即按目的MAC地址转发，提高了转发速率。缺点是不检查出错帧
      - 存储转发方式——完全接收和缓存下来，再按目的MAC地址转发。传输延迟大
      - 无碎片交换方式——收到64字节以后才开始以直通方式转发帧，避免了碎片帧，但可能转发差错帧

#### 高速以太网
- 速率在100Mb/s以上
- 100BASE-T
- 千兆以太网
- 万兆以太网——光纤

#### 虚拟局域网(VLAN)
- 是在现有局域网提供的划分逻辑组的一种服务，与物理位置无关
- 在以太网的帧格式中插入VLAN标记的4字节标识符——用来指明发送该帧的工作站属于哪一个虚拟局域网，实现逻辑组的划分
- 帧最大长度变为1522字节

#### 无线局域网
- 是以无线方式实现局部范围内的多台计算机互相通信的一种局域网技术
- CSMA/CA（冲突避免）
  - 原因
    - 无线终端处理能力相对较弱，不适合边发边检测
    - 存在隐蔽站问题和暴露站问题
  - 在CSMA基础上增加冲突避免和确认机制，用以确定移动站什么时间能发送和接收数据
  - 标准规定，所有站在完成发送后，必须等待一段很短的时间才能发送下一帧。称为帧间间隔
  - 帧间间隔
    - SIFS——短帧间间隔
    - PIFS——点协调功能帧间间隔
    - DIFS——分布协调功能帧间间隔
  - 避免冲撞
    - 信道预约，RTS(Request To Send，请求发送)，CTS(Clear To Send，允许发送)，收到CTS帧后允许发送，但仍会有冲突。使用二进制退避算法

#### 广域网
- 广域网是用来实现长距离传输数据的网络，由节点交换机和链路构成。
- 分类
  - 无连接的网络服务，数据报服务
  - 面向连接的网络服务，虚电路服务
- 数据报服务
  - 不可靠，不能保证服务质量
  - 网络随时都可接收主机发送的分组
  - 网络为每个分组独立地选择路由
  - 网络交付的分组不保证顺序，不保证不丢失
- 虚电路服务
  - 需要建立连接，数据传输，连接释放的过程
  - 服务质量有保证
  - 属于同一虚电路的分组使用同一路由进行转发
  - 保证顺序到达
- 广域网中的分组交换
  - 主机地址——交换机编号+交换机端口编号，[1,3]表示与交换机1的端口3相连的主机  
  - 分组转发机制
    - 分组交换机在接收到一个分组时，查找分组交换机中转发表，确定应该转向的下一条
    - 交换机是根据目的站的交换机号来进行路由转发的
  - 默认路由
    - 将具有相同下一条的条目进行合并作为默认路由，但是比其他项目的优先级低
- X.25分组交换网
  - 向高层提供面向连接的虚电路服务
  - 分组层
  - 数据链路层
  - 物理层
- 帧中继
  - 虚电路服务，被称为第二代的X.25
  - 优点
    - 减少了网络互连的代价
    - 提高了性能
    - 增加了互操作性
    - 协议的独立性
- ATM（异步传递方式，Asynchronous Transfer Mode）
  - 是建立在电路交换和分组交换的基础上的一种面向连接的快速分组交换技术
  - 元素
    - ATM端点
    - ATM交换机
  - 物理层
  - ATM层——完成交换和复用，以及流量控制
    - 虚通路标志 VCI（Virtual Channel Identifier）——两个或以上端点之间的运送ATM信元的通路
    - 虚通道标识符 VPI（Virtual Path Identifier）——包含有许多相同端点的虚通路，这些虚通路都使用同一个VPI
  - ATM适配层，AAL
    - 增强ATM所提供的服务，并向上高层提供各种不同的服务

## 第5章 网络层和网络互连
#### 网络层概念
- 网络互联设备
  - 物理层：转发器
  - 数据链路层：网桥或交换机
  - 网络层：路由器
  - 传输层及以上：网关

#### 网络互连
- 因特网IP层的服务是一个尽最大努力传送的，不可靠，无连接的数据报存储转发的分组交换系统，IP（Internet Protocol网际协议）
- IP地址
  - A：0...(8+24)网络号2~126
  - B：10...(16+16)
  - C：110...(24+8)
  - D：1110...
  - E：11110...
- IP地址管理机构在分配IP地址时只分配网络号
- 用转发器或网桥(交换机)连接起来的局域网，逻辑上仍然是一个网络，具有相同的网络号
- 地址解析协议（ARP）——为网络层地址和数据链路层地址提供动态映射
  - 主机A向主机B发送IP数据报，先在其高速缓存表中查看有无主机B的地址，如有，查看其硬件地址，如无，则广播一个ARP请求报文
- 因特网的基本传送单元是IP数据报
  - IP数据报：首部(20字节)+[可选字段]+数据，可选字段（1~40字节）
  - IP数据报首部的分析
- 无连接的数据报传送
  - 路由器实际上上一种通信专用的计算机，CPU+存储器+接口
  - 功能：网络分段，隔离广播风暴，实现子网之间的信息传递，提供安全访问的机制
  - 直接交付——目的主机和本机在同一个物理网络上
  - 间接交付——获取路由器的物理地址，交付给路由器
  - IP数据报转发算法
    - 从数据报取出目的站地址I
    - 表中包含I的特定路由
    - 与直接相连的网络地址匹配，交给目的站
    - 表中包含到网络N的路由
    - 表中包含默认路由
    - 向源站报告，目的不可达
  - 主机收到数据报
    - 取出目的地址
    - 匹配，交给高层处理
    - 不匹配，丢弃
  - 路由器收到数据报
    - 取出数据报中的目的IP地址
    - 和任一个物理网络连接的IP地址匹配，是广播地址，则接收
    - 不匹配，将首部的生存时间TTL减1
    - TTL=0，丢弃，向源站发送超时差错报告
    - TTL>0，重新计算校验和，并转发数据报

#### ICMP(Internet Control Message Protocol，因特网差错和控制协议)
- ICMP报文
  - 前三个字段格式统一：类型(1字节)+代码(1字节)+校验和(2字节)
  - 差错报告报文
  - 提供信息的报文
- 目的不可达报文
  - 类型为3
  - 代码部分
    - 0——网络不可达
    - 1——主机不可达
    - 2——协议不可达
    - 3——端口不可达
    - 4——需要分片，但DF标志置位
    - 5——源路由失败
- 超时报文
  - 类型为11
  - 代码部分
    - 0——传输过程IP TTL(time to live)超时
    - 1——分片重装超时
- 回应请求和应答报文
  - PING（Packet InterNet Groper）
  - PING 用于测试两个主机之间的连通性
  - 类型值为8——回文请求报文
  - 类型值为0——回应应答报文

#### IP编址技术
- 子网编址
  - IP地址：网络号+子网号+主机号
  - 子网掩码——网络号、子网号对应位为1，主机号对应位为0
  - 子网地址 = 子网掩码 & IP地址
  - 子网划分
  - 子网转发
    - IP地址和子网掩码相与，
    - 和目的地址匹配，直接交付
    - 发往下一跳地址
    - 没找到，向源站发送目的不可达差错报告
- CIDR(Classless Inter-Domain Routing，无分类域间路由选择)
  - 网络前缀+主机号
  - IP地址后加‘/’，写上网络前缀所占的比特数
  - 前缀长度不超过23bit的CIDR地址块都包含了连续的C类地址，这些C类地址合起来构成了超网
  - 路由查找算法
    - 最长前缀匹配
    - 二叉线索树
- 专用IP地址
  - IANA保留了三块只能用于专用互联网内部通信的IP地址
  - 10.0.0.0~10.255.255.255
  - 172.16.0.0~172.31.255.255
  - 192.168.0.0~192.168.255.255

#### 路由选择协议
- 自治系统与路由选择协议的分类
  - 路由算法分类
    - 静态路由选择策略——不能动态地适应网络变化
    - 动态路由选择策略——自适应路由选择，实现较为复杂
  - 路由选择协议
    - IGP（Interior Gateway Protocol）自治系统内部路由信息所用的任何协议统称
    - EGP（External Gateway Protocol）外部，两个自治系统之间传递网络可达性信息所用的协议统称
- 内部网关协议RIP
  - Routing Information Protocol，基于距离向量算法，距离也称跳数，每经过一个路由器，跳数加1
  - 距离短，路由好，一条路径最多包含15个路由器，16相当于不可达
  - 路由信息交换特点
    - 一开始，各路由器表只有到相邻路由器的信息
    - 每个路由器只和相邻的路由器交换并更新路由信息，
  - 使用UDP进行传送，端口号为520
  - 应用层协议
  - 缺点：网络出现故障时，需要较长时间才能将信息传送到所有的路由器
  - 优点：实现简单，开销较小
- 内部网关协议OSPF
  - 最短路径优先算法
  - 使用距离向量路由选择协议时——路由器依赖于邻居的路由选择策略
  - 使用链路状态路由选择协议时——每台路由器都完全了解网络拓扑
  - OSPF将一个自治系统分为若干个更小的范围，叫做区域
    - 骨干区域
    - 非骨干区域
    - 区域内部的路由器只知道本区域的完整网络拓扑
    - 利用洪泛法交换链路状态信息的范围局限于每一个区域
  - 直接用IP数据报传送，OSPF位于网络层
  - OSPF分组类型
    - 问候(Hello)分组——用于发现邻居，建立邻接关系
    - 数据库描述分组
    - 链路状态请求分组
    - 链路状态更新分组
    - 链路状态确认分组
  - OSPF规定每隔一段时间要刷新一次数据库中的链路状态
- 外部网关协议BGP
  - BGP报文
    - Open报文
    - Keepalive报文
    - Update报文
    - Notification报文
  - 特点
    - 建立TCP连接
    - 是一个路径向量协议
    - 支持CIDR
    - 采用增量更新节约网络带宽

#### IP组播
- 组播(Multicast)——是一种对于某种特定组成员的广播，有限范围的广播形式
- IP组播是对硬件组播的抽象
- 组播可明显减少网络中资源的消耗
- IP使用D类地址支持组播，组播地址只能用于目的地址
- IGMP（Internet Group Management Protocol，网际组管理协议），使用IP数据报传输。是网际协议IP的一个组成部分
- 网1（支持组播）——网2（支持组播），两者之间使用隧道技术，隧道中通行的单播IP数据报

#### 移动IP
- 特征
  - 宏观移动性
  - 透明性
  - 与IPv4互操作性
  - 物理广泛性
  - 安全性
- 归属地址(主地址)
- 转交地址(次地址)

#### VPN和NAT
- VPN——虚拟专用网络
  - VPN中任何一对计算机之间的通信对外接是隐藏的
  - 存在两个网络
    - 物理网络
    - 逻辑网络——建立在物理网络基础之上
  - 使用的技术
    - 隧道技术(IP-in-IP封装)
    - 加密技术——对转发的数据报进行加密
- NAT——网络地址转换(Network Address Translation)
  - 两种NAT方法
    - 基本网络地址转换(NAT)——并发访问特定外部地址存在限制
    - 网络地址和端口转换(NAPT)——通过对TCP和UDP端口以及IP地址的转换允许并发访问
  - 缺点：通信仅限于TCP和UDP
  - 优点：能够仅用一个全球有效地址获得通用性、透明性和并发性

## 第6章——传输层
#### 传输服务
- 传输层作用
  - 应用进程通信
  - 差错校验
  - 协议
  - 合理传输服务
  - 服务原语

#### 传输层编址
- 端口是应用层进程的标识
- 常用端口
- 套接字(Socket)——IP地址和端口号的组合
- 无连接
  - 传输层：UDP
  - 网络层：IP
  - 数据链路层：CSMA/CD
- 面向连接
  - 传输层：TCP
  - 网络层：X.25分组级
  - HDLC，PPP

#### UDP协议
- UDP只在IP数据报服务至上增加了很少的功能，即端口的功能和有限的差错检测
- 无连接，不可靠，面向报文，同时支持点到点和多点之间
- UDP格式
  - 首部：源端口(2字节)+目的端口(2字节)+长度(2字节)+检验和(2字节)+数据
  - IP数据报的数据=UDP首部+UDP数据
  - IP数据报=IP首部+IP数据
  - 伪首部——只参与计算UDP校验和，既不向下层传送，也不向上层递交。在UDP首部之前增加12字节（源IP地址（4）+目的IP地址（4）+0（1）+协议（1）+长度（1））
- 校验和计算
  - 伪首部参与计算
  - 进位加至末尾位
  - 累加结果取反码即为校验和

#### TCP协议
- 面向连接，可靠传输，只能进行点到点通信，面向字节流
- TCP首部格式
  - 前20字节固定
  - 源端口(2)+目的端口(2)
  - 序号(4)
  - 确认号(4)——期望收到下一个报文段的数据的第一个字节的序号
  - 数据偏移(4bit)+保留+标志位(6bit)+窗口(2)
  - 检验和(2)+紧急指针(2)
  - 选项(0~40字节)
- 三次握手——建立连接
  - 连接过程(图)
  - 为什么采用三次握手
- 四次握手——连接释放
  - 半关闭
  - 双向关闭
  - 连接释放过程(图)
- 可靠传输保证
  - 序号确认机制
  - 超时重传机制
  - 定时器设置
- 超时重传机制
  - RTT自适应算法
  - 平均往返时延计算
- 定时器设置
  - 重传定时器
  - 持续定时器
  - 保活定时器
- TCP流量控制
  - 大小可变滑动窗口
  - 接收窗口
  - 发送窗口
- TCP拥塞控制
  - 避免网络发生拥塞，或者缓解已经发生的拥塞
  - 传输层实现
  - 慢启动
  - 拥塞避免
  - 快速重传
  - 快速恢复
- TCP相关内容多看例题     

## 第7章——应用层
- 网络应用模式
  - 以大型机为中心
  - 以共享服务器为中心
  - 客户机-服务器
  - 基于web的客户机-服务器
  - 对等网络应用模式(Peer-to-Peer)
- 域名系统(DNS，Domain Name System)
  - 域名到IP地址的解析
  - 域名
- 远程登录(Telnet)  
- 文件传输协议(FTP)
  - 客户服务器方式
  - 主进程+从属进程，两者并发工作
  - 控制接连+数据连接
- 简单文本传输协议(TFTP)
  - UDP数据报，需要有自己的差错改正措施
  - 工作原理
    - 数据PDU也称文件块，块按序编号
    - 每次传送的数据PDU不超过512字节
- 引导程序协议(BOOTP)
- 动态主机配置协议(DHCP)
  - 现在DHCP服务器数据库中查找该主机配置信息，有则采用提供报文将其会送到主机；找不到，则从服务器IP地址池中任选一个分配给主机
- 简单邮件传输协议(SMTP)
  - 用于发送邮件
  - 三个阶段
    - 连接建立
    - 邮件传送
    - 连接释放
- 邮局协议(POP3)
  - 用于接收邮件
  - 用户读取，POP3服务器就将该邮件删除
- 因特网报文存取协议(IMAP4)
  - 用于接收邮件
  - 用户读取，仍然保存收到的邮件
- 通用因特网邮件拓展(MIME)
  - 增加了SMTP的功能，加入了图像视频附件等数据内容
- 超文本传输协议(HTTP)
  - URL——统一资源定位符，定位文档
  - 使用TCP连接进行的可靠传送
  - 无状态协议
  - 面向事务的客户服务器协议
  - 请求报文和响应报文

## 第8章——网络管理和网络安全
- 网络管理5大功能域
  - 配置管理
  - 性能管理
  - 故障管理
  - 计费管理
  - 安全管理
- 简单网络管理协议(SNMP)
- 网络安全威胁因素
  - 人为
  - 自然
- 加密算法
  - 对称加密
  - 非对称加密

## IPv6
- 128位
- 16进制+冒号(:)
- 零压缩，一个地址中只能用一次
- 地址种类和格式
- IPv4到IPv6过渡
  - 双协议栈
  - 隧道技术