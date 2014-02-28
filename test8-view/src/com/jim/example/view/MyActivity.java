package com.jim.example.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private RadioButton radioButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        radioButton = (RadioButton) findViewById(R.id.radioButton);


    }
}
