查询特殊符号ESCAPE定义转义符
在使用LIKE关键字进行模糊查询时，“%”、“_”和“[]”单独出现时，会被认为是通配符。
为了在字符数据类型的列中查询是否存在百分号（%）、下划线（_）或者方括号（[]）字符，
就需要有一种方法告诉DBMS，将LIKE判式中的这些字符看作是实际值，而不是通配符。
关键字ESCAPE允许确定一个转义字符，告诉DBMS紧跟在转义字符之后的字符看作是实际值。

    //查询10%  
    select  *  from  deptre    where  deptname LIKE '%10!%%' ESCAPE '!'  
    //查询_  
    select  *  from  deptre    where  deptname LIKE '%!_%' ESCAPE '!'          