# jpushservicegtimpl
推送服务实现了个推，5分钟实现个推集成
首先这个模块需要感谢我们的组长  是他带领我们团队 实现了模块化开发  
## @xmgcoffee<https://github.com/xmgcoffee>


调用步骤：在项目根目录的`build.gradle`添加库引用
```
maven {url "http://mvn.gt.igexin.com/nexus/content/repositories/releases/"}
maven { url 'https://jitpack.io' }
```
分部是引用个推的库和别的依赖库，再在app目录下-`build.gradle`的-`dependcies`中添加
```
dependencies {
    api 'com.github.wangyan3:jcommon:1.0.2'
    api 'com.github.wangyan3:jpushservice:1.0.0'
    api 'com.github.wangyan3:jpushservicegtimpl:1.0.0'
    api 'com.getui:sdk:2.11.1.0'
}
```
现在开始配置个推的参数在`build.gradle`的`defaultConfig`中加入
```
defaultConfig {
       //此处加入自己申请的个推参数
       manifestPlaceholders = [
                GETUI_APP_ID:"",
                GETUI_APP_KEY:"",
                GETUI_APP_SECRET:""
        ]
        ndk {
            abiFilters "armeabi", "armeabi-v7a", "x86_64", "arm64-v8a", "mips" ,"x86"
        }
        packagingOptions {
            pickFirst "lib/mips64/libgetuiext2.so"
            pickFirst "lib/x86_64/libgetuiext2.so"
            pickFirst "lib/armeabi-v7a/libgetuiext2.so"
            pickFirst "lib/armeabi/libgetuiext2.so"
            pickFirst "lib/arm64-v8a/libgetuiext2.so"
            pickFirst "lib/x86/libgetuiext2.so"
            pickFirst "lib/mips/libgetuiext2.so"
        }
}
```
配置完成后就开始实现自己的个推服务和个推消息处理器了，首先新建自己的推送服务类
```
public class PushService extends BusGetuiPushService {
    @Override
    public IPushInfo getPushInfo() {
        IPushInfo info = new GetuiInfo("","","");//传入个推需要配置的参数，分别是appid,appkey,appsecret

        return info;
    }
    //点对点推送时需要像服务端上传clientId
    @Override
    public void onUploadClientId(String clientid, IBusActionListener iBusActionListener) {
        super.onUploadClientId(clientid, iBusActionListener);
    }
}
```
然后再新建消息的处理器
```
public class PushMessageProcessor implements IBusMessageService.IMessageProcessor{
    @Override
    public void process(IBusMessageService.IMessage iMessage) {
        //处理接受到的消息 
        PushMessage pushMessage = (PushMessage) iMessage;
        IPushNotifyModel iPushNotifyModel = new PushNotifyModel("测试", pushMessage.getPushConent() , R.mipmap.ic_launcher);
        IBusPushService iBusPushService = BusServiceFactory.getInstance().getBusService(IBusPushService.class);
        //显示推送消息,需要传入Context,intent,bundle和显示通知的需要的model
        iBusPushService.onShowPushMessage(null, null,null, iPushNotifyModel);
    }
}
```
最后,只需要在application配置你的服务
```
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
```

## 如果调用有问题，可以qq联系我  763798314




        
