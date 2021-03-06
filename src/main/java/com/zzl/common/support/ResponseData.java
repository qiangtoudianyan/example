package com.zzl.common.support;

/**
 * @author: 张志龙
 * @Date: 2018/7/5 21:10
 * @Description:
 */
public class ResponseData {
    private String code;

    private String message;

    private Object data;

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data = data;
    }

    @Override
    public String toString(){
        return "ResponseData{" + "code='"
               + code
               + '\''
               + ", message='"
               + message
               + '\''
               + ", data="
               + data
               + '}';
    }
}
