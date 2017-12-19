package com.jades.bus.jpushservicegtimpl;

import com.jades.bus.common.busservice.BusServiceFactory;
import com.jades.bus.pushservice.model.IPushNotifyModel;
import com.jades.bus.pushservice.model.PushNotifyModel;
import com.jades.bus.pushservice.service.IBusMessageService;
import com.jades.bus.pushservice.service.IBusPushService;

/**
 * Created by wangyan on 2017/12/19.
 */

public class PushMessageProcessor implements IBusMessageService.IMessageProcessor{
    @Override
    public void process(IBusMessageService.IMessage iMessage) {
        //处理接受到的消息

        IPushNotifyModel iPushNotifyModel = new PushNotifyModel("测试", "测试内容" , R.mipmap.ic_launcher);
        IBusPushService iBusPushService = BusServiceFactory.getInstance().getBusService(IBusPushService.class);
        iBusPushService.onShowPushMessage(null, null,null, iPushNotifyModel);//显示推送消息,需要传入Context ,点击
    }
}
