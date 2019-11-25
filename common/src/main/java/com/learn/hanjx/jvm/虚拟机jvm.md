##经典的JVM内存布局

####Heap 堆
`新生代 老年代`

####Metaspace 元空间
`JDK8使用元空间代替永久代`

#### JVM Stack 虚拟机栈

`局部变量表
操作栈
动态连接
方法返回地址`

#### Native Method Stacks 本地方法栈

#### Program Counter Register 程序技术寄存器


#### 线程内存

从线程共享的角度看，堆和元空间是所有线程共享的，
而虚拟机栈、本地方法栈、程序计数器是线程内部私有的。











##最后汇总一下JVM常见配置

堆设置
-Xms:初始堆大小
-Xmx:最大堆大小
-XX:NewSize=n:设置年轻代大小
-XX:NewRatio=n:设置年轻代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
-XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
-XX:MaxPermSize=n:设置持久代大小
收集器设置
-XX:+UseSerialGC:设置串行收集器
-XX:+UseParallelGC:设置并行收集器
-XX:+UseParalledlOldGC:设置并行年老代收集器
-XX:+UseConcMarkSweepGC:设置并发收集器
垃圾回收统计信息
-XX:+PrintGC
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:filename
并行收集器设置
-XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数。并行收集线程数。
-XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间
-XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)
并发收集器设置
-XX:+CMSIncrementalMode:设置为增量模式。适用于单CPU情况。
-XX:ParallelGCThreads=n:设置并发收集器年轻代收集方式为并行收集时，使用的CPU数。并行收集线程数。


1）堆

所有通过new创建的对象的内存都在堆中分配，其大小可以通过-Xmx和-Xms来控制。堆被划分为新生代和旧生代，新生代又被进一步划分为Eden和Survivor区，最后Survivor由From Space和To Space组成，结构图如下所示：



新生代。新建的对象都是用新生代分配内存，Eden空间不足的时候，会把存活的对象转移到Survivor中，新生代大小可以由-Xmn来控制，也可以用-XX:SurvivorRatio来控制Eden和Survivor的比例
旧生代。用于存放新生代中经过多次垃圾回收仍然存活的对象
2）栈

每个线程执行每个方法的时候都会在栈中申请一个栈帧，每个栈帧包括局部变量区和操作数栈，用于存放此次方法调用过程中的临时变量、参数和中间结果

3）本地方法栈

用于支持native方法的执行，存储了每个native方法调用的状态

4）方法区

存放了要加载的类信息、静态变量、final类型的常量、属性和方法信息。可以被不同线程共享。
方法区 具体实现（jdk7 持久代Permanet Generation 、 jdk8 元空间Metaspace ），可通过-XX:PermSize和-XX:MaxPermSize来指定最小值和最大值




bug实战

-------------bin/JavaVisualVM 工具可以进行 heapdump（heap队dump内存镜像） threaddump的分析
设置JVM参数 收集dump文件 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp
1、Tomcat内存溢出，直接崩溃
      定位：查看崩溃日志，线程数正常；然后因为tomcat已经崩溃了无法查询其他相关信息，第一次简单粗暴解决；
      直接将内存从900+M调整为2G（我们设备内存4G），然后重启tomcat；重启后持续观察内存是否增长，查看内存回收情况、查看内存分配情况、dump内存文件进行分析，
      后来发现老生代持续增长且回收时消耗了很长时间只回收很少的内存；分析dump文件发现时有些静态的map持续增长没有清空导致的。
2、线程数过多导致内存溢出后无法创建新的线程从而程序崩溃
     怀疑原因：
     （1）线程执行过慢，导致最后阻塞过多；
     （2）在高峰期时，内存不够创建这么多线程（我们设备内存4G，后来调整成了2G；导致线程从最开始可以创建5000+后达到顶峰崩溃到只能创建2000+就崩溃了，具体开始为什么从1G调整成2G参考问题1），
     发现这个内存分配总是有利有弊的，需要长时间验证给出合理的分配才行，因为问题1已经解决，考虑到2G内存太大后来还是调整成了默认值；
     然后重启tomcat，实时观察线程数文件，看是否有阻塞问题，后来发现XStream将xml转对象时在进行new()操作时非常耗时从而导致阻塞。
3、打开文件数过多导致程序崩溃
      怀疑原因：
      （1）文件数不够，我们设置的文件数是4096，后来改成了12288；
      （2）因为我们服务器有socket通讯，很多客户端会进行连接，一个客户端会在短时间内发送N多socket消息，当客户端达到800左右就扛不住了；
      我们socket通讯用的是MINA,后来针对MINA开启的端口设置了并线程数为800，超过次数其他消息就进行丢弃，然后客户端再设置超时重传机制
4、MYSQL查询下载很慢、且CPU过高
    当时这张表的数据达到了百万条，且字段很多，下载文件为xls，且能根据时间排序、3000一组进行查询写入；
    此处有什么问题我就不描述了，反正是将整个sql重新优化了一下，排序 分组都很耗时


悲剧的是各种内存溢出线程数能达到5万+或者无缘无故内存溢出、打开文件数过多 、CPU过高也不行，查询或下载太慢也不行

