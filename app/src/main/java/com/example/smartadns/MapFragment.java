package com.example.smartadns;


import android.app.Notification;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;
import static com.example.smartadns.App.CHANNEL_1_ID;


public class MapFragment extends Fragment implements OnMapReadyCallback  {


    private MapView mapView;
    private GoogleMap mMap;
    private LatLng marker;
    private String productID;
    private NotificationManagerCompat notificationManager;

    private String status;

    private Notification notification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        notificationManager = NotificationManagerCompat.from(getView().getContext());

        notification = new NotificationCompat.Builder(getView().getContext(), CHANNEL_1_ID)
                .setSmallIcon(R.drawable.adsn)
                .setContentTitle("Accident")
                .setContentText("There is a accident Please check the application")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

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

        FirebaseDatabase.getInstance().getReference("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot child = dataSnapshot.child(productID);
                Log.d(TAG, child.toString());
                MapInfo mapValue = child.getValue(MapInfo.class);
                marker = new LatLng((Float.parseFloat(mapValue.getLatitude())/100),(Float.parseFloat(mapValue.getLongitude())/100));
                System.out.println(marker);
                // Add a marker in Sydney, Australia, and move the camera.
                status = mapValue.getAccidentStatus();
                if(marker != null) {
                    mMap.addMarker(new MarkerOptions().position(marker).title("Accident Place"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));

                }
                if(status.equals("1")){
                    Log.d(TAG,"Service"+status);
                    notificationManager.notify(1,notification);
                    Uri notification1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                    Ringtone r = RingtoneManager.getRingtone(getView().getContext(), notification1);
                    r.play();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
