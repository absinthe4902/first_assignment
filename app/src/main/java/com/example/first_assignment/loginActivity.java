package com.example.first_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    String sub1, sub2, sub3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_login);

        Intent intent = getIntent();
        sub1 = intent.getStringExtra("id_value");
        sub2 = intent.getStringExtra("pw_value");
        sub3 = intent.getStringExtra("android_id_value");

        Toast.makeText(this, sub1 +" " + sub2 +" " + sub3, Toast.LENGTH_SHORT).show();




    }
}
