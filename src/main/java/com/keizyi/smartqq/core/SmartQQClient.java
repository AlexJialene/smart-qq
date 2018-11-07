package com.keizyi.smartqq.core;

import com.keizyi.smartqq.dto.response.LoginResponse;
import com.keizyi.smartqq.dto.XLoginFormData;
import com.keizyi.smartqq.dto.XLoginDto;
import com.keizyi.smartqq.kit.JsonMapperKit;
import com.keizyi.smartqq.kit.RequestPathKit;
import com.keizyi.smartqq.kit.SmartQQKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLConnection;
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

    private XLoginDto xLoginDto;


    public SmartQQClient() {
        //login
        insQRCodePath();
        insQRCode();
        checkingQRLogin();
        checkSig();
        insVfWebQQ();
        insXLogin();
        //login success
        //Gets myself info



        this.threadSwitch = true;
        //ins runnable
        //Instance ptqrshow

        //Instance thread to listen for ptqrlogin?
    }

    private void insXLogin() {

        String result = Request.$(RequestPathKit.X_LOGIN)
                .addHeader("origin", "https://d1.web2.qq.com")
                .addHeader("cookie", SmartQQKit.vfCookie(this.checkSigResponseHeader))
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .sendPost("r=" + JSON_MAPPER_KIT.toJson(new XLoginFormData()));
        logger.debug("login2 result : {}" , result);
        LoginResponse loginResponse = JSON_MAPPER_KIT.fromJson(result, LoginResponse.class);
        if (null != loginResponse && 0 == loginResponse.getRetcode()) {
            this.xLoginDto = loginResponse.getResult();
        }
    }

    private void checkSig() {
        if (null == checkLoginResultLink)
            return;
        //save check_sig cookie & other http headers
        URLConnection urlConnection = Request.$(this.checkLoginResultLink).connGet();
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
            String ptqrtoken = String.valueOf(SmartQQKit.hash33(this.qrsig.replace("qrsig=", "")));
            String result = Request.$(RequestPathKit.PT_QR_LOGIN, ptqrtoken).addHeader("cookie", qrsig).sendGet();
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
            URLConnection connection = Request.$(RequestPathKit.QR_SHOW).connGet();
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
        String  result = Request.$(RequestPathKit.GET_VF_WEB_QQ)
                .addHeader("cookie" , SmartQQKit.vfCookie(this.checkSigResponseHeader))
                .sendGet();

        logger.debug("getVfWebQQ result : {}", result);

        LoginResponse loginResponse = JSON_MAPPER_KIT.fromJson(result, LoginResponse.class);
        if (null != loginResponse && 0 == loginResponse.getRetcode()) {
            this.vfwebqq = loginResponse.getResult().getVfwebqq();
        }
    }

    public static class Request{
        public static RequestHelper $(RequestPathKit kit, String... patten) {
            return new RequestHelper(kit, patten);
        }

        public static RequestHelper $(String url, String... patten) {
            return new RequestHelper(url, patten);
        }
    }



}
