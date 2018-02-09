package com.learn.hanjx.T;
/**
 * 如果你试图获取泛型参数的具体类型，你还不如不用泛型，而直接把类型对象作为一个变量传进来。
泛型的魅力就在于对于它支持的类型“一视同仁”。
这位仁兄说的对头，之所以只用泛型就是因为在某些地方我们不知道或者不想知道具体的参数类型信息而笼统的把他认为是某个类型T/E之类的，
 这样能够是方法变得灵活通用等等；
 */
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericDAO<T> {
	public static void main(String[] args) throws Exception {
		OptionManager manager = new OptionManager();
		GenericDAO manager2 = new GenericDAO<String>();
		System.out.println(GenericDAO.getSuperClassGenricType(manager2.getClass(), 0));
		GenericDAO manager3 = new GenericDAO<String>(new String() );
	}

	private Class<T> entityClass;

	protected GenericDAO() {
		/*
		 * ParameterizedType是表示带有泛型参数的类型的Java类型，
		 * JDK1.5引入了泛型之后，Java中所有的Class都实现了Type接口，
		 * ParameterizedType则是继承了Type接口，所有包含泛型的Class类都会实现这个接口。
		 */
		System.out.println("this:" + this.getClass());//no use
		T t = (T) new Object();
		System.out.println("T t :" + t.getClass());// no user
		Type type = getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType){
			Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
			System.out.println("constractor Type:" + trueType);
			this.entityClass = (Class<T>) trueType;
		}
	}
	protected GenericDAO(T t) {
		System.out.println("constractor Type:" + t.getClass());
	}

	public static Class getSuperClassGenricType(Class clazz, int index) {

		System.out.println(GenericDAO.class.getGenericSuperclass());
		Type genType = clazz.getGenericSuperclass();// 得到泛型父类

		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if (!(genType instanceof ParameterizedType)) {

			return Object.class;
		}
		// 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
		// DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {

			throw new RuntimeException("你输入的索引"
					+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		if (!(params[index] instanceof Class)) {

			return Object.class;
		}
		return (Class) params[index];
	}

}

class OptionManager extends GenericDAO<String> {
}
