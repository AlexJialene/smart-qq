package com.keizyi.smartqq.bean;

import com.alibaba.fastjson.JSONObject;
import com.keizyi.smartqq.kit.JsonKit;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 15:50
 */
public class MessageValue implements AsClass {

    //message type
    private String poll_type;

    private Object value;

    public String getPoll_type() {
        return poll_type;
    }

    public void setPoll_type(String poll_type) {
        this.poll_type = poll_type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    @Override
    public Object asClass(Class clazz) {
        return JsonKit.parse(JsonKit.toFeatureJson(this.value), clazz);
    }
}
