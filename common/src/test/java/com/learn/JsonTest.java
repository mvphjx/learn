package com.learn;

import com.learn.hanjx.util.json.JsonUtil;

public class JsonTest
{
    /**
     * json  数组转为对象
     * @param args
     */
    public static void main(String[] args)
    {
        Demo demo = new Demo();
        String json = JsonUtil.createJsDataByJackson(demo);
        System.out.println(json);
        Demo[] demos = JsonUtil.createObjectByJackson("[{\"name\":\"a\",\"id\":\"007\"},{\"name\":\"b\",\"id\":\"007\"}]",Demo[].class);
        System.out.println(demos[1]);
    }

}
class Demo{
    public String name="a";
    public String id="007";

    @Override
    public String toString()
    {
        return "Demo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
