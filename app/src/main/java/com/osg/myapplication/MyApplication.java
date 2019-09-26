package com.osg.myapplication;

import android.app.Application;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyApplication extends Application  {
    private static MyApplication instance = null;
    List<DataChangeListener> dataChangeListenerList = new ArrayList();

    public static MyApplication getInstance()
    {
        if (instance == null)
            instance = new MyApplication();

        return instance;
    }

    public void fireDataChangeListener(String paramString, Object paramObject)
    {
        Iterator localIterator = this.dataChangeListenerList.iterator();
        while (true)
        {
            if (!localIterator.hasNext())
                return;
            ((DataChangeListener)localIterator.next()).dataChanged(paramString, paramObject);
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        // CrashHandler.getInstance().init(getApplicationContext());
        //initialEnv();
        //initialTts();
    }

    public void unregisterDataChangeListener(DataChangeListener paramDataChangeListener)
    {
        this.dataChangeListenerList.remove(paramDataChangeListener);
    }

    public void registerDataChangeListener(DataChangeListener paramDataChangeListener)
    {
        this.dataChangeListenerList.add( paramDataChangeListener);
    }

    public static abstract interface DataChangeListener
    {
        public abstract void dataChanged(String paramString, Object paramObject);
    }
}
