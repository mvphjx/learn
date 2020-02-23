package com.learn.hanjx.mind.result;
/**
 *
 * 1要统一返回值对象
 * 2统一处理异常信息
 *  业务层代码  只需抛出异常即可  不需要捕获；  也就是使用 aop处理异常
 *  例如 为ExceptionHandler
 *      相似的还有 声明式事务
 *         创建日期：   2019/8/31 14:54
 */
import java.io.Serializable;

public class ResultBean<T> implements Serializable
{
    private static final long serialVersionUID=1L;

    public static final int SUCESS=0;

    public static final int FAIL=1;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static final int NO_PERMESSION=2;

    private String msg="SUCESS";

    private int code=SUCESS;



    private T data;

    public ResultBean(){
        super();
    }

    public ResultBean(T data){
        super();
        this.data=data;
    }

    public ResultBean(Throwable e){
        super();
        this.msg=e.toString();
        this.code=FAIL;
    }

    @Override
    public String toString()
    {
        return "ResultBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
