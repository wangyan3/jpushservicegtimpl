package com.jades.bus.pushservicegtimpl;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.jades.bus.common.busservice.BusServiceFactory;
import com.jades.bus.common.busservice.IBusActionListener;
import com.jades.bus.pushservice.service.IBusPushService;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class GtIntentService extends GTIntentService {


    private boolean mUpload;
    private String mClientId;
    public GtIntentService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {

    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        if(msg.getPayload()!=null){
            String message = new String(msg.getPayload());
            Log.e("onReceiveMessageData","message="+message );
            IBusPushService iBusPushService = BusServiceFactory.getInstance().getBusService(IBusPushService.class);
            iBusPushService.onReceivedMessage(message);
        }

    }

    @Override
    public void onReceiveClientId(Context context, final String clientid) {
        Log.e("onReceiveClientId","clientid="+clientid );
        mClientId= clientid;

        if(!mUpload&& !TextUtils.isEmpty(mClientId)){
            mUpload=true;
            uploadClientId();
        }




    }
    private void uploadClientId(){
        IBusPushService iBusPushService = BusServiceFactory.getInstance().getBusService(IBusPushService.class);
        iBusPushService.onUploadClientId( mClientId, new IBusActionListener() {
            @Override
            public void onFinish(boolean isSuccess, String desc, Object o) {
                if(!isSuccess){

                    mUpload=false;
                    mClientId = "";
                }
            }
        });

    }
    @Override
    public void onReceiveOnlineState(Context context, boolean online) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {

    }




}