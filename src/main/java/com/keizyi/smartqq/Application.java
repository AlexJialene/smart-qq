package com.keizyi.smartqq;

import com.keizyi.smartqq.bean.DiscuMessage;
import com.keizyi.smartqq.bean.GroupMessage;
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
                System.out.println("用户信息");
            }

            @Override
            public void groupMessage(GroupMessage message) {
                System.out.println("群消息");
            }

            @Override
            public void discuMessage(DiscuMessage message) {
                System.out.println("讨论组消息");
            }
        }).startReceive();


    }
}
