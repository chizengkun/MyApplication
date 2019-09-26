package com.osg.myapplication.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.osg.myapplication.http.RequestListenerThread;
import com.osg.myapplication.utils.Constant;


public class WebService extends Service
{

    private RequestListenerThread thread;

    @Override
    public void onCreate()
    {
        super.onCreate();

        thread = new RequestListenerThread(Constant.Config.PORT);
        thread.setDaemon(false);
        thread.start();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        new Thread()
        {
            public void run()
            {
                if (thread != null)
                    thread.destroy();
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
