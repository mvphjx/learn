package com.learn.hanjx.design.facade;

/**
Fa莽ade锛堝瑙傦級

浣滅敤锛?
锛?锛夊皝瑁呬竴缁勪氦浜掔被锛屼竴鑷村湴瀵瑰鎻愪緵鎺ュ彛
锛?锛夊皝瑁呭瓙绯荤粺锛岀畝鍖栧瓙绯荤粺璋冪敤
JDK涓綋鐜帮細java.util.logging鍖?
 */
public class Test {
/*
 * 妯℃嫙鍦烘櫙锛岀敤鎴锋搷浣滆绠楁満
 */
	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.startup();
		computer.shutdown();
	}
}
