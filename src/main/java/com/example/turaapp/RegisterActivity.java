package com.example.turaapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

public class RegisterActivity extends AppCompatActivity {

    private static final int SECRET_KEY = 99;
    EditText username;
    EditText useremail;
    EditText password;
    EditText passwordagain;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        int secretkey = getIntent().getIntExtra("SECRET_KEY", 0);


        if(secretkey != 99){
            finish();
        }
        username = findViewById(R.id.usernameText);
        useremail = findViewById(R.id.userEmailText);
        password = findViewById(R.id.passwordtext);
        passwordagain = findViewById(R.id.passwordAgainText);

        mAuth = FirebaseAuth.getInstance();
    }

    public void registration(View view){

        String user = username.getText().toString();
        String email = useremail.getText().toString();
        String pwd = password.getText().toString();
        String pwdagain = passwordagain.getText().toString();

        if (!pwdagain.equals(pwd)){
          //  Log.e(LOG_TAG,"Nem egyezik a két jelszó");
            new AlertDialog.Builder(this)
                    .setTitle("A jelszók nem egyeznek")
                    .setMessage("A jelszók nem egyeznek, kérlek próbáld újra.")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
        }else {
           // Log.i(LOG_TAG, "Regisztrált: " + user + ", email: " + email);
            mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        destinations();
                    }else {
                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("Regisztrációs hiba")
                                .setMessage("Regisztrációs hiba, kérlek próbáld újra.")
                                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                .show();
                    }
                }
            });
        }

    }

    public void cancel(View view){
        finish();
    }

    public void destinations(){
        Intent intent = new Intent(this, DestinationActivity.class);
        intent.putExtra("SECRET_KEY", 99);
        startActivity(intent);
    }
}