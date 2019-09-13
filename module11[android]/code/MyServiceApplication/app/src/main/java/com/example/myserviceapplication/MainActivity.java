package com.example.myserviceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // compteur renvoyé par le service
    private TextView texteCompteur;
    // notre canal de communication
    // pour ENVOYER nos messages au service
    private Messenger canal_envoie_message_ServiceB;
    // le canal de communication avec le service
    // celui que nous renvoie le service
    // celui par le lequel on recevra les messages du service
    Messenger canal_reception_message_serviceB = new Messenger(new IncomingHandler());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MyServiceA.class));
        texteCompteur = findViewById(R.id.messageCompteur);

    }
    // la connection a un service
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(
                ComponentName componentName,
                IBinder service) {
            canal_envoie_message_ServiceB = new Messenger(service);
            Log.i("mainactivity", "connecté au service B !");
            Message msg = Message.obtain(null, MyServiceB.MSG_REGISTER_CLIENT);
            msg.replyTo = canal_reception_message_serviceB;
            try {
                canal_envoie_message_ServiceB.send(msg);
            } catch (RemoteException e) {e.printStackTrace();}
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            canal_envoie_message_ServiceB = null;
        }
    };

    public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.i("mainactivity", "message recu :" + msg.arg1 + " " + msg.arg2);
            if (msg.what == MyServiceB.MSG_SET_COMPTEUR) {
                texteCompteur.setText("compteur: " + msg.arg1);
            }
            else {
                super.handleMessage(msg);
            }
        }
    }
    // attacher un service
    private void doBindService() {
        // demande a s'attacher a un service
        // en lui passant l'objet connection avec les informations sur la connection
        // et un flag demandant de creer le service s'il n'existe pas
        bindService(new Intent(this, MyServiceB.class),
                    mConnection,
                    Context.BIND_AUTO_CREATE);
    }

    public void clickStartService(View v) {
        // démarrer le service
        startService(new Intent(this, MyServiceB.class));
    }
    public void clickStopService(View v) {
        unBindFromService(v);
        stopService(new Intent(this, MyServiceB.class));
    }
    public void bindToService(View v) {
        doBindService();
    }
    public void unBindFromService(View v) {
        if (canal_envoie_message_ServiceB != null) {
            // message pour unregister
            Message msg = Message.obtain(null,
                                        MyServiceB.MSG_UNREGISTER_CLIENT);
            msg.replyTo = canal_reception_message_serviceB;
            try {
                canal_envoie_message_ServiceB.send((msg));
            } catch (RemoteException e) {e.printStackTrace();}
        }
        // fonction "systeme" pour desatacher le service
        unbindService(mConnection);
    }


}
