package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private EditText editTextTextEmailAddress3, editTextTextPassword3, editTextTextPersonName, editTextPhone;
    private Button register;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextTextEmailAddress3 = findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword3 = findViewById(R.id.editTextTextPassword3);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextPhone = findViewById(R.id.editTextPhone);
        firebaseAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference().child("Info");
    }

    protected void onStart() {
        super.onStart();
    }

    public void onRegister(View view){ createUser(); }

    void createUser(){
        String getEmail = editTextTextEmailAddress3.getText().toString().trim();
        String getPassword = editTextTextPassword3.getText().toString().trim();
        Intent loginScreen = new Intent(this, MainActivity.class);
        String getName = editTextTextPersonName.getText().toString();
        String getPhone = editTextPhone.getText().toString();

        User user = new User(getName, getPhone);

        dbReference.push().setValue(user);

        firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(Register.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Usuário criado com sucesso.", Toast.LENGTH_LONG).show();
                            startActivity(loginScreen);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Erro ao criar usuário.", Toast.LENGTH_LONG).show();
                            Log.e("FIREBASE", "Creat error" + task.getException().toString());
                        }
                    }
                });
    }
}