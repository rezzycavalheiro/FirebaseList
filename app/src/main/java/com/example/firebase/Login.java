package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Login extends AppCompatActivity {

    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    protected void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            Toast.makeText(getApplicationContext(),"Usuário logado.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ListUsers.class);
            startActivity(intent);
        }
    }

    public void onLogin(View view){ checkLogin(); }

    public void checkLogin() {
        String getEmail = editTextTextEmailAddress.getText().toString().trim();
        String getPassword = editTextTextPassword.getText().toString().trim();
        Intent listUsers = new Intent(this, ListUsers.class);

        firebaseAuth.signInWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(Login.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Usuário logado com sucesso.", Toast.LENGTH_LONG).show();
                            startActivity(listUsers);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Erro ao logar usuário.", Toast.LENGTH_LONG).show();
                            Log.e("FIREBASE", "Creat error" + task.getException().toString());
                        }
                    }
                });;
    }
}