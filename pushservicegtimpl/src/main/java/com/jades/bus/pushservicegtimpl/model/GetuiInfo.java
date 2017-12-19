package com.jades.bus.pushservicegtimpl.model;


import com.jades.bus.pushservice.service.IBusPushService;

/**
 * Created by wangyan on 2017/12/6.
 */

public class GetuiInfo implements IBusPushService.IPushInfo {
    private String mAppId;
    private String mAppKey;
    private String mAppSecret;


    public GetuiInfo(String appId, String appKey, String appSecret) {
        mAppId = appId;
        mAppKey = appKey;
        mAppSecret = appSecret;
    }

    @Override
    public String getAppId() {
        return mAppId;
    }

    @Override
    public String getAppKey() {
        return mAppKey;
    }

    @Override
    public String getAppSecret() {
        return mAppSecret;
    }
}
