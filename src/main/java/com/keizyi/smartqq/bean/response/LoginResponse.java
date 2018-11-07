package com.keizyi.smartqq.bean.response;

import com.keizyi.smartqq.bean.XLogin;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/7
 * Time: 14:39
 */
public class LoginResponse {

    private Integer retcode;

    private XLogin result;


    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public XLogin getResult() {
        return result;
    }

    public void setResult(XLogin result) {
        this.result = result;
    }
}
