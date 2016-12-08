package com.learn.hanjx.jvm.gc;
/**
 * 为了回收没有用的对象，可以把对象引用赋值成为null
 * @author han
 *
 *since  jdk ArrayList
 */
public class LetGcWorkByNull {
/*
 * java语言的关键字，变量修饰符，如果用transient声明一个实例变量，当对象存储时，它的值不需要维持。换句话来说就是，用transient关键字标记的成员变量不参与序列化过程
 */
	private transient Object[] elementData;
	public Object remove(int index) throws Exception {
		int size = elementData.length;
		if (index >= size)
            throw new Exception();
		Object oldValue = elementData[index];
        int numMoved = elementData.length - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // Let gc do its work
        return oldValue;
    }
}
