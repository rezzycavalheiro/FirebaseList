package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginButton(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void registerButton(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void listInfoButton(View view){
        Intent intent = new Intent(this, ListUsers.class);
        startActivity(intent);
    }
}