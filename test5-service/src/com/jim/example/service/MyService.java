package com.jim.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/18/14
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyService extends Service {
    private Handler objHandler = new Handler();
    private int intCounter = 0;

    private Runnable mTasks = new Runnable() {
        @Override
        public void run() {
            intCounter++;

            Log.i(getClass().getName(), "Counter:" + intCounter);
            System.out.println("Counter: " + intCounter);

            objHandler.postDelayed(mTasks, 1000);
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("on bind");
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCreate() {
        System.out.println("on create");
        objHandler.postDelayed(mTasks, 1000);
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onDestroy() {
        System.out.println("on destroy");
        objHandler.removeCallbacks(mTasks);
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
    }

}
