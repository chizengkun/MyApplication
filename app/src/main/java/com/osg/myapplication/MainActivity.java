package com.osg.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.osg.myapplication.listener.TTSListener;
import com.osg.myapplication.server.SocketService;
import com.osg.myapplication.utils.SpeechUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyApplication.DataChangeListener {

    //tts语音播放
    public static TextToSpeech mSpeech = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent( getApplication(), SocketService.class));

        mSpeech = new TextToSpeech(getApplicationContext(), new TTSListener());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.ttsText).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                SpeechUtil.openAudioFile(MainActivity.mSpeech,"你好，欢迎光临！");
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        MyApplication.getInstance().registerDataChangeListener(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        MyApplication.getInstance().unregisterDataChangeListener(this);
        //activityList.remove(this);
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
        ((EditText)findViewById(R.id.ttsText)).setText( paramString);
    }
}
