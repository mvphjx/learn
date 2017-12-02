#复制表结构及其数据：

--复制表结构及其数据

    create table table_name_new as select * from table_name_old
--只复制表结构：

    create table table_name_new as select * from table_name_old where 1=2;
或者：

    create table table_name_new like table_name_old
--只复制表数据：
如果两个表结构一样：

    insert into table_name_new select * from table_name_old
如果两个表结构不一样：

    insert into table_name_new(column1,column2...) select column1,column2... from table_name_old