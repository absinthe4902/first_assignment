package com.example.first_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text1;
    EditText text2;
    Button btn;

    String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.log_id);
        text2 = findViewById(R.id.log_pw);
        btn = findViewById(R.id.log_button);
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        //id는 2로, 비밀번호는 1로 설정


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text1.getText().toString().equals("2") && text2.getText().toString().equals("1") ) {
                    //맞는 조건
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("id_value", text1.getText().toString());
                    intent.putExtra("pw_value", text2.getText().toString());
                    intent.putExtra("android_id_value", android_id);

                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), R.string.login_warning, Toast.LENGTH_SHORT).show();
                    text1.setText("");
                    text2.setText("");
                    text1.setHint(R.string.id_hint);
                    text2.setHint(R.string.pw_hint);
                }
            }
        });


    }
}
