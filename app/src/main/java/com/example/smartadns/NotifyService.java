package com.example.smartadns;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;
import static com.example.smartadns.App.CHANNEL_1_ID;
import static com.example.smartadns.App.CHANNEL_2_ID;

public class NotifyService extends Service {

    private String productID;
    private String status;
    private Notification notification1;
    private NotificationManagerCompat notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificationManager = NotificationManagerCompat.from(this);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal)
                .setContentTitle("Service")
                .setContentText("Started Notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        notificationManager.notify(2, notification);


        notification1 = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.adsn)
                .setContentTitle("Accident")
                .setContentText("There is a accident Please check the application")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        accidentNotify();
        return START_NOT_STICKY;
    }

    private void accidentNotify() {
        FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).exists()){

                    DataSnapshot child = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    User value = child.getValue(User.class);
                    productID = value.getProductId();
                    Log.d(TAG, productID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        FirebaseDatabase.getInstance().getReference("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot child = dataSnapshot.child(productID);
                Log.d(TAG, child.toString());
                MapInfo mapValue = child.getValue(MapInfo.class);
                status = mapValue.getAccidentStatus();
                if(status.equals("1")){
                    Log.d(TAG,"Service"+status);
                    notificationManager.notify(1,notification1);
                    Uri notification1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification1);
                    r.play();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
