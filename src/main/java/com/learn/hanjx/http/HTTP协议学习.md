#HTTP

##HTTP协议

    应用层：
 - HTTP请求报文
 - HTTP请求方法
 - HTTP响应报文


##TCP协议

    传输层：三次握手，提供可靠的字节流服务
##IP协议

    网络层：使用IP地址、MAC地址确定网络地址

##DNS

` dns服务是和http协议位于应用层的协议，它提供域名到ip地址之间的解析服务
`
##HTTPS协议

    http+SSL(Secure Sockets Layer 安全套接层)
    SSL位于传输层
    

##网络安全，按照突破点分为：


 ###服务器
 
   - SQL注入
   - OS指令注入
#####  解决方案
   - 服务器 校验 
 ###客户端
 
   - 用户运行第三方脚本，无法限制 
   - 跨站脚本攻击(Cross-Site Scripting ,XSS)
#####  解决方案:
   - cookie限制http only；
   - 提醒用户不要运行不安全脚本
   - 前后端校验，避免输入非法内容，如可执行js脚本
 ###网络传输
 
   - Http 窃听    
   - Http 篡改 
#####  解决方案
   - HTTPS加密传输   

