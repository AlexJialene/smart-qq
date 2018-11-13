package com.keizyi.smartqq.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/13
 * Time: 17:28
 */
public class Recent {

    /*
     * 0:friend
     * 1:group
     * 2:discu
     */
    private int type;

    private long uin;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }
}
