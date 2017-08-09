package com.learn.hanjx.design.composite;
/**
 * Composite（组合）
作用：一致地对待组合对象和独立对象
JDK中体现：
（1）org.w3c.dom
（2）javax.swing.JComponent#add(Component)
 */
public class Tree {

	TreeNode root = null;

	public Tree(String name) {
		root = new TreeNode(name);
	}

	public static void main(String[] args) {
		Tree tree = new Tree("A");
		TreeNode nodeB = new TreeNode("B");
		TreeNode nodeC = new TreeNode("C");
		
		nodeB.add(nodeC);
		tree.root.add(nodeB);
		System.out.println("build the tree finished!");
	}
}
