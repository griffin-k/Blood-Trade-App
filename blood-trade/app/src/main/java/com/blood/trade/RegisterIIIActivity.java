package com.blood.trade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterIIIActivity extends AppCompatActivity {

    AutoCompleteTextView bloodgrp;
    AutoCompleteTextView donnerGrp;
    com.google.android.material.textfield.TextInputEditText mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_i_i_i);

        initializeComponents();

        // Populate blood group dropdown
        String[] bloodGroups = getResources().getStringArray(R.array.blood_groups);
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, bloodGroups);
        bloodgrp.setAdapter(bloodAdapter);

        // Populate donner group dropdown
        String[] donnerGroups = getResources().getStringArray(R.array.donner_groups);
        ArrayAdapter<String> donnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, donnerGroups);
        donnerGrp.setAdapter(donnerAdapter);
    }

    private void initializeComponents() {
        bloodgrp = findViewById(R.id.bloodGrpDropDown);
        donnerGrp = findViewById(R.id.donnerGrpDropDown);
        mobile = findViewById(R.id.mobileEditText);
    }

    public void verifyAndSubmit(View view) {
        String mobileNumber = mobile.getText().toString();
        String bloodGroup = bloodgrp.getText().toString();
        String donnerGroup = donnerGrp.getText().toString();

        if (mobileNumber.isEmpty()) {
            mobile.setError("Enter Mobile Number!");
            return;
        }

        if (bloodGroup.isEmpty() || donnerGroup.isEmpty()) {
            // Handle empty fields
            return;
        }

        // Create a HashMap to store user data
        HashMap<String, Object> values = new HashMap<>();
        values.put("Step", "Done");
        values.put("Mobile", mobileNumber);
        values.put("BloodGroup", bloodGroup);
        values.put("DonnerGroup", donnerGroup);
        values.put("Visible", "True");

        // Add data to the database directly
        FirebaseDatabase.getInstance().getReference("Donors/" + FirebaseAuth.getInstance().getUid())
                .updateChildren(values)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(RegisterIIIActivity.this, DispalyRequestsActivity.class));
                            RegisterIIIActivity.this.finish();
                        } else {
                            Toast.makeText(RegisterIIIActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
