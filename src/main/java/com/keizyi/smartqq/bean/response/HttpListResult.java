package com.keizyi.smartqq.bean.response;

import com.keizyi.smartqq.bean.AsClass;
import com.keizyi.smartqq.kit.JsonKit;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 16:34
 */
public class HttpListResult<T> implements AsClass<T> {
    //0:success
    private Integer retcode;

    private List<Object> result;

    private String retmsg;

    private String errmsg;

    public T asClass(Class<T> clazz) {
        if (null!=result){
            String json = JsonKit.toFeatureJson(this.result.get(0));
            return JsonKit.parse(json, clazz);
        }
        return null;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
