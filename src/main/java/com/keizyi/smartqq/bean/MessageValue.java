package com.keizyi.smartqq.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 15:50
 */
public class MessageValue<T> {

    private String poll_type;

    private T value;

    /*public T asClass(Class<T> clazz){

    }*/

    public String getPoll_type() {
        return poll_type;
    }

    public void setPoll_type(String poll_type) {
        this.poll_type = poll_type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
