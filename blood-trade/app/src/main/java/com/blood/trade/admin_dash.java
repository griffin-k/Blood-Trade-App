package com.blood.trade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.blood.trade.admin.displayaccepter;
import com.blood.trade.admin.displydonners;


public class admin_dash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        // Get references to the ImageView elements
        ImageView imageDonner = findViewById(R.id.donnercardimg);
        ImageView imageAccepter = findViewById(R.id.aceptercardimg);

        // Set OnClickListener for Donner card
        imageDonner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DonnerActivity
                Intent intent = new Intent(admin_dash.this, displydonners.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for Accepter card
        imageAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AccepterActivity
                Intent intent = new Intent(admin_dash.this, displayaccepter.class);
                startActivity(intent);
            }
        });
    }
}
