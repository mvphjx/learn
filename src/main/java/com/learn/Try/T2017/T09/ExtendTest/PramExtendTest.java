package com.learn.Try.T2017.T09.ExtendTest;

/**
 * Created by han on 2017/9/21.
 */

public class PramExtendTest implements INetUserLogin
{
    @Override
    public int test(Parent parent)
    {
        System.out.println(parent.name+"1");
        return 0;
    }

    @Override
    public int test(Children children)
    {
        System.out.println(children.name+"2");
        return 0;
    }

    public static void main(String []s){
        Parent parent= new Parent();
        Children children = new Children();
        PramExtendTest pramExtendTest = new PramExtendTest();
        pramExtendTest.test(parent);
        pramExtendTest.test(children);
    }


}

interface INetUserLogin
{
    int test(Parent parent);
    int test(Children children);
}
class Parent{
    public String name = "Parent";
}
class Children extends Parent{
    public String name = "Children";
}
