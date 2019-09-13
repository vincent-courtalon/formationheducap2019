package com.example.myserviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyServiceA extends Service {
    public MyServiceA() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service_A", "bind du service A");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("service_A", "unbind service A");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("service_A", "creation service A");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("service_A", "destruction service A");
    }
}
