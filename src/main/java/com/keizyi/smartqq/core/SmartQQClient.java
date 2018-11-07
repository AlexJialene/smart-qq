package com.keizyi.smartqq.core;

import com.keizyi.smartqq.dto.LoginResponse;
import com.keizyi.smartqq.dto.VfWebQQResponse;
import com.keizyi.smartqq.dto.XLoginFormData;
import com.keizyi.smartqq.dto.XLoginResponse;
import com.keizyi.smartqq.kit.HttpRequestKit;
import com.keizyi.smartqq.kit.JsonMapperKit;
import com.keizyi.smartqq.kit.SmartQQKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/6
 * Time: 11:03
 */
public class SmartQQClient {

    //static area
    //
    private final static int APP_ID = 501004106;
    private final static JsonMapperKit JSON_MAPPER_KIT = JsonMapperKit.nonNullMapper();

    private Logger logger = LoggerFactory.getLogger(SmartQQClient.class);
    //ptqrshow response Set-Cookie & ptqrlogin? cookie
    private String qrsig;

    private String qrcodePath;

    //the thread on || off flag;
    private boolean threadSwitch;

    private String checkLoginResultLink;

    private Map<String, List<String>> checkSigResponseHeader;

    //the vfwebqq interface response
    private String vfwebqq;

    private XLoginResponse xLoginResponse;


    public SmartQQClient() {
        //login
        insQRCodePath();
        insQRCode();
        checkingQRLogin();
        checkSig();
        insVfWebQQ();
        insXLogin();
        this.threadSwitch = true;
        //ins runnable
        //Instance ptqrshow

        //Instance thread to listen for ptqrlogin?
    }

    private void insXLogin() {
        String path = "https://d1.web2.qq.com/channel/login2";
        String referer = "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2";

        Map<String, String> httpHeader = new HashMap<>();
        httpHeader.put("referer", referer);
        httpHeader.put("origin", "https://d1.web2.qq.com");
        httpHeader.put("cookie", SmartQQKit.vfCookie(this.checkSigResponseHeader));
        httpHeader.put("content-type" , "application/x-www-form-urlencoded");

        String result = HttpRequestKit.sendPost(path , "r="+JSON_MAPPER_KIT.toJson(new XLoginFormData()) , httpHeader);
        logger.debug("Xlogin result : {}" , result);
    }

    private void checkSig() {
        if (null == checkLoginResultLink)
            return;
        //save check_sig cookie & other http headers
        URLConnection urlConnection = HttpRequestKit.getConn(this.checkLoginResultLink);
        this.checkSigResponseHeader = urlConnection.getHeaderFields();

    }

    private void checkingQRLogin() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //send ptqrlogin
            String path = "https://ssl.ptlogin2.qq.com/ptqrlogin?u1=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&ptqrtoken={}&ptredirect=0&h=1&t=1&g=1&from_ui=1&ptlang=2052&action=0-0-1541497137149&js_ver=10284&js_type=1&login_sig=&pt_uistyle=40&aid=501004106&daid=164&mibao_css=m_webqq&";
            String referer = "https://xui.ptlogin2.qq.com/cgi-bin/xlogin?daid=164&target=self&style=40&pt_disable_pwd=1&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&s_url=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20131024001";
            String ptqrtoken = String.valueOf(SmartQQKit.hash33(this.qrsig.replace("qrsig=", "")));

            Map<String, String> httpHeader = new HashMap<>();
            httpHeader.put("referer", referer);
            httpHeader.put("cookie", qrsig);

            String result = HttpRequestKit.sendGet(SmartQQKit.urlAssembly(path, ptqrtoken), null, httpHeader);
            String httpLink = SmartQQKit.ptuiCB(result);
            if (null != httpLink) {
                logger.info("登录成功");
                this.checkLoginResultLink = httpLink;
                break;
            }

        }
    }

    public void insQRCodePath() {
        String filePath = System.getProperty("user.dir") + "/img";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.qrcodePath = filePath + "/qrcode.png";
    }

    public void insQRCode() {
        try {
            URLConnection connection = HttpRequestKit.getConn("https://ssl.ptlogin2.qq.com/ptqrshow?appid=501004106&e=2&l=M&s=3&d=72&v=4&t=0.9236259082903007&daid=164&pt_3rd_aid=0");
            InputStream inputStream = connection.getInputStream();
            //get qrsig
            insQrsig(connection.getHeaderField("Set-Cookie"));
            FileOutputStream outputStream = new FileOutputStream(new File(this.qrcodePath));

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            logger.info("Your login QR code has been generated ; path : {}", this.qrcodePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insQrsig(String headerField) {
        if (null != headerField) {

            String[] cookies = headerField.split(";");
            this.qrsig = cookies[0];
        }
    }

    public String getQrsig() {
        return qrsig;
    }

    public String getCheckLoginResultLink() {
        return checkLoginResultLink;
    }

    public void insVfWebQQ() {
        //eg: ptwebqq was null in this path
        String path = "https://s.web2.qq.com/api/getvfwebqq?ptwebqq=&clientid=53999199&psessionid=&t=1541564021367";
        String referer = "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1";

        Map<String, String> headers = new HashMap<>();
        headers.put("referer", referer);
        headers.put("cookie", SmartQQKit.vfCookie(this.checkSigResponseHeader));

        String result = HttpRequestKit.sendGet(path, null, headers);
        logger.debug("getVfWebQQ result : {}", result);

        LoginResponse loginResponse = JSON_MAPPER_KIT.fromJson(result, LoginResponse.class);
        if (null != loginResponse && 0 == loginResponse.getRetcode()) {
            this.vfwebqq = loginResponse.getResult().getVfwebqq();
        }
        //return vfWebQQResponse;
    }
}
