package com.keizyi.smartqq.core;

import com.keizyi.smartqq.kit.HttpRequestKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLConnection;

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
    private Logger logger = LoggerFactory.getLogger(SmartQQClient.class);
    //
    private String qrsig;

    private String qrcodePath;

    //the thread on || off flag;
    private boolean threadSwitch;


    public SmartQQClient() {
        //login
        insQRCodePath();
        insQRCode();
        //checkingQRLogin();

        this.threadSwitch = true;
        //ins runnable
        //Instance ptqrshow

        //Instance thread to listen for ptqrlogin?
    }

    private void checkingQRLogin() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //todo
            //path : https://ssl.ptlogin2.qq.com/ptqrlogin?u1=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&ptqrtoken=1956742765&ptredirect=0&h=1&t=1&g=1&from_ui=1&ptlang=2052&action=0-0-1541495368526&js_ver=10284&js_type=1&login_sig=&pt_uistyle=40&aid=501004106&daid=164&mibao_css=m_webqq&
            //referer :　https://xui.ptlogin2.qq.com/cgi-bin/xlogin?daid=164&target=self&style=40&pt_disable_pwd=1&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&s_url=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20131024001

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
            URLConnection connection = HttpRequestKit.connectGet("https://ssl.ptlogin2.qq.com/ptqrshow?appid=501004106&e=2&l=M&s=3&d=72&v=4&t=0.9236259082903007&daid=164&pt_3rd_aid=0",null, HttpRequestKit.HttpType.GET);
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

    /**
     * hash33: function(t) {
     * for (var e = 0,i = 0,n = t.length; i < n; ++i){
     * e += (e << 5) + t.charCodeAt(i);
     * return 2147483647 & e
     * }
     * }
     * The function that generates the ptqrtoken
     *
     * @param qrsig
     * @return
     */
    public int hash33(String qrsig) {
        int i = 0, e = 0;
        for (; i < qrsig.length(); ++i) {
            e += (e << 5) + qrsig.charAt(i);
        }
        return 2147483647 & e;
    }

    public String getQrsig() {
        return qrsig;
    }
}
