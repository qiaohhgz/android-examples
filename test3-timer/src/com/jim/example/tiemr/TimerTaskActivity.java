package com.jim.example.tiemr;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 14-3-4
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class TimerTaskActivity extends Activity {
    private static final String TAG = "TimerTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.timer_task);

        final Handler handler = new Handler();


        TimerTask task = new TimerTask() {
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        Timer timer = new Timer(true);
        timer.schedule(task, 1000, 1000);
    }
}
