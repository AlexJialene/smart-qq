package com.keizyi.smartqq.core;

import com.keizyi.smartqq.kit.HttpRequestKit;
import com.keizyi.smartqq.kit.RequestPathKit;
import com.keizyi.smartqq.kit.SmartQQKit;

import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/7
 * Time: 17:12
 */
public class RequestHelper {
    private String reqPath;
    private Map<String, String> headers = new ConcurrentHashMap<>();

    public RequestHelper(String url, String... patten){
        this.reqPath = SmartQQKit.urlAssembly(url, patten);
        addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
    }

    public RequestHelper(RequestPathKit kit, String... patten) {
        this.reqPath = SmartQQKit.urlAssembly(kit.getUrl(), patten);
        addHeader("referer", kit.getReferer());
        addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

    }

    public RequestHelper addHeader(String key, String value) {
        if (null != key && null != value && !"".equals(key) && !"".equals(value)) {
            getHeaders().put(key, value);
        }
        return this;
    }

    private Map<String, String> getHeaders() {
        return headers;
    }

    private String getReqPath() {
        return reqPath;
    }

    public String sendGet() {
        return HttpRequestKit.sendGet(getReqPath(), null, getHeaders());
    }

    public String sendPost(String postParam) {
        return HttpRequestKit.sendPost(getReqPath(), postParam, getHeaders());
    }

    public URLConnection connGet() {
        return HttpRequestKit.connGet(getReqPath(), getHeaders());
    }


}