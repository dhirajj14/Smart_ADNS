package com.example.smartadns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterUSer extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText registerFirstName;
    private EditText registerLastName;
    private EditText registerEmailId;
    private EditText registerPassword;
    private EditText registerProductId;
    private Button registerUserButton;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String productId;
    private static final String TAG="Log";



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
        registerUserButton = findViewById(R.id.registerUserButton);

        registerUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                firstName = registerFirstName.getText().toString();
                lastName = registerLastName.getText().toString();
                email = registerEmailId.getText().toString();
                password = registerPassword.getText().toString();
                productId = registerProductId.getText().toString();

                registerUser(email, password);
            }
        });

    }

    void registerUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterUSer.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterUSer.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
