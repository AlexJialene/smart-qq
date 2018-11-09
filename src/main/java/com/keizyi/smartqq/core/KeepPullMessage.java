package com.keizyi.smartqq.core;

import com.keizyi.smartqq.bean.Pull2FormData;
import com.keizyi.smartqq.kit.RequestPathKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 14:30
 */
public class KeepPullMessage extends Thread{

    private Logger logger = LoggerFactory.getLogger(KeepPullMessage.class);

    private SmartQQClient client;

    private volatile boolean startFlag = false;

    public KeepPullMessage(SmartQQClient client){
        this.client = client;
    }

    @Override
    public synchronized void start() {
        if (startFlag){
            logger.warn("Thread is already running !");
            return ;
        }
        this.startFlag = true;

        super.start();
    }

    @Override
    public void run() {
        logger.debug("keep pull message cookie: {}",this.client.getCheckSigResponseCookie());
        logger.debug("keep pull message psessionid: {}",this.client.xLogin().getPsessionid());
        logger.info("开启接收QQ消息");

        RequestHelper requestHelper = SmartQQClient.Request.$(RequestPathKit.PULL_MSG)
                .addHeader("cookie",this.client.getCheckSigResponseCookie())
                .addHeader("origin","https://d1.web2.qq.com")
                .addHeader("content-type","application/x-www-form-urlencoded");
        Pull2FormData data = new Pull2FormData(this.client.xLogin().getPsessionid());

        while (startFlag){
            String result = requestHelper.sendPost(this.client.jsonFormData(data)).get();

            logger.info("pull message result: {}" ,result);
            //callback


        }

    }

    public void close() {
        this.startFlag = false;
        logger.info("关闭接收QQ消息");
    }
}
