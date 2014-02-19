package com.jim.example.ring;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextView txt;
    private ProgressBar ringVolume;
    private Button ringSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initializeViews();

        loadSystemData();
    }

    private void loadSystemData() {
        txt.setText("");//clear content

        //To change body of created methods use File | Settings | File Templates.
        //Android 程序获取、设置铃声和音量
        //通过程序获取android系统手机的铃声和音量。同样，设置铃声和音量的方法也很简单！
        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //通话音量
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        Log.d("VIOCE_CALL", "max: " + max + " current : " + current);
        printLine("VIOCE_CALL", "max: " + max + " current : " + current);

        //系统音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        Log.d("SYSTEM", "max : " + max + " current : " + current);
        printLine("SYSTEM", "max : " + max + " current : " + current);

        //铃声音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
        ringVolume.setMax(max);
        ringVolume.setProgress(current);
        Log.d("RING", "max : " + max + " current : " + current);
        printLine("RING", "max : " + max + " current : " + current);

        //音乐音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d("MUSIC", "max : " + max + " current : " + current);
        printLine("MUSIC", "max : " + max + " current : " + current);

        //提示声音音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        Log.d("ALARM", "max : " + max + " current : " + current);
        printLine("ALARM", "max : " + max + " current : " + current);
    }

//        设置音量的方法也很简单，AudioManager提供了方法：

    public void setStreamVolume(int streamType, int index, int flags) {
//        streamType为铃声类型，例如：AudioManager.STREAM_VOICE_CALL、AudioManager.STREAM_SYSTEM等，index为音量大小
//        falgs为标志位。


//        设置振动：
//    mVibrator=(Vibrator)mContext.getSystemService(Service.VIBRATOR_SERVICE);
//    long[] pattern = {150, 100}; // OFF/ON/OFF/ON...
//    mVibrator.vibrate(pattern,-1);
//        静音：
//        设置系统声音为0就行

//通话时设置静音
//        System.out.println("isMicrophoneMute =" + audioManager.isMicrophoneMute());
//        audioManager.setMicrophoneMute(!audioManager.isMicrophoneMute());

//通话时设置免提
//        System.out.println("isSpeakerphoneOn =" + audioManager.isSpeakerphoneOn());
//        audioManager.setSpeakerphoneOn(!audioManager.isSpeakerphoneOn());
//别忘了修改的权限 <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS"/>

    }

    private void initializeViews() {
        txt = (TextView) findViewById(R.id.txt);

        ringVolume = (ProgressBar) findViewById(R.id.ringProgressBar);
        ringVolume.setOnFocusChangeListener(new ProgressBar.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                System.out.println("change ring volume");
            }
        });

        ringSave = (Button) findViewById(R.id.ringSave);
        ringSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int newVolume = ringVolume.getProgress();
                System.out.println("new volume = " + newVolume);
                mAudioManager.setStreamVolume(AudioManager.STREAM_RING, newVolume, AudioManager.FLAG_PLAY_SOUND);
                loadSystemData();
            }
        });
    }

    private void printLine(String title, String body) {
        txt.append("\n" + title + ": " + body + "\n");
    }

    private void printLine(String format, Object... params) {
        txt.append(String.format("\n" + format + "\n", params));
    }
}
