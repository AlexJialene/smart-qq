package com.keizyi.smartqq.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartQQKit {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmartQQKit.class);

    /**
     * The function that generates the ptqrtoken
     * Sample
     * hash33: function(t) {
     * for (var e = 0,i = 0,n = t.length; i < n; ++i){
     * e += (e << 5) + t.charCodeAt(i);
     * return 2147483647 & e
     * }
     * }
     *
     * @param qrsig
     * @return
     */
    public static int hash33(String qrsig) {
        int i = 0, e = 0;
        for (; i < qrsig.length(); ++i) {
            e += (e << 5) + qrsig.charAt(i);
        }
        return 2147483647 & e;
    }

    /**
     * Match login verification
     * Sample
     * ptuiCB('66','0','','0','二维码未失效。(2953855840)', '')
     * ptuiCB('67','0','','0','二维码认证中。(2817537926)', '')
     * ptuiCB('0','0','https://ptlogin2.web2.qq.com/check_sig?pttype=1&uin=467146659...','0','登录成功！', 'lamkeizyi')
     *
     * @param str
     * @return String: https://xxxxx
     */
    public static String ptuiCB(String str) {
        LOGGER.debug("checking login result : {}", str);
        String[] s = str.trim()
                .replace("ptuiCB(", "")
                .replace(")", "")
                .replaceAll("'", "")
                .split(",");

        if ("0".equals(s[0]) && s[2].contains("https://") && s[4].contains("成功")) {
            LOGGER.info("checking login success : {}", s[2]);
            return s[2];
        }
        return null;
    }
}
