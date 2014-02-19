package com.jim.example.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements Button.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button startServiceBtn;
    private Button stopServiceBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startServiceBtn = (Button) findViewById(R.id.startServiceBtn);
        startServiceBtn.setOnClickListener(this);
        stopServiceBtn = (Button) findViewById(R.id.stopServiceBtn);
        stopServiceBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        System.out.println("view id = " + view.getId());
        switch (view.getId()) {
            case R.id.startServiceBtn:
                System.out.println("click start service");
                Intent i = new Intent(this, MyService.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startService(i);
                break;
            case R.id.stopServiceBtn:
                System.out.println("click stop service");
                stopService(new Intent(this, MyService.class));
                break;
        }
    }
}
