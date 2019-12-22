package com.learn.hanjx.scriptengine;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
/**
 * javascritp 引擎 测试
 */
public class ScriptDemo
{
    /**
     * @param jsStr    js脚本内容
     * @param function 要调用的js方法名
     * @param args     调用js方法时传入的参数
     * @return
     */
    public static Object jsObjFunc(String jsStr, String function, Object... args)
    {
        System.out.println(jsStr);
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine scriptEngine = sem.getEngineByName("js");
        Bindings bindings = scriptEngine.createBindings();
        bindings.put("name","123");
        try
        {
            scriptEngine.eval(jsStr);
            Invocable inv2 = (Invocable) scriptEngine;
            return inv2.invokeFunction(function, args);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception
    {
        //注入java 方法
        String jsInit =String.format("var JavaFunction = Java.type('%s');",JavaFunction.class.getName());
        //注入 js脚本
        String jsStr = "function myFuc(param){return 'param:'+param+'、JavaFunction:'+JavaFunction.plus(1,2);}";//js脚本内容
        System.out.println(jsObjFunc(jsInit+jsStr, "myFuc", "test"));
    }
}

