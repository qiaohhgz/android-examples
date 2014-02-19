package com.jim.example.tiemr;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/18/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyActivity extends Activity {
    private MyCount mc;
    private EditText editText;
    private TextView tv;
    private Button startBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initializeViews();
    }

    private void initializeViews() {
        editText = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.myTxt);
        startBtn = (Button) findViewById(R.id.startBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        startBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                int val = Integer.valueOf(s).intValue() * 1000;
                mc = new MyCount(val, 1000);
                mc.start();
            }
        });
        cancelBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mc.cancel();
                tv.setText("cancel");
                System.out.println("cancel");
            }
        });
    }

    class MyCount extends CountDownTimer {
        private long millisInFuture;

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.millisInFuture = millisInFuture;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String info = "请等待" + (millisInFuture / 1000) + "秒(" + millisUntilFinished / 1000 + ")...";
            System.out.println(info);
            tv.setText(info);
        }

        @Override
        public void onFinish() {
            tv.setText("finish");
            System.out.println("finish");
        }
    }
}
