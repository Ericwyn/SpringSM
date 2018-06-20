package com.ericwyn.ssm.util;

import java.util.HashMap;
import java.util.List;

/**
 * 普通的
 * 返回Json 的格式化类
 *
 * 格式统一为
 *
 *     {
 *         "success": true,         代表请求成功
 *         "code": "1",               同样代表请求是否成功，成功的话为1
 *         "msg": "",                 包含的信息，用以存储错误提示，或者成功提示
 *         "dataMap": {},             当返回的数据需要包含 map数据 的时候使用
 *         "dataList": null           当返回的数据需要包含 list数据 的时候使用
 *     }
 *
 *
 * Created by Ericwyn on 17-11-4.
 */
public class ResJson<T> {
    private boolean success;
    private String code;

    private String msg;
    private HashMap<String ,Object>dataMap;
    private List<T> dataList;

//    public ResJson(String success, String code, String msg) {
//        this.success = success;
//        this.code = code;
//        this.msg = msg;
//    }

    private ResJson(boolean success, String code, String msg, List<T> dataList, HashMap<String, Object> dataMap) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.dataList = dataList;
        this.dataMap=dataMap;
    }


    public static ResJson failJson(String msg){
        return new ResJson(false,"-1",msg,null,null);
    }

    public static ResJson failJson(String failCode,String msg){
        return new ResJson(false,failCode,msg,null,null);
    }
    public static ResJson failJson(String msg, List<?> dataList){
        return new ResJson(false,"-1",msg,dataList,null);
    }

    public static ResJson successJson(String msg){
        return new ResJson(true,"1",msg,null,null);
    }
    public static ResJson successJson(String successCode,String msg){
        return new ResJson(false,successCode,msg,null,null);
    }
    public static ResJson successJson(String msg,List<?> dataList){
        return new ResJson(true,"1",msg,dataList,null);
    }

    public static ResJson failJson(String msg, HashMap<String, Object> dataMap){
        return new ResJson(false,"-1",msg,null,dataMap);
    }

    public static ResJson successJson(String msg, HashMap<String, Object> dataMap){
        return new ResJson(true,"1",msg,null,dataMap);
    }

    public static ResJson tokenFailResJson(){
        return ResJson.failJson("40000","token error or token has expired");
    }

    public static ResJson errorRequestParam(){
        return ResJson.failJson("40001","request params error");
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public HashMap<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
