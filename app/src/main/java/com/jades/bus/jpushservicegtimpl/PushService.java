package com.jades.bus.jpushservicegtimpl;

import com.jades.bus.pushservicegtimpl.BusGetuiPushService;
import com.jades.bus.pushservicegtimpl.model.GetuiInfo;

/**
 * Created by wangyan on 2017/12/19.
 */

public class PushService extends BusGetuiPushService {
    @Override
    public IPushInfo getPushInfo() {
        IPushInfo info = new GetuiInfo("","","");//传入个推需要配置的参数

        return info;
    }
}
