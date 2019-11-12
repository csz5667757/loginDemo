package com.csz.common.dto;

public class ResponseInfo {
    private int code;
    private String message;
    private Object data;
    public ResponseInfo(){}
    public ResponseInfo(int code, Object data, String message){
        init( code, data, message);
    }
    public ResponseInfo(int code, String message){
        init( code, null, message);
    }
    private void init(int code,Object data,String message){
        setCode(code);
        setData(data);
        setMessage(message);
    }
    public static ResponseInfo Success(Object data,String message){
        return new ResponseInfo(200,data,message);
    }
    public static ResponseInfo Success(Object data){
        return new ResponseInfo(0,data,"Success");
    }
    public static ResponseInfo Exception(Object data,Exception e){
        return new ResponseInfo(500,data,e.getMessage());
    }
    public static ResponseInfo Exception(Object data,String message){
        return new ResponseInfo(500,data,message);
    }
    public static ResponseInfo Exception(Object data){
        return new ResponseInfo(500,data,"Server Exception!");
    }
    public static ResponseInfo Exception(int code,String message){
        return new ResponseInfo(code,message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
