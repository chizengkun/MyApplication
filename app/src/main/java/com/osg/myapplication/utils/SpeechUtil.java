package com.osg.myapplication.utils;

import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class SpeechUtil {
    //语速
    public static float voice_speed=0.8f;
    //音调
    public static float voice_pitch=0.9f;

    public synchronized static void openAudioFile(TextToSpeech mSpeech, String content) {
        mSpeech.setLanguage(Locale.CHINESE);
        mSpeech.setSpeechRate(voice_speed);
        mSpeech.setPitch(voice_pitch);
/**
 * queueMode用于指定发音队列模式，有两个参数：
 * TextToSpeech.QUEUE_FLUSH:该模式下在有新任务时候会清除当前语音任务，执行新的语音任务
 * TextToSpeech.QUEUE_ADD:该模式下会把新的语音任务放到语音任务之后，等前面的语音任务执行完了才会执行新的语音任务
 */
//mSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null);
        mSpeech.speak(content, TextToSpeech.QUEUE_ADD, null);
    }
}
