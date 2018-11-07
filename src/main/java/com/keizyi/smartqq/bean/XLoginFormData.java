package com.keizyi.smartqq.bean;

/**
 * {"ptwebqq":"","clientid":53999199,"psessionid":"","status":"online"}
 * User: Alex_
 * Date: 2018/11/7
 * Time: 14:48
 */
public class XLoginFormData {
    private String ptwebqq = "";

    private Integer clientid = 53999199;

    private String psessionid = "";

    private String status = "online";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
