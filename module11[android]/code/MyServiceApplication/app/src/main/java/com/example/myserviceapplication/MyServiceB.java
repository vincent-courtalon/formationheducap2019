package com.example.myserviceapplication;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyServiceB extends Service {

    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_UNREGISTER_CLIENT = 2;
    public static final int MSG_SET_COMPTEUR = 3;


    private NotificationManager nm;
    private Timer timer = new Timer();
    private int compteur = 0;
    private int increment = 1;

    // on utilise la classe Messanger pour faciliter la communication
    // chaque messenger va gérer une connection
    private ArrayList<Messenger> mClients = new ArrayList<>();

    // Messenger pour la reception
    private Messenger myMessenger = new Messenger(new IncomingHandler());

    public MyServiceB() {
    }

    private void sendMessageToActivity(int valueToSend) {
        for (int i = mClients.size() - 1; i >= 0 ; i--) {
            try {
                // envoyer un simple message avec le compteur a mes clients
                mClients.get(i)
                        .send(Message.obtain(
                                null, MSG_SET_COMPTEUR, valueToSend, 0));
            } catch (RemoteException e) {
                Log.i("service_b", e.getMessage());
                mClients.remove(i);
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
        compteur= 0;
        Log.i("service_b", "le service est détruit");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("service_b", "creation service");

        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                onTimerTick();
            }
        }, 0, 100);
    }

    public void onTimerTick() {
        Log.i("service_b", "timer is working " + compteur);
        compteur += increment;
        // j'envoie aux activités le compteur mis à jour
        sendMessageToActivity(compteur);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myMessenger.getBinder();
    }

    public class IncomingHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo); break;
                case  MSG_UNREGISTER_CLIENT:
                    mClients.remove((msg.replyTo));
                    break;
                case MSG_SET_COMPTEUR:
                    Log.i("service_B", "set increment");
                    increment = msg.arg1;
                    break;
                default:
                    super.handleMessage(msg);
             }
        }
    }

}
