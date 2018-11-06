package com.keizyi.smartqq.core;

import com.keizyi.smartqq.kit.HttpRequestKit;
import com.keizyi.smartqq.kit.SmartQQKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
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
        checkingQRLogin();

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
            String path = "https://ssl.ptlogin2.qq.com/ptqrlogin?u1=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&ptqrtoken="+ SmartQQKit.hash33(this.qrsig.replace("qrsig=" , ""))+"&ptredirect=0&h=1&t=1&g=1&from_ui=1&ptlang=2052&action=0-0-1541497137149&js_ver=10284&js_type=1&login_sig=&pt_uistyle=40&aid=501004106&daid=164&mibao_css=m_webqq&";
            String referer="https://xui.ptlogin2.qq.com/cgi-bin/xlogin?daid=164&target=self&style=40&pt_disable_pwd=1&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&s_url=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20131024001";
            try {
                String result = "";
                BufferedReader in;
                URL realUrl = new URL(path);
                URLConnection connection = realUrl.openConnection();
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
                connection.setRequestProperty("referer",referer);
                connection.setRequestProperty("cookie",qrsig);
                //URLConnection connection = HttpRequestKit.connectGet(path , referer , HttpRequestKit.HttpType.GET);
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                try {
                    while ((line = in.readLine()) != null) {
                        result += line;
                    }
                    logger.info("checking login result : {}" ,result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
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
}
