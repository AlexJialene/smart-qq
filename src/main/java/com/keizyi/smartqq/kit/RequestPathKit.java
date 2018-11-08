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
    ),
    PT_QR_LOGIN(
            "https://ssl.ptlogin2.qq.com/ptqrlogin?u1=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&ptqrtoken={}&ptredirect=0&h=1&t=1&g=1&from_ui=1&ptlang=2052&action=0-0-1541497137149&js_ver=10284&js_type=1&login_sig=&pt_uistyle=40&aid=501004106&daid=164&mibao_css=m_webqq&",
            "https://xui.ptlogin2.qq.com/cgi-bin/xlogin?daid=164&target=self&style=40&pt_disable_pwd=1&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&s_url=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20131024001"
    ),
    GET_VF_WEB_QQ(
            "https://s.web2.qq.com/api/getvfwebqq?ptwebqq=&clientid=53999199&psessionid=&t=1541564021367",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    X_LOGIN(
            "https://d1.web2.qq.com/channel/login2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    SELF_INFO(
            "https://s.web2.qq.com/api/get_self_info2?t=1541603827945",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    PULL_MSG(
            "https://d1.web2.qq.com/channel/poll2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
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
