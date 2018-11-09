package com.keizyi.smartqq;

import com.keizyi.smartqq.bean.Message;
import com.keizyi.smartqq.core.Callback;
import com.keizyi.smartqq.core.KeepPullMessage;
import com.keizyi.smartqq.core.SmartQQClient;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 14:20
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {

        SmartQQClient smartQQClient = new SmartQQClient(new Callback() {
            @Override
            public void onMessage(Message message) {
                System.out.println(message.getContent()[1]);
            }
        }).startReceive();


    }
}
