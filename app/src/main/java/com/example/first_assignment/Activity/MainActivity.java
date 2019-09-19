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


import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.R;

import java.util.Locale;


/*
MainActivity 는
1. 사용자에게 id/pw를 입력받고
2. 나머지 파라메터(디바이스 아이디, 설정 언어, 안드로이드 버젼, 디바이스 모델, 국가코드)를 구한 다음에
3. 리퀘스트를 보내기 위해 만들어놓은 JsonRequest객체를 선언해서 값을 다 넣고
4. LoginActivity에 그 객체를 전달해줌과 동시에 LoginActivity로 실행이 넘어가게 한다
 */
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


        /* 안드로이드 디바이스(하드웨어) 아이디 가져오는 함수
        노란 줄이 뜨는 이유는 android studio에서 바꿀 수 없는 고유한 하드웨어 아이디 (SSID, IME 등)을 사용하지 않기를 권장하기 때문에.
        광고id나 식별을 위한 api, instanceid를 쓰라고 제안하고 있으나 지금은 디바이스 아이디가 필요해서 그냥 씀.
        잠재적으로 시스템에 문제있는 건 아니고 개인정보 문제라고 함
        https://developer.android.com/training/articles/user-data-ids
         */
        app_device_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        app_lang = Locale.getDefault().getLanguage();
        os_version = Build.VERSION.RELEASE; //안드로이드 버젼 가져오는 함수
        model_name = Build.MODEL; //디바이스 모델 가져오는 함수
        country_no = searchCountry(); // 국가 코드를 찾기 위해 함수를 따로 만들었다.

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id는 2로, 비밀번호는 1로 설정
                if(text1.getText().toString().equals("2") && text2.getText().toString().equals("1") ) {

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                    body = new JsonRequest(country_no, String.valueOf(text1.getText()), String.valueOf(text2.getText()), app_device_id, "A", app_lang, "1.0.12", os_version, model_name);
                    intent.putExtra("request_body", body);  //인자 하나씩 넘겨주면 putExtra를 과도하게 해서 코드가 너무 지저분해보여 jsonRequest의 객체를 생성하여 거기다 담아서 넣어주었다.
                    startActivity(intent);                              //edittext에서 문자열 가져오는 방법으로 toString()을 많이 썼었는데 toString()보다 String.valueOf() 가 더 좋다고 해서 String.valueOf()를 써봤다
                                                                        // 더 좋은 이유: 파라메터가 null이 들어가면 String.valueOf()는 null을 반환해주는데 toString()은 null exception이 나와버린다.

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

    /*함수를 간단하게 만들려고 dnx에서 테스트를 진행하기로 예정한 국가들만을 넣었다
    원래는 모든 국가가 써있는 xml 파일을 추가하고, 그걸 이용해서 찾아내는 형식을 많이 쓴다.
     https://stackoverflow.com/questions/31578958/how-to-get-country-codecalling-code-in-android/48873912
     */
    public String searchCountry() {
        String[][] country_arr = new String[][]{{"KR", "82"}, {"US", "1"}, {"MX", "52"}, {"FR", "33"}, {"ES", "34"}};
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String country_code =  tm.getSimCountryIso().toUpperCase();  //디바이스에서 사용중인 네트워크 통신망의 유심을 이용하여 현재 서비스 되고 있는 국가의 iso 코드를 가져온다.

        /*
        가독성의 이유로 안드로이드 스튜디오는 for보다 foreach 사용을 추천한다고 하는데,
        1차원이면 하겠는데 2차원 배열이라서 warning을 무시하고 for을 사용했다.
        https://stackoverflow.com/questions/32548820/why-does-android-studio-want-me-to-use-for-each-instead-of-for-loop
        */
        for(int i=0; i<country_arr.length; i++){
            if(country_code.equals(country_arr[i][0])){
                Log.d("국가번호 확인", country_arr[i][0]);
                Log.d("국가 코드 확인", country_code);
                return country_arr[i][1];
            }
        }

        return "none";
    }
}
