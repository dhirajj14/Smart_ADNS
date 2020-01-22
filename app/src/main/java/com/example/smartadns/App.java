package com.example.smartadns;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID = "notifyChannel";
    public static final String CHANNEL_2_ID = "serviceChannel";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotitficationChannel();

    }

    private void createNotitficationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, "emregency", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is emergency channel");

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID, "service", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is service channel");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
    }
}
