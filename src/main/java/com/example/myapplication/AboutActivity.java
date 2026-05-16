package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    TextView txtGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Add this line
        txtGithub = findViewById(R.id.txtGithub);

        // Add this block here
        txtGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/yourusername")
                );

                startActivity(browserIntent);
            }
        });

    }
}