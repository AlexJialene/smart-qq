package com.keizyi.smartqq.core;

import com.keizyi.smartqq.bean.DiscuMessage;
import com.keizyi.smartqq.bean.GroupMessage;
import com.keizyi.smartqq.bean.Message;

public interface Callback {

    void onMessage(Message message);

    void groupMessage(GroupMessage message);

    void discuMessage(DiscuMessage message);
}
