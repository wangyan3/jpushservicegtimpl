# jpushservicegtimpl
推送服务实现了个推，5分钟实现个推集成

调用步骤：
在项目根目录的- `build.gradle`添加库引用
```
maven {url "http://mvn.gt.igexin.com/nexus/content/repositories/releases/"}
maven { url 'https://jitpack.io' }
```
分部是引用个推的库和别的依赖库，再在app目录下-`build.gradle`的-`dependcies`中添加
```dependencies {
    api 'com.github.wangyan3:jcommon:1.0.2'
    api 'com.github.wangyan3:jpushservice:1.0.0'
    api 'com.github.wangyan3:jpushservicegtimpl:1.0.0'
    }
```
现在开始配置个推的参数在-`build.gradle`的-`defaultConfig`中加入
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

