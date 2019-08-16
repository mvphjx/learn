package com.learn.hanjx.design.memento;

/**
 * 备忘录对象
 * 当前对象快照，用于备份、还原
 */
public class Memento {
	
	private String value;

	public Memento(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
