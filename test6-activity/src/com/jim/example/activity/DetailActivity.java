package com.jim.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/19/14
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class DetailActivity extends Activity implements Button.OnClickListener {
    private TextView infoView;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

        // 加载布局
        setContentView(R.layout.info);

        // 初始化对象
        infoView = (TextView) findViewById(R.id.infoView);
        backBtn = (Button) findViewById(R.id.backBtn);

        // 绑定事件
        backBtn.setOnClickListener(this);

        // 取得 Intent 中的 Bundle 对象
        Bundle bundle = this.getIntent().getExtras();

        // 取得 Bundle 中的对象
        String userName = bundle.getString("userName");

        // 显示信息
        infoView.setText("Hello: " + userName);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn) {
            back();
        }
    }

    private void back() {
        Intent intent = new Intent();

        intent.setClass(this, MyActivity.class);

        startActivity(intent);
    }
}
