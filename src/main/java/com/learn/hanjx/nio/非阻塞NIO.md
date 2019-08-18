#NIO技术概述

##Channel 通道

   ####特点
   `非阻塞，双向性；传统流是单向、阻塞的`

   ####JDK 具体实现
    file  UDP  TCP

##Buffer缓冲区
    一块内存，用来读写channel的数据
###属性    
* 容量Capacity 
* 位置Position 
* Limit上限 
* Mark标记

    

##Selector 多路复用器
#### IO就绪选择 NIO编程模型核心

##SelectionKey 选择键
`四种就绪状态常量`

#NIO DEMO概述

`多人聊天室`

##NIO服务器端
1.  创建Selector
2.  通过ServerSocketChannel创建channel通道
3.  为channel通道绑定监听端口
4. 设置channel为非阻塞模式
5. 将channel注册到selector上，监听连接事件  
6. 循环等待新接入的连接
7. 根据就绪状态，调用对应方法处理业务逻辑
8. 接入事件处理器、可读事件处理器
##NIO客户端

1. 连接服务器端（SocketChannel）
2. 非阻塞接收服务器端响应（Selector）
3. 向服务器端发送数据





