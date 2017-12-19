package com.jades.bus.jpushservicegtimpl;

import com.jades.bus.common.busservice.BusServiceFactory;
import com.jades.bus.pushservice.service.BusMessageService;
import com.jades.bus.pushservice.service.IBusMessageService;
import com.jades.bus.pushservice.service.IBusPushService;
import com.jades.bus.pushservicegtimpl.model.PushMessage;

import android.app.Application;

/**
 * Created by wangyan on 2017/12/19.
 */

public class TestApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //注册服务的具体实现类
        BusServiceFactory.getInstance().register(IBusPushService.class,PushService.class);
        BusServiceFactory.getInstance().register(IBusMessageService.class, BusMessageService.class);

        //为消息添加处理器
        IBusMessageService iBusMessageService = BusServiceFactory.getInstance().getBusService(IBusMessageService.class);
        iBusMessageService.addMessageProcessor(PushMessage.class,new PushMessageProcessor());
    }
}


