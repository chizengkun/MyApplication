在Andriod中实现Http服务器，通过http服务不同的请求实现界面的不同变化

重点：
 1 需注册Service服务，在manifest 中增加
    <service
            android:name=".service.WebService"
            android:enabled="true"
            android:exported="true" >
        </service>
        
  2 需建立 MyApplication extends Application ， 通过系统间传递数据，不能在请求中直接处理数据
  3 理解通接口实现回调处理
   public static abstract interface DataChangeListener
