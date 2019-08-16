#jdk 概述

###java.lang： 

   这个是系统的基础类，这个包是一个可以不用引入(import)就可以使用的包。
   例如：
   
    Object、Class、Thread、Exception、Error
    数据结构（原生、对象、数组）
    关键字
    
    
   
   

 -  annotation
 
    注解 @since 1.5
    
 -  invoke 
 
    函数编程、lambda 相关 @since1.8
    
 -  management
    
    状态监控API
     
 -  ref
    
    reference 引用
 
 -  reflect
 
    反射、动态代理
    元数据：属性Field、方法Method、注解AnnotatedElement
     
           
###java.util
    
   这个是系统辅助类，特别是集合类Collection，List，Map等。
   
 -  concurrent 
 
    并发框架 @since 1.5
 -  function 
 
    函数接口 @since1.8
 *
###java.io    
   这里面是所有输入输出有关的类，比如文件操作等。
###java.nio    
   为了完善io包中的功能，提高io包中性能而写的一个新包 ，例如NIO非堵塞应用
###java.net    
   这里面是与网络有关的类，比如URL，URLConnection等。         
###java.sql    
   这个是数据库操作的类，Connection， Statement，ResultSet等。
###javax.servlet    
   这个是JSP，Servlet等使用到的类。
   
   ....待续

