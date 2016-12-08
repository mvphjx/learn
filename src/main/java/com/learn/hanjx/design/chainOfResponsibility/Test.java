package com.learn.hanjx.design.chainOfResponsibility;

/**
Chain of Responsibility（责任链）
作用：请求会被链上的对象处理，但是客户端不知道请求会被哪些对象处理
JDK中体现：
（1）java.util.logging.Logger会将log委托给parent logger
（2）ClassLoader的委托模型
 */
/*
JVM的ClassLoader分三层，分别为Bootstrap ClassLoader，Extension ClassLoader，System ClassLoader，
他们不是类继承的父子关系，是逻辑上的上下级关系。

Bootstrap ClassLoader是启动类加载器，它是用C++编写的，
从%jre%/lib目录中加载类，或者运行时用-Xbootclasspath指定目录来加载。

Extension ClassLoader是扩展类加载器，
从%jre%/lib/ext目录加载类，或者运行时用-Djava.ext.dirs制定目录来加载。

System ClassLoader，系统类加载器，它会从系统环境变量配置的classpath来查找路径，
环境变量里的.表示当前目录，是通过运行时-classpath或-Djava.class.path指定的目录来加载类。

 */
public class Test {

	public static void main(String[] args) {
		MyHandler h1 = new MyHandler("h1");
		MyHandler h2 = new MyHandler("h2");
		MyHandler h3 = new MyHandler("h3");

		h1.setHandler(h2);
		h2.setHandler(h3);

		h1.operator();
	}
}
