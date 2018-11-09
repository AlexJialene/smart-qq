package com.keizyi.smartqq.core;

import com.keizyi.smartqq.bean.SelfInfo;
import com.keizyi.smartqq.bean.response.HttpMapResult;
import com.keizyi.smartqq.bean.response.LoginResponse;
import com.keizyi.smartqq.bean.XLoginFormData;
import com.keizyi.smartqq.bean.XLogin;
import com.keizyi.smartqq.kit.JsonKit;
import com.keizyi.smartqq.kit.RequestPathKit;
import com.keizyi.smartqq.kit.SmartQQKit;
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

    private final Callback callback;
    private Logger logger = LoggerFactory.getLogger(SmartQQClient.class);

    //ptqrshow response Set-Cookie & ptqrlogin? cookie
    private String qrsig;
    private String qrcodePath;
    private String checkLoginResultLink;
    private String checkSigResponseCookie;

    //the vfwebqq interface response
    private String vfwebqq;

    //login info & user info
    private XLogin xLogin;

    private SelfInfo selfInfo;

    private KeepPullMessage keepPullMessage;


    public SmartQQClient(Callback callback) {
        this.callback = callback;
        if (login()) {
            logger.info("接收消息组件初始化");
            this.keepPullMessage = new KeepPullMessage(this);
            //todo
        }
    }

    public SmartQQClient startReceive() {
        if (null != this.keepPullMessage)
            this.keepPullMessage.start();
        return this;
    }

    public SmartQQClient closeReceive() {
        this.keepPullMessage.close();
        return this;
    }

    private boolean login() {
        insQRCodePath();
        insQRCode();
        checkingQRLogin();
        checkSig();
        insVfWebQQ();
        insXLogin();
        insSelfInfo();
        if (null != checkSigResponseCookie) {
            return true;
        }
        return false;
    }

    private void insSelfInfo() {
        String resultStr = Request.$(RequestPathKit.SELF_INFO)
                .addHeader("cookie", this.checkSigResponseCookie)
                .sendGet()
                .get();

        logger.debug("get self info : {}", resultStr);
        HttpMapResult result = JsonKit.parse(resultStr, HttpMapResult.class);
        if (0 == result.getRetcode()) {
            SelfInfo selfInfo = (SelfInfo) result.asClass(SelfInfo.class);
            if (null != selfInfo) {
                this.selfInfo = selfInfo;
                logger.info("登陆成功，欢迎用户： {}", selfInfo.getNick());
            }
        }
    }

    private void insXLogin() {
        LoginResponse loginResponse = (LoginResponse) Request.$(RequestPathKit.X_LOGIN)
                .addHeader("origin", "https://d1.web2.qq.com")
                .addHeader("cookie", this.checkSigResponseCookie)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .sendPost(jsonFormData(new XLoginFormData()))
                .toResult(LoginResponse.class);

        //logger.debug("login2 result : {}", result);
        //LoginResponse loginResponse = jsonMapper.fromJson(result, LoginResponse.class);
        if (null != loginResponse && 0 == loginResponse.getRetcode()) {
            this.xLogin = loginResponse.getResult();
        }
    }

    private void checkSig() {
        if (null == checkLoginResultLink)
            return;
        //save check_sig cookie & other http headers
        URLConnection urlConnection = Request.$(this.checkLoginResultLink).connGet();
        //Gets cookies from headers
        this.checkSigResponseCookie = SmartQQKit.vfCookie(urlConnection.getHeaderFields());

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
            String result = Request.$(RequestPathKit.PT_QR_LOGIN, ptqrtoken).
                    addHeader("cookie", qrsig)
                    .sendGet()
                    .get();
            String httpLink = SmartQQKit.ptuiCB(result);
            if (null != httpLink) {
                logger.info("扫码登录成功");
                this.checkLoginResultLink = httpLink;
                break;
            } else {
                //TODO other
            }

        }
    }

    private void insQRCodePath() {
        String filePath = System.getProperty("user.dir") + "/img";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.qrcodePath = filePath + "/qrcode.png";
    }

    private void insQRCode() {
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

    private void insVfWebQQ() {
        //eg: ptwebqq was null in this path
        String result = Request.$(RequestPathKit.GET_VF_WEB_QQ)
                .addHeader("cookie", this.checkSigResponseCookie)
                .sendGet()
                .get();

        logger.debug("getVfWebQQ result : {}", result);

        LoginResponse loginResponse = JsonKit.parse(result, LoginResponse.class);
        if (null != loginResponse && 0 == loginResponse.getRetcode()) {
            this.vfwebqq = loginResponse.getResult().getVfwebqq();
        }
    }

    protected String jsonFormData(Object obj) {
        return "r=" + JsonKit.toFeatureJson(obj);
    }

    protected XLogin xLogin() {
        return this.xLogin;
    }

    protected String getCheckSigResponseCookie() {
        return checkSigResponseCookie;
    }

    public static class Request {
        public static RequestHelper $(RequestPathKit kit, String... patten) {
            return new RequestHelper(kit, patten);
        }

        public static RequestHelper $(String url, String... patten) {
            return new RequestHelper(url, patten);
        }
    }


}
