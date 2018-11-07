package com.keizyi.smartqq.kit;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/7
 * Time: 17:02
 */
public enum RequestPathKit {

    QR_SHOW(
            "https://ssl.ptlogin2.qq.com/ptqrshow?appid=501004106&e=2&l=M&s=3&d=72&v=4&t=0.9236259082903007&daid=164&pt_3rd_aid=0",
            ""
    );


    private String url;
    private String referer;

    RequestPathKit(String url, String referer) {
        this.url = url;
        this.referer = referer;
    }

    public String getUrl() {
        return url;
    }

    public String getReferer() {
        return referer;
    }
}
