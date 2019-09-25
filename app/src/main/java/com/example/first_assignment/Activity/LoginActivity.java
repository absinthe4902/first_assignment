package com.example.first_assignment.Activity;

/**
 * first_assignment
 * Class: LoginActivity
 * Created by absinthe4902 on 2019-09-24.
 * <p>
 * Description: Login을 하는 Activity, 여기서 api에 post request를 보내고 응답을 받게 된다.  여기서는 result가 0k인지 아닌지만 걸러서 로그인 시켜주고 자료에 대한 후 처리는 printActivity가 하도록 내비두자.
 */


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


import com.example.first_assignment.ApiUtils;
import com.example.first_assignment.GetDataService;
import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.HttpForm.RetroResponse;
import com.example.first_assignment.R;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

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


      /*
      device의 고유 hardware id를 가져오는 것에 대한 privacy warning
       */
        app_device_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        app_lang = Locale.getDefault().getLanguage();
        os_version = Build.VERSION.RELEASE;
        model_name = Build.MODEL;
        country_no = searchCountry();

        body = new JsonRequest(country_no, app_device_id, "A", app_lang, "1.0.12", os_version, model_name);

        Log.d("Tag국가번호", country_no);
        Log.d("Tag디바이스 아이디", app_device_id);
        Log.d("Tag언어", app_lang);
        Log.d("Tag운영체제 버전", os_version);
        Log.d("Tag모델이름", model_name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body.setPhone_no(String.valueOf(text1.getText()));
                body.setPassword(String.valueOf(text2.getText()));
/*
                if(body.checkValid(body.getCountry_no(), body.getPhone_no(), body.getPassword())){
                   sendPost(body);
                }else {
                    Toast.makeText(getApplicationContext(), R.string.parameter_warning, Toast.LENGTH_SHORT).show();
                    textReset();
                }*/

                sendPost(body);

            }
        });


    }


    /**
     *
     * @param body
     */
    public void sendPost(final JsonRequest body) {

        GetDataService myDataService = ApiUtils.getAPIService();

        /*
        result로 오류 잡으면 안된다. response가 오류가 나도 status를 200으로 찍어보내는 것은 알고 있는 사항이었는데
        result가 OK 아니면 FAIL이라서 제대로 검출을 할 수 없다.
        더 상세하게 나와있는 errorCode나 errorMessage로 검출을 해야한다.

        이 상황에서 내가 낼 수 있는 오류
        1. 파라메터 오류 (errorCode: SWER001 errorMessage: no parameter
        2. 없는 유저 정보 입력, 오타 등등 (errorCode: NO USER errorMessage: invalid user
        3. 네트워크 끊은 상태로 보내기: 이럼 http request가 아예 안 보내져서 response도 안 오고 retrofit client에서도 onFailure로 하드웨어 오류 취급한다.
        (4) body가 null이 오는 경우는... 뭔가 잘못은 되었는데 client 오류는 아니지 않나싶었는데 json안에 json이 있는 경우 뭘 잘못하면 날 수 있는 오류라고 한다.
         */

        myDataService.getRetroResponse(body).enqueue(new Callback<RetroResponse>() {
            @Override
            public void onResponse(Call<RetroResponse> call, Response<RetroResponse> response) {
                // 검출해야할 조건 1. body가 null이 아닌지, 2. result가 ok인지. 일단 body가 null이 아니어야 result도 값이 들어갈 수 있는 거 아님?
                if(response.body()==null){
                    //몸뚱이가 null 와버린 것에 대한 오류 처리
                    Toast.makeText(getApplicationContext(), R.string.response_warning, Toast.LENGTH_SHORT).show();
                    textReset();
                }else {
                    if(response.body().getResult().equals("OK")){
                        response.body().responseDebug(); //로그 한 번 찍어본다. null나오면 안되니까 다 주는 쪽에서만 찍어보는 졸렬함

                        Intent intent = new Intent(getApplicationContext(), PrintActivity.class);
                        /*
                        RetroResponse 객체를 새로 만들어서 보내지 않고 그냥 받은대로 보내면 문제가 일어나지 않을까 생각을 했는데 찍어보니까 문제가 없었다.
                        원래는 RetroResponse 객체를 만들어 보내야지! 생각하고 화면에 찍어낼 필요 없는 result, errorCode, errorMessage를 넣지않은 생성자
                        하나 더 만들어서 객체 만들려고 했는데 안해도 되는 짓이었음.
                         */
                        intent.putExtra("response_body", response.body());
                        startActivity(intent);
                    }else {
                        //내가 발생시킬 수 있는
                        Toast.makeText(getApplicationContext(), R.string.login_warning, Toast.LENGTH_SHORT).show();
                        textReset();
                    }
                }
            }

            @Override
            public void onFailure(Call<RetroResponse> call, Throwable t) {
                /*
                네트워크 꺼버리면 post 형식이 써지기는 한다. 근데 서버로 보내지는게 아니다.
                okhttp로 검출한 결과 HTTP FAILED: java.net.UnknownHostException: Unable to resolve host "tpi.dnx.kr": No address associated with hostname 가 떠버림.
                 */
                    Toast.makeText(getApplicationContext(), R.string.hardware_error, Toast.LENGTH_SHORT).show();
                    textReset();
            }
        });
    }


    /**
     * 오류 발생할 때마다 command 다 치기가 너무 귀찮아서 함수로 따로 뺴서 만들었다.
     */
    public void textReset(){
        text1.setText("");
        text2.setText("");
        text1.setHint(R.string.id_hint);
        text2.setHint(R.string.pw_hint);
    }


    /**
     *
     * @return dnx에서 서비스하는 국가들의 국가번호를 찾아서 반환해준다.매핑값은 네트워크 통신망의 유심에서 제공해주는 서비스 국가 iso 코드
     * upgrade 형식 : https://stackoverflow.com/questions/31578958/how-to-get-country-codecalling-code-in-android/48873912 시간 있으면 이 형식으로 바꿔서 달기
     */
    public String searchCountry() {
        String[][] country_arr = new String[][]{{"KR", "82"}, {"US", "1"}, {"MX", "52"}, {"FR", "33"}, {"ES", "34"}};
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String country_code =  tm.getSimCountryIso().toUpperCase();

        /*
        for-each 사용하라고 해서 설계적 이유로 warning 무시
         */
        for(int i=0; i<country_arr.length; i++){
            if(country_code.equals(country_arr[i][0])){
                return country_arr[i][1];
            }
        }

        return "none";
    }
}

