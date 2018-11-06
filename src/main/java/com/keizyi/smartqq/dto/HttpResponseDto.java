package com.keizyi.smartqq.dto;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/6
 * Time: 14:43
 */
public class HttpResponseDto {

    private InputStream inputStream;

    private Map<String , List<String>> headers;

    public HttpResponseDto() {
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);

        //this.inputStream = inputStream;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
