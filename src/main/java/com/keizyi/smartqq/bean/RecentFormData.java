package com.keizyi.smartqq.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/13
 * Time: 17:36
 */
public class RecentFormData {

    private String vfwebqq;

    private Integer clientid = 53999199;

    private String psessionid;

    public RecentFormData(String vfwebqq, String psessionid) {
        this.vfwebqq = vfwebqq;
        this.psessionid = psessionid;
    }

    public String getVfwebqq() {
        return vfwebqq;
    }

    public void setVfwebqq(String vfwebqq) {
        this.vfwebqq = vfwebqq;
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
}
