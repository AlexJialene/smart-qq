package com.keizyi.smartqq.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/19
 * Time: 17:07
 */
public class FriendFormData {
    private String vfwebqq;

    private String hash ;

    public FriendFormData(String vfwebqq, String hash) {
        this.vfwebqq = vfwebqq;
        this.hash = hash;
    }

    public String getVfwebqq() {
        return vfwebqq;
    }

    public void setVfwebqq(String vfwebqq) {
        this.vfwebqq = vfwebqq;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
