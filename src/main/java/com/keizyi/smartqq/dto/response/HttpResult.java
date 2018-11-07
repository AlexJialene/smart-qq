package com.keizyi.smartqq.dto.response;

import com.keizyi.smartqq.kit.JsonMapperKit;

import java.util.HashMap;

public class HttpResult<T> {

    //0:success
    private Integer retcode;

    private HashMap<String,String> result;

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public HashMap<String, String> getResult() {
        return result;
    }

    public void setResult(HashMap<String, String> result) {
        this.result = result;
    }

    public T asClass(Class<T> clazz){
        JsonMapperKit jsonMapperKit = JsonMapperKit.nonNullMapper();
        return jsonMapperKit.fromJson(jsonMapperKit.toJson(result) , clazz);
    }
}
