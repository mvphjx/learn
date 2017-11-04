Oracle基础函数使用

    --条件判断函数
    DECODE(条件，值1，翻译值1，值2，翻译值2，...值n，翻译值n，缺省值)
    IF 条件=值1 THEN
    　　　　RETURN(翻译值1)
    ELSIF 条件=值2 THEN
    　　　　RETURN(翻译值2)
    　　　　......
    ELSIF 条件=值n THEN
    　　　　RETURN(翻译值n)
    ELSE
    　　　　RETURN(缺省值)
    END IF