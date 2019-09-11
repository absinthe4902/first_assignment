package com.example.first_assignment.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.first_assignment.Activity.LoginActivity;
import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText text1;
    EditText text2;
    Button btn;

    String country_no;
    String app_device_id, app_lang, os_version, model_name;
    JsonRequest body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.log_id);
        text2 = findViewById(R.id.log_pw);
        btn = findViewById(R.id.log_button);

        app_device_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID); //안드로이드 디바이스 아이디 가져오는 함수
        app_lang = Locale.getDefault().getLanguage(); // 안드로이드 현재 설정 언어 가져오는 함수
        os_version = Build.VERSION.RELEASE; //안드로이드 버젼 가져오는 함수
        model_name = Build.MODEL;
        country_no = searchCountry();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id는 2로, 비밀번호는 1로 설정
                if(text1.getText().toString().equals("2") && text2.getText().toString().equals("1") ) {

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                    body = new JsonRequest(country_no, String.valueOf(text1.getText()), String.valueOf(text2.getText()), app_device_id, "A", app_lang, "1.0.12", os_version, model_name);
                    intent.putExtra("request_body", body);
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

    public String searchCountry() {
        String country_arr[][] = {{"KR", "82"}, {"US", "1"}, {"MX", "52"}, {"FR", "33"}, {"ES", "34"}}; //간단하게 만들려고 dnx에서 테스트를 진행하기로 예정한 국가들만을 넣었다
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String country_code =  tm.getSimCountryIso().toUpperCase();

        for(int i=0; i<country_arr.length; i++){
            if(country_code.equals(country_arr[i][0])){
                Log.d("국가번호 확인", country_arr[i][0]);
                Log.d("국가 코드 확인", country_code);
                return country_arr[i][0];
            }
        }

        return "none";
    }
}
