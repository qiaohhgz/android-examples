package com.jim.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class MyActivity extends Activity implements Button.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private TextView userNameInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        userNameInput = (TextView) findViewById(R.id.userNameInput);
        findViewById(R.id.loginBtn).setOnClickListener(this);

        // Set Test Data
        userNameInput.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                String userName = userNameInput.getText().toString();
                buildIntent(userName);
                break;
        }
    }

    private void buildIntent(String userName) {
        Intent intent = new Intent();
        // 指定目标class
        intent.setClass(this, DetailActivity.class);

        // 创建一个Bundle对象 并放入数据
        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);

        // 将 bundle 对象 assign 给 Intent
        intent.putExtras(bundle);

        // 调用 Activity
        startActivity(intent);
    }
}
