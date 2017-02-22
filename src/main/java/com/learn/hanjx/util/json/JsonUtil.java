package com.learn.hanjx.util.json;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.hanjx.jpa.bean.Person;

public class JsonUtil {
	  /**
     * jackson实现object转json
     * @param obj
     * @return String
     */
    public static String createJsDataByJackson(Object obj)
    {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try
        {
            result = mapper.writeValueAsString(obj);
        }
        catch (JsonGenerationException e)
        {
            // TODO 异常处理
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * jackson实现object转json
     * @param obj
     * @return String
     */
    public static <T> T createObjectByJackson(String json, Class<T> valueType)
    {

        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try
        {
            t = (T) mapper.readValue(json,valueType);
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return t;
    }
    public static void main(String[] args) {
    	//jackson有很多强大的注解，可以规定json格式，json解析策略
    	// jackson annotations注解详解
    	Person person = new Person();
    	person.setId(57);
    	person.setBirthDay(new Date());
    	person.setInfo("remark");
    	person.setName("xiaoMing");
		String json  = createJsDataByJackson(person);
		System.out.println(json);
		person = createObjectByJackson(json, Person.class);
		System.out.println(person);
		
		String jsonstr = "{\"id\":57,\"name\":\"xiaoMing\",\"gender\":\"MAN\",\"info\":\"\\{\"abc\":57\\}\",\"filebyte\":null,\"imagepath\":null,\"idcard\":null,\"BIRTH_DAY\":1487758117137}" ;
		person = createObjectByJackson(jsonstr, Person.class);
		System.out.println(person);
	}

}
