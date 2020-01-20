package com.example.smartadns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button registerButton;
    private Button loginButton;
    private EditText loginEmailId;
    private EditText loginPassword;
    private String email;
    private String password;
    private static final String TAG="Log";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registerButton = findViewById(R.id.registerBtn);
        loginButton = findViewById(R.id.loginBtn);

        loginEmailId = findViewById(R.id.loginEmailId);
        loginPassword  = findViewById(R.id.loginPassword);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerBtn  = new Intent(MainActivity.this, RegisterUSer.class);
                startActivity(registerBtn);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!validateForm()){
                   Toast.makeText(MainActivity.this, "Enter all the fields",
                           Toast.LENGTH_SHORT).show();
               }else {
                   login(email,password);
               }
            }
        });
    }

    void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent login  = new Intent(MainActivity.this, Controller.class);
                            startActivity(login);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    //auto_login with email and password
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser !=null){
            Intent w_menu = new Intent(MainActivity.this, Controller.class);
            w_menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            w_menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(w_menu);
            finish();

        }
    };
    //end

    private boolean validateForm() {
        boolean valid = true;
        email = loginEmailId.getText().toString();
        password = loginPassword.getText().toString();

        email = loginEmailId.getText().toString();
        if (TextUtils.isEmpty(email)) {
            loginEmailId.setError(Html.fromHtml("Reqired"));
            valid = false;
        } else {
            loginEmailId.setError(null);
        }

        password = loginPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            loginPassword.setError(Html.fromHtml("Reqired"));
            valid = false;
        } else {
            loginPassword.setError(null);
        }
        return valid;
    }

}
