package com.keizyi.smartqq.dto;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/7
 * Time: 14:31
 */
public class XLoginResponse {

    private Integer cip;

    private Integer f;

    private Integer index;

    private Integer port;

    private String psessionid;

    private String status;

    private Integer uin;

    private Integer user_state;

    private String vfwebqq;

    public Integer getCip() {
        return cip;
    }

    public void setCip(Integer cip) {
        this.cip = cip;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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

    public Integer getUin() {
        return uin;
    }

    public void setUin(Integer uin) {
        this.uin = uin;
    }

    public Integer getUser_state() {
        return user_state;
    }

    public void setUser_state(Integer user_state) {
        this.user_state = user_state;
    }

    public String getVfwebqq() {
        return vfwebqq;
    }

    public void setVfwebqq(String vfwebqq) {
        this.vfwebqq = vfwebqq;
    }
}
