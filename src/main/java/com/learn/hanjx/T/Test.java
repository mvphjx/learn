package com.learn.hanjx.T;
/**
 * 一个例子全部说明java泛型中的
 * K,V,T,E,?,object的意思及其用法
 * K表示键，V表示值，T表示type类型，E表示enum枚举，其实这四个都只是符号，都是表示泛型名称,
 * 下面的例子的T全部可以换成E，也可以换成K,V,hanjx，都没关系。
 */
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Vector;
//T1,T2都是随便定义的东西，注意1:他们不会关联到其他类，只是在本类中通用，只是告诉我们new的时候要加入泛型  
public class Test<T1, T2> {  
    public static void main(String[] args) {  
        System.out.println(new Test().getaa());  
        
        Test<String, String> test = new Test<String, String> ();  
        test.getaa();
        test.getbb("");
        System.out.println("get实例类型String, String:->"+test.getClass().getTypeParameters()[0]);
        System.out.println("get实例类型String, String:->"+test.getClass().getName());
        
        
        
        new Test().getcc(Test.class);  
        //注意下6:面这个HashMap的括号里面不能是T,E,T1,T2等不确定的东西,但可以是?  
        HashMap<Object, String> map = new HashMap<Object, String>();
        HashMap<?, ?> map2 = new HashMap<Object, String>(); 
        List<?> list = new ArrayList<String>();  
    }  
  
    T2 getaa() {  
    	Object obj = new Object();
    	System.out.println( " 类内部 get实例类型"+((T1)obj).getClass());
        //注意2:T2将自动转型为String,这个不需要去担心  
        return (T2) "few";  
  
    }  
  
    public <T> void getbb(T x) {  
        //注意3:Class<T>前面缺少<T>将编译错误  
        System.out.println("getbb:"+x.getClass().getName());
        
    }  
  
    public <T> Class<?>  getcc(Class<T> a) {  
        //getcc前面的Class<T>前面缺少<T>将编译错误,注意4:Class<?>里面的问号可以换成T  
        System.out.println("getcc:" +a.getClass().getName());  
        //注意5:参数里面的Class<T>最大的好处是如果方法里面定义了泛型，可以自动获取类型值，比如如下的List<T>可以自动获取到a的类型，不必强调死  
        //List<T> aa=new ArrayList<T>();  
        //System.out.println("getcc:"+aa);  
        return a;  
    }  
} 