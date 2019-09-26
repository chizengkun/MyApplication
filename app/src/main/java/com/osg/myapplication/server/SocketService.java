package com.osg.myapplication.server;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class SocketService extends Service {
    private int port = Constants.defaultPort;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        new Thread(new Runnable()
        {
            public void run()
            {
                HttpServer localHttpServer = new HttpServer();
                /*while (true)
                {*/
                    try
                    {
                            localHttpServer.start(SocketService.this.port);
                            return;
                        /*if (Utils.checkPort(Constants.portAry[i]))
                        {
                            SocketService.this.port = Constants.portAry[i];
                            continue;
                        }*/
                    }
                    catch (Exception localException)
                    {
                        //AppListYPLog.e("异常:" + localException.getMessage());
                        return;
                    }
                // }
            }
        }).start();
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //registerReceiver(this.receiver, localIntentFilter);
    }
}
