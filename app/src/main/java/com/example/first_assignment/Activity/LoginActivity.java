package com.example.first_assignment.Activity;

/**
 * first_assignment
 * Class: LoginActivity
 * Created by absinthe4902 on 2019-09-24.
 * <p>
 * Description: Login을 하는 Activity, 여기서 api에 post request를 보내고 응답을 받게 된다.  여기서는 result가 0k인지 아닌지만 걸러서 로그인 시켜주고 자료에 대한 후 처리는 printActivity가 하도록 내비두자.
 */


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.first_assignment.NoSingletonRetroClient;
//import com.example.first_assignment.RetrofitClientInstance;
import com.example.first_assignment.GetDataService;
import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.HttpForm.RetroResponse;
import com.example.first_assignment.R;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity {


    EditText text1;
    EditText text2;
    Button btn;
    ProgressDialog progressDialog;


    String country_no;
    String app_device_id, app_lang, os_version, model_name;
    JsonRequest body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TagLoginActivity", String.format("%s", "처음 activity 시작"));

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
        /*
        phone_no와 password를 제외한 값들의 log를 찍어본다
         */
        body.requestDebug();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body.setPhone_no(String.valueOf(text1.getText()));
                body.setPassword(String.valueOf(text2.getText()));

                /*
                일부러 오류 만들때 계속 키보드 남아있는게 싫어서 코드로 내렸다.
                 */
                text1.onEditorAction(EditorInfo.IME_ACTION_DONE);
                text2.onEditorAction(EditorInfo.IME_ACTION_DONE);
                sendPost(body);

            }
        });


    }


    /**
     *
     * @param body request를 보낼 파라메터를 담고 있는 json 객체
     *             1. retrofit으로 request 보내고, 받기
     *             2. response의 errorMessage로 오류 검출 및 처리
     *             3. 성공하면 intent로 PrintActivity에 값 넘겨줌
     */
    public void sendPost(final JsonRequest body) {

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


       /* 싱글톤 형식을 사용
       GetDataService myDataService = RetrofitClientInstance.getClient().create(GetDataService.class);
       */

       NoSingletonRetroClient noSingletonRetroClient = new NoSingletonRetroClient();
       GetDataService myDataService = noSingletonRetroClient.noGetClient().create(GetDataService.class);

        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.progress_loding) );
        myDataService.getRetroResponse(body).enqueue(new Callback<RetroResponse>() {
            @Override
            public void onResponse(@NonNull  Call<RetroResponse> call, @NonNull Response<RetroResponse> response) {
                progressDialog.dismiss();
                Log.d("TagOnResponse", String.format("%s", "onResponse 호출됨"));
                // 검출해야할 조건 1. body가 null이 아닌지, 2. result가 ok인지. 일단 body가 null이 아니어야 result도 값이 들어갈 수 있는 거 아님?
                if(response.body()==null){
                    Log.d("TagonResponse", String.format("%s", "response일부에서 null이 검출됨"));
                    textReset();
                    Toast.makeText(getApplicationContext(), R.string.response_warning, Toast.LENGTH_SHORT).show();
                }else {
                    if(response.body().getErrorMessage().equals("no parameter")){
                        Log.d("TagonResponse", String.format("%s", "파라메터를 다 채우지 않았다"));
                        textReset();
                        Toast.makeText(getApplicationContext(), R.string.parameter_warning, Toast.LENGTH_SHORT).show();
                    }else if(response.body().getErrorMessage().equals("invalid user")){
                        Log.d("TagOnResponse", String.format("%s", "없는 사용자"));
                        textReset();
                        Toast.makeText(getApplicationContext(), R.string.login_warning, Toast.LENGTH_SHORT).show();
                    }else {
                        //성공한 케이스
                        response.body().responseDebug();

                        Intent intent = new Intent(getApplicationContext(), PrintActivity.class);
                        intent.putExtra("response_body", response.body());
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RetroResponse> call, @NonNull Throwable t) {
                Log.d("TagOnFailure", String.format("%s", "onFailure 호출됨"));
                progressDialog.dismiss();
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
        //커서 처음 edittext로 옮기기
        text1.requestFocus();
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

