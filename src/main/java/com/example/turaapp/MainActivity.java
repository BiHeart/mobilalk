package com.example.turaapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final int SECRET_KEY = 99;

    EditText username;
    EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextUserName);
        password = findViewById(R.id.editTPassword);

        mAuth = FirebaseAuth.getInstance();

    }

    public void login(View view) {

        String user = username.getText().toString();
        String pwd = password.getText().toString();

        mAuth.signInWithEmailAndPassword(user, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    destinations();
                }else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Hibás adatok")
                            .setMessage("Az e-mail cím vagy a jelszó hibás, kérlek próbáld újra.")
                            .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                            .show();
                }
            }
        });

    }

    public void register(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("SECRET_KEY", 99);
        startActivity(intent);

    }

    public void destinations(){
        Intent intent = new Intent(this, DestinationActivity.class);
        intent.putExtra("SECRET_KEY", 99);
        startActivity(intent);
    }
}