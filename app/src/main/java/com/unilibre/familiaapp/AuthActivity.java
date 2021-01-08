package com.unilibre.familiaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.unilibre.familiaapp.ui.login.LoginActivity;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        fAuth = FirebaseAuth.getInstance();
    }

    protected void onStart(){
        super.onStart();
        Log.d("Auth", fAuth.getCurrentUser() + "");
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(AuthActivity.this, HomeActivity.class));
        } else {
            startActivity(new Intent(AuthActivity.this, LoginActivity.class));
        }
    }
}
