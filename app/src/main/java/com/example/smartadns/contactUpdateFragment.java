package com.example.smartadns;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class contactUpdateFragment extends Fragment {

    private EditText updateFirstName;
    private EditText updateLastName;
    private EditText updatecontactNo;
    private Button updateInfoButton;
    DatabaseReference myRef ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateFirstName = getView().findViewById(R.id.updateContactFirstName);
        updateLastName = getView().findViewById(R.id.updateContactLastName);
        updatecontactNo = getView().findViewById(R.id.updateContactNo);
        updateInfoButton = getView().findViewById(R.id.updateContactInfo);


        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef = FirebaseDatabase.getInstance().getReference("Users/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
                myRef.child("firstName").setValue(updateFirstName.getText().toString());
                myRef.child("lastName").setValue(updateLastName.getText().toString());
                myRef.child("contact").setValue(updatecontactNo.getText().toString());

            }
        });
    }
}
