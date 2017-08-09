package com.learn.Try.T2017.T08;

/**
 * Created by han on 2017/8/9.
 */
public class ReplaceAll
{
    //<![CDATA[abc]]>
    //\<\!\[CDATA\[(?<text>[^\]]*)\]\]\>
    public static void main(String[] args)
    {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><TextFilter><FirstRowPos><![CDATA[function{T_T}]]></FirstRowPos><MaxRowCount>1000</MaxRowCount><Projection><Pi>CREATE_UNIT_CODE</Pi></Projection><Projection><Pi>COUNT(CREATE_UNIT_CODE)</Pi></Projection><From><Table>MIS_PERSONS</Table></From><GroupBy>CREATE_UNIT_CODE</GroupBy><Having>COUNT(CREATE_UNIT_CODE)&amp;lt;0</Having><OrderBy><Oi Order=\"DESC\">CREATE_UNIT_CODE</Oi></OrderBy><Where><SimpFilter><Group><ColNode Col=\"CREATE_TIME\" Op=\"btwn\" Dt=\"b\"><Arg Lt=\"const\" Dt=\"dt\">20150101</Arg><Arg Lt=\"const\" Dt=\"dt\">20150101</Arg></ColNode></Group></SimpFilter><ComplexFilter></ComplexFilter></Where></TextFilter>";
        String regex = "\\<\\!\\[CDATA\\[(?<text>[^\\]]*)\\]\\]\\>";
        s.replaceAll(regex,"****");
        System.out.println(s);
    }

}
