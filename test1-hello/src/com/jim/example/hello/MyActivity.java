package com.jim.example.hello;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextView txt;
    private static final int SHOW_DATE_TIME = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_DATE_TIME) {
                showDateTime();
            }
        }
    };

    private void showDateTime() {
        String dateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        txt.setText(dateTime);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txt = (TextView) findViewById(R.id.textView);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Error Permission denied
                        //txt.setText(DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                        Message message = new Message();
                        message.what = SHOW_DATE_TIME;
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
            }
        }.start();
    }
}
