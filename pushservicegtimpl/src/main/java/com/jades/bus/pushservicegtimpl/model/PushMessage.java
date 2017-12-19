package com.jades.bus.pushservicegtimpl.model;


import com.jades.bus.pushservice.model.IPushMessage;

/**
 * Created by wangyan on 2017/12/7.
 */

public class PushMessage implements IPushMessage {
    private String mIPushContent;
    private boolean mIsConsumed;

    public PushMessage(String IPushContent) {
        mIPushContent = IPushContent;

    }

    @Override
    public String getPushConent() {
        return mIPushContent;
    }

    @Override
    public boolean isConsumed() {
        return mIsConsumed;
    }

    @Override
    public void consume() {
        mIsConsumed = true;
    }
}
