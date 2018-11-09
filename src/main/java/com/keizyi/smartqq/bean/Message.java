package com.keizyi.smartqq.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 15:40
 */
public class Message {

    /*
    content[0] = Array,length=2
    content[1] = String:msg
     */
    private Object[] content;

    private long from_uin;

    private Integer msg_id;

    private Integer msg_type;

    private long time;

    private long to_uin;

    public String getMessage() {
        if (null != content[1])
            return content[1].toString();
        return null;
    }

    public Object[] getContent() {
        return content;
    }

    public void setContent(Object[] content) {
        this.content = content;
    }

    public long getFrom_uin() {
        return from_uin;
    }

    public void setFrom_uin(long from_uin) {
        this.from_uin = from_uin;
    }

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public Integer getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(Integer msg_type) {
        this.msg_type = msg_type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTo_uin() {
        return to_uin;
    }

    public void setTo_uin(long to_uin) {
        this.to_uin = to_uin;
    }

}
