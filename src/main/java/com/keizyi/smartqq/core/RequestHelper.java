package com.keizyi.smartqq.core;

import com.keizyi.smartqq.kit.HttpRequestKit;
import com.keizyi.smartqq.kit.JsonKit;
import com.keizyi.smartqq.kit.RequestPathKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/7
 * Time: 17:12
 */
public class RequestHelper {
    private String reqPath;
    private String result;
    private Map<String, String> headers = new ConcurrentHashMap<>();
    private Logger logger = LoggerFactory.getLogger(RequestHelper.class);

    public RequestHelper(String url, String... patten) {
        this.reqPath = urlAssembly(url, patten);
        addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
    }

    public RequestHelper(RequestPathKit kit, String... patten) {
        this.reqPath = urlAssembly(kit.getUrl(), patten);
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

    private String urlAssembly(String url, String... param) {
        StringBuilder sb = new StringBuilder(url);
        Stream.of(param).forEach(k -> {
            sb.replace(sb.indexOf("{"), sb.indexOf("}") + 1, k);

        });
        return sb.toString();
    }

    public RequestHelper sendGet() {
        this.result = HttpRequestKit.sendGet(getReqPath(), null, getHeaders());
        return this;
    }

    public RequestHelper sendPost(String postParam) {
        this.result = HttpRequestKit.sendPost(getReqPath(), postParam, getHeaders());
        return this;
    }

    public URLConnection connGet() {
        return HttpRequestKit.connGet(getReqPath(), getHeaders());
    }

    public Object toResult(Class<?> clazz) {
        int i = this.reqPath.indexOf("?");
        String reqName = this.reqPath.substring(this.reqPath.lastIndexOf("/"), i == -1 ? this.reqPath.length() : i);

        logger.debug("the request [ {} ] result: {}", reqName, this.result);

        return JsonKit.parse(this.result, clazz);
    }

    public String get() {
        return this.result;
    }

}
