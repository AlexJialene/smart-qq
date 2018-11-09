package com.keizyi.smartqq.core;

import com.keizyi.smartqq.bean.Message;

public interface Callback {

    void onMessage(Message message);

    void groupMessage();
}
