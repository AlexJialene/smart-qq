package com.keizyi.smartqq.dto;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/7
 * Time: 14:39
 */
public class LoginResponse {

    private Integer retcode;

    private XLoginResponse result;


    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public XLoginResponse getResult() {
        return result;
    }

    public void setResult(XLoginResponse result) {
        this.result = result;
    }
}
