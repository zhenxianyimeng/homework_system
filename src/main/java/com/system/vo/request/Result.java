package com.system.vo.request;

import com.system.common.constant.WebConstant;

import java.io.Serializable;

/**
 * @author zjb
 * @date 2018/7/8.
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -7706430141206682077L;

    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(){
        return new Result<T>(0, WebConstant.MSG_SUCCESS);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(0, WebConstant.MSG_SUCCESS, data);
    }

    public static <T> Result<T> success(Integer code, String msg, T data){
        return new Result<T>(code, msg, data);
    }

    public static <T> Result<T> fail(){
        return new Result<T>(-1, WebConstant.MSG_FAILED);
    }

    public static <T> Result<T> fail(String msg){
        return new Result<T>(-1, msg);
    }

    public static <T> Result<T> fail(Integer code, String msg, T data){
        return new Result<T>(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

