package com.jades.bus.pushservicegtimpl;


import com.igexin.sdk.PushManager;
import com.jades.bus.common.busservice.BusServiceFactory;
import com.jades.bus.common.busservice.IBusActionListener;
import com.jades.bus.pushservice.service.BusPushServiceBase;
import com.jades.bus.pushservice.service.IBusMessageService;
import com.jades.bus.pushservicegtimpl.model.PushMessage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;



/**
 * Created by wangyan on 2017/11/2.
 */

public abstract class BusGetuiPushService extends BusPushServiceBase {

    private boolean mPushStatus;

    @Override
    public void onReceivedMessage(String content) {
        IBusMessageService.IMessage iMessage = new PushMessage(content);

        IBusMessageService iBusMessageService = BusServiceFactory.getInstance().getBusService(IBusMessageService.class);

        iBusMessageService.addMessage(iMessage,true);
    }

    @Override
    public void initPushSdk(Context context) {
        if(context==null){
            throw new IllegalStateException("context cannot be null");
        }
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        appInfo.metaData.putString("PUSH_APPID", getPushInfo().getAppId());
        appInfo.metaData.putString("PUSH_APPKEY", getPushInfo().getAppKey());
        appInfo.metaData.putString("PUSH_APPSECRET", getPushInfo().getAppSecret());
        mPushStatus=true;

        PushManager.getInstance().initialize(context.getApplicationContext(),GetuiPushService.class);
        PushManager.getInstance().registerPushIntentService(context.getApplicationContext(), GtIntentService.class);
    }

    /**
     * 单点推送需要上传设备id到服务端
     * @param clientid
     * @param iBusActionListener
     */
    @Override
    public void onUploadClientId( final String clientid, final IBusActionListener iBusActionListener) {



    }

    @Override
    public void turnOnPush(Context context ) {
        PushManager.getInstance().turnOnPush(context);
        mPushStatus=true;
    }

    @Override
    public void turnOffPush(Context context) {
        PushManager.getInstance().turnOffPush(context);
        mPushStatus=false;
    }

    @Override
    public boolean getPushStatus() {
        return mPushStatus;
    }


    @Override
    public String getDesc() {
        return "BusGetuiPushService";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
