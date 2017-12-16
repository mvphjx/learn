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
    
最近执行语句：
    
    select sql_text,last_load_time from v$sql where sql_text like '%delete from "HSABISADM"%' order by last_load_time desc;
     
查找前十条性能差的sql.
 
    SELECT * FROM (select PARSING_USER_ID,EXECUTIONS,SORTS,
                  COMMAND_TYPE,DISK_READS,sql_text FROM v$sqlarea
                order BY disk_reads DESC )where ROWNUM<10 ;
      
查看oracle表结构历史：
    
    select sql_text,last_load_time from v$sql where sql_text like '%delete from "HSABISADM"%' order by last_load_time desc;
     
    