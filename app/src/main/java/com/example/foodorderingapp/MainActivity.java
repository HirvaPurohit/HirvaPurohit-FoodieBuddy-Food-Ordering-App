package com.example.foodorderingapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    Button btnsignupLP , btnforgotpasswordLP,btnloginLP;
    EditText etemailLP,etPasswordLP;

        FirebaseAuth mAuth;

        ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignupLP = findViewById(R.id.btnsignupLP);
//        btnforgotpasswordLP = findViewById(R.id.btnforgotpasswordLP);
        btnloginLP = findViewById(R.id.btnloginLP);
        etPasswordLP = findViewById(R.id.etPasswordLP);
        etemailLP = findViewById(R.id.etemailLP);
        progressBar = findViewById(R.id.progressbar);


//        boolean aBoolean = sharedPreferences.getBoolean("login",false);
//        if(aBoolean)
//        {
//            Intent i = new Intent(MainActivity.this, HomeActivity.class);
//            startActivity(i);
//            finish();
//        }


        mAuth = FirebaseAuth.getInstance();

        btnloginLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaillogin, passwordlogin;

                SharedPreferences sharedPreferences = getSharedPreferences("logindata",MODE_PRIVATE);
                SharedPreferences.Editor  editor = sharedPreferences.edit();

                emaillogin = String.valueOf(etemailLP.getText());
                passwordlogin = String.valueOf(etPasswordLP.getText());

                if (emaillogin.equals("") || passwordlogin.equals("")) {

                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }

                    editor.putString("Email",emaillogin);
//                    editor.putBoolean("login",true);
                    editor.apply();


                mAuth.signInWithEmailAndPassword(emaillogin, passwordlogin)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(i);
                                finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        btnsignupLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);

            }
        });

//        btnforgotpasswordLP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
//                startActivity(i);
//            }
//        });

    }


}

