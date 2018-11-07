package com.keizyi.smartqq.dto;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/7
 * Time: 14:16
 */
public class VfWebQQResponse {
    //0:success
    private Integer retcode;

    /**
     * Sample
     * "vfwebqq":"d86fabbed2bb3601a5cb0f9c1a187459d185798b5702b8575b4a3090b209f26cf5a1f9da91d49b4f"
     */
    private Map<String ,String > result;

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
