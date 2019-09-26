package com.osg.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.osg.myapplication.listener.TTSListener;
import com.osg.myapplication.service.WebService;
import com.osg.myapplication.utils.Constant;
import com.osg.myapplication.utils.SpeechUtil;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements MyApplication.DataChangeListener {

    private MainHandler handler;
    private TextView hint;
    //tts语音播放
    public static TextToSpeech mSpeech = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        handler = new MainHandler(this);
        startService(new Intent( getApplicationContext(), WebService.class));

        mSpeech = new TextToSpeech(this.getApplicationContext(), new TTSListener());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hint = (TextView) findViewById(R.id.ttsText);
        /*
        hint.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
            }
        });*/
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        MyApplication.getInstance().registerDataChangeListener(this);

        final String context1 = "你好~";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SpeechUtil.openAudioFile(mSpeech, context1);
            }
        }, 500 );
    }


    @Override
    protected void onDestroy()
    {
        if (mSpeech != null)
            mSpeech.shutdown();
        MyApplication.getInstance().unregisterDataChangeListener(this);
        //activityList.remove(this);
        stopService(new Intent(getApplicationContext(), WebService.class));
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void dataChanged(String paramString, Object paramObject) {
        hint.setText( paramString);
        //SpeechUtil.openAudioFile(mSpeech, "你好啊~");
    }


    private static class MainHandler extends Handler
    {
        private WeakReference<MainActivity> weakReference;

        public MainHandler(MainActivity activity)
        {
            weakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            final MainActivity activity = weakReference.get();
            if (activity == null)
                return;

            switch (msg.what)
            {
                case Constant.MSG.GET_NETWORK_ERROR :
                    //activity.hint.setText("手机网络地址获取失败，即将退出程序");
                    activity.handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            activity.finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 2 * 1000);
                    break;
            }
        }
    }
}
