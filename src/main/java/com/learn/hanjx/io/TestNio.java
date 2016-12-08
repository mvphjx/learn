package com.learn.hanjx.io;

/**
 * Java NIO非堵塞技术实际是采取Reactor模式，
 * 或者说是Observer模式为我们监察I/O端口，
 * 如果有内容进来，会自动通知我们，这样，我们就不必开启多个线程死等，
 * 从外界看，实现了流畅的I/O读写，不堵塞了。
 * @author han
 *
 *
 *这是一个守候在端口9000的noblock server例子，如果我们编制一个客户端程序，就可以对它进行互动操作，或者使用telnet 主机名　9000 可以链接上。
 *win7开启telnet功能
 *telnet ip 9000  （非127.0.0.1）
 */
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;
import java.net.*;
import java.util.*;

public class TestNio {

	public void startServer() throws Exception {
		int channels = 0;
		int nKeys = 0;
		int currentSelector = 0;

		// 使用Selector
		Selector selector = Selector.open();
		// 建立Channel 并绑定到9000端口
		ServerSocketChannel ssc = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(
				InetAddress.getLocalHost(), 9000);
		ssc.socket().bind(address);

		// 使设定non-blocking的方式。
		ssc.configureBlocking(false);

		// 向Selector注册Channel及我们有兴趣的事件
		SelectionKey s = ssc.register(selector, SelectionKey.OP_ACCEPT);
		printKeyInfo(s);

		while (true) // 不断的轮询
		{
			debug("NioTest: Starting select");

			// Selector通过select方法通知我们我们感兴趣的事件发生了。
			nKeys = selector.select();
			// 如果有我们注册的事情发生了，它的传回值就会大于0
			if (nKeys > 0) {
				debug("NioTest: Number of keys after select operation: " + nKeys);

				// Selector传回一组SelectionKeys
				// 我们从这些key中的channel()方法中取得我们刚刚注册的channel。
				Set selectedKeys = selector.selectedKeys();
				Iterator i = selectedKeys.iterator();
				while (i.hasNext()) {
					s = (SelectionKey) i.next();
					printKeyInfo(s);
					debug("NioTest: Nr Keys in selector: "
							+ selector.keys().size());

					// 一个key被处理完成后，就都被从就绪关键字（ready keys）列表中除去
					i.remove();
					if (s.isAcceptable()) {
						// 从channel()中取得我们刚刚注册的channel。
						Socket socket = ((ServerSocketChannel) s.channel())
								.accept().socket();
						SocketChannel sc = socket.getChannel();

						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ
								| SelectionKey.OP_WRITE);
						System.out.println(++channels);
					} else {
						debug("NioTest: Channel not acceptable");
					}
				}
			} else {
				debug("NioTest: Select finished without any keys.");
			}

		}

	}

	private static void debug(String s) {
		System.out.println(s);
	}

	private static void printKeyInfo(SelectionKey sk) {
		String s = new String();

		s = "Att: " + (sk.attachment() == null ? "no" : "yes");
		s += ", Read: " + sk.isReadable();
		s += ", Acpt: " + sk.isAcceptable();
		s += ", Cnct: " + sk.isConnectable();
		s += ", Wrt: " + sk.isWritable();
		s += ", Valid: " + sk.isValid();
		s += ", Ops: " + sk.interestOps();
		debug(s);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		TestNio nio = new TestNio();
		try {
			nio.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}