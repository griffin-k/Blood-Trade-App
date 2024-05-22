package com.blood.trade;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blood.trade.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();

        new CountDownTimer(1000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Do nothing on tick
            }

            @Override
            public void onFinish() {
                if (user != null) {
                    getSelf();
                } else {
                    redirectToLogin();
                }
            }
        }.start();
    }

    private void getSelf() {
        FirebaseDatabase.getInstance().getReference("Donors/" + user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        if (user != null && user.getStep() != null) {
                            switch (user.getStep()) {
                                case "1":
                                    startActivity(new Intent(SplashScreen.this, RegisterIIActivity.class));
                                    break;
                                case "2":
                                    startActivity(new Intent(SplashScreen.this, RegisterIIIActivity.class));
                                    break;
                                case "Done":
                                    startActivity(new Intent(SplashScreen.this, DispalyRequestsActivity.class));
                                    break;
                                default:
                                    // Handle unknown step
                                    break;
                            }
                        } else {
                            redirectToLogin();
                        }
                        SplashScreen.this.finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle onCancelled event
                    }
                });
    }

    private void redirectToLogin() {
        startActivity(new Intent(SplashScreen.this, LoginScreenActivity.class));
        SplashScreen.this.finish();
    }
}
