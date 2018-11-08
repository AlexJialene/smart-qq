package com.keizyi.smartqq.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 11:05
 */
public class Pull2FormData {
    private String ptwebqq = "";

    private Integer clientid = 53999199;

    private String psessionid;

    private String key = "";

    public Pull2FormData() {
    }

    public Pull2FormData(String psessionid) {
        this.psessionid = psessionid;
    }

    public String getPtwebqq() {
        return ptwebqq;
    }

    public void setPtwebqq(String ptwebqq) {
        this.ptwebqq = ptwebqq;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getPsessionid() {
        return psessionid;
    }

    public void setPsessionid(String psessionid) {
        this.psessionid = psessionid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
