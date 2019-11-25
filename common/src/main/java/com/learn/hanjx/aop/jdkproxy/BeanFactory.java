package com.learn.hanjx.aop.jdkproxy;
/**
 *   对于在配置文件中对某个bean配置前置或后置处理器，
 *   我们可以在bean中增加两个属性aop和aopType，
 *   aop的值为对应的前置顾问类或后置顾问类的名称，
 *   aopType用于指明该顾问类为前置还是后置顾问，
 *   为before时表示为前置处理器，为after时表示为后置处理器，
 */
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *bean工厂类.      
 */
public class BeanFactory {
    private Map<String, Object> beanMap = new HashMap<String, Object>();

    /**
     *bean工厂的初始化.
     *@paramxmlxml配置文件
     */
    public void init(String xml) {
       try {
           //读取指定的配置文件
           SAXReader reader = new SAXReader();
           ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
           InputStream ins = classLoader.getResourceAsStream(xml);
           if(ins==null){
        	   ins= new FileInputStream(xml); 
           }
           Document doc = reader.read(ins);
           Element root = doc.getRootElement();   
           Element foo;

           //创建AOP处理器
           AopHandler aopHandler = new AopHandler();

           //遍历bean
           for (Iterator i = root.elementIterator("bean"); i.hasNext();) {   
              foo = (Element) i.next();
              //获取bean的属性id、class、aop以及aopType
              Attribute id = foo.attribute("id");   
              Attribute cls = foo.attribute("class");
              Attribute aop_before = foo.attribute("aop_before");
              Attribute aop_after = foo.attribute("aop_after");
              Attribute aopType = foo.attribute("aopType");

              //配置了aop和aopType属性时，需进行拦截操作
              if ((aop_before != null ||aop_after!=null) && aopType != null) {
            	  //根据aopType的类型来设置前置或后置顾问
            	  if(aopType.getText()!=null&&aopType.getText().indexOf("before")>=0){
            		  //根据aop字符串获取对应的类
                      Class advisorCls = Class.forName(aop_before.getText());
                      //创建该类的对象
                      Advisor advisor = (Advisor) advisorCls.newInstance();
                      aopHandler.setBeforeAdvisor(advisor);
            	  }
            	  //根据aopType的类型来设置前置或后置顾问
            	  if(aopType.getText()!=null&&aopType.getText().indexOf("after")>=0){
            		  //根据aop字符串获取对应的类
                      Class advisorCls = Class.forName(aop_after.getText());
                      //创建该类的对象
                      Advisor advisor = (Advisor) advisorCls.newInstance();
                      aopHandler.setAfterAdvisor(advisor);
            	  }
              }

              //利用Java反射机制，通过class的名称获取Class对象
              Class bean = Class.forName(cls.getText());
              System.out.println("反射初始化:"+cls.getText());
              //获取对应class的信息
              java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(bean);
              //获取其属性描述
              java.beans.PropertyDescriptor pd[] = info.getPropertyDescriptors();
              //设置值的方法
              Method mSet = null;
              //创建一个对象
              Object obj = bean.newInstance();
              //遍历该bean的property属性
              for (Iterator ite = foo.elementIterator("property"); ite.hasNext();) {   
                  Element foo2 = (Element) ite.next();
                  //获取该property的name属性
                  Attribute name = foo2.attribute("name");
                  String value = null;

                  //获取该property的子元素value的值
                  for(Iterator ite1 = foo2.elementIterator("value"); ite1.hasNext();) {
                     Element node = (Element) ite1.next();
                     value = node.getText();
                     break;
                  }

                  for (int k = 0; k < pd.length; k++) {
                     if (pd[k].getName().equalsIgnoreCase(name.getText())) {
                         mSet = pd[k].getWriteMethod();
                         //利用Java的反射机制调用对象的某个set方法，并将值设置进去
                         mSet.invoke(obj, value);
                     }
                  }
              }

              //为对象增加前置或后置顾问
              obj = (Object) aopHandler.setObject(obj);

              //将对象放入beanMap中，其中key为id值，value为对象
              beanMap.put(id.getText(), obj);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    /**
     *通过bean的id获取bean的对象.
     *@parambeanNamebean的id
     *@return返回对应对象
     */
    public Object getBean(String beanName) {
       Object obj = beanMap.get(beanName);
       return obj;
    }

    
}