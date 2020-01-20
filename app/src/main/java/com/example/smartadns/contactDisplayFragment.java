package com.example.smartadns;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




/**
 * A simple {@link Fragment} subclass.
 */
public class contactDisplayFragment extends Fragment {

    private TextView contactName;
    private TextView contactEmailId;
    private TextView contactNo;
    private TextView contactProductId;

    public contactDisplayFragment() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_display, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactName = getView().findViewById(R.id.contactName);
        contactEmailId = getView().findViewById(R.id.contactEmail);
        contactNo = getView().findViewById(R.id.contactNo);
        contactProductId = getView().findViewById(R.id.contactID);

        FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).exists()){
                     DataSnapshot child = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        User value = child.getValue(User.class);
                        contactName.setText(value.getFirstName() +" "+ value.getLastName());
                        contactEmailId.setText(value.getEmail());
                        contactNo.setText(value.getContact());
                        contactProductId.setText(value.getProductId());
                 }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
