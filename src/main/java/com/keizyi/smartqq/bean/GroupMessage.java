package com.keizyi.smartqq.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/9
 * Time: 15:45
 */
public class GroupMessage {

    /*
     * content length=4
     * content[0] = font
     * content[1] = @your nickname
     * content[2] = unknown
     * content[3] = msg content
     *
     * the last one is message
     */
    private Object[] content;

    private long from_uin;

    private long group_code;

    private int msg_id;

    private int msg_type;

    private long send_uin;

    //Unix timestamp
    private long time;

    private long to_uin;

    /**
     * last one = message
     *
     * @return message
     */
    public String getMessage() {
        if (null != content[content.length - 1])
            return content[content.length - 1].toString();
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

    public long getGroup_code() {
        return group_code;
    }

    public void setGroup_code(long group_code) {
        this.group_code = group_code;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }

    public long getSend_uin() {
        return send_uin;
    }

    public void setSend_uin(long send_uin) {
        this.send_uin = send_uin;
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
