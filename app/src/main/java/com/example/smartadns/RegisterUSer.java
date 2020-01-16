package com.example.smartadns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterUSer extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText registerFirstName;
    private EditText registerLastName;
    private EditText registerEmailId;
    private EditText registerPassword;
    private EditText registerProductId;
    private Button registerUserButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registerFirstName = findViewById(R.id.registerFirstName);
        registerLastName = findViewById(R.id.registerLastName);
        registerEmailId = findViewById(R.id.registerEmailId);
        registerPassword  = findViewById(R.id.registerPassword);
        registerProductId = findViewById(R.id.registerProductId);
        registerUserButton = findViewById(R.id.registerBtn);

        registerUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}
