package com.osg.myapplication.listener;

import android.speech.tts.TextToSpeech;
import android.util.Log;

public class TTSListener implements TextToSpeech.OnInitListener {
    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            Log.i("TTS", "onInit: TTS引擎初始化成功");
        } else{
            Log.i("TTS", "onInit: TTS引擎初始化失败");
        }
    }
}
