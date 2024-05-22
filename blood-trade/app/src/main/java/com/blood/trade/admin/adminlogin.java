package com.blood.trade.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blood.trade.R;
import com.blood.trade.SplashScreen;
import com.blood.trade.admin_dash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class adminlogin extends AppCompatActivity {


    com.google.android.material.textfield.TextInputEditText Email,Pass;
    com.google.android.material.button.MaterialButton login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        Email = findViewById(R.id.emailLogin);
        Pass = findViewById(R.id.passLogin);
    }

    public void OpenRegisterActivity(View view) {
        startActivity(new Intent(adminlogin.this, SplashScreen.class));
    }


    public void LoginNow(View view) {
        if(!Email.getText().toString().isEmpty() && !Pass.getText().toString().isEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(Email.getText().toString(),Pass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(adminlogin.this, admin_dash.class));
                            }else {
                                Toast.makeText(adminlogin.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}