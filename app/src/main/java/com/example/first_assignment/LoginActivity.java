package com.example.first_assignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private String phone_no, password, app_device_id;
    private GetDataService myDataServie;
    private jsonRequest body;


    LinearLayout first, second;
    TextView tvSession, tvUserId, tvUserType, tvStartMode, tvDevices, tvFailure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_login);

        first = findViewById(R.id.first_layout);
        second = findViewById(R.id.second_layout);

        tvSession = findViewById(R.id.tv_session_key);
        tvUserId = findViewById(R.id.tv_user_id);
        tvUserType = findViewById(R.id.tv_user_type);
        tvStartMode = findViewById(R.id.tv_start_mode);
        tvDevices = findViewById(R.id.tv_devices);
        tvFailure = findViewById(R.id.failure);

        Intent intent = getIntent();
        phone_no = intent.getStringExtra("id_value");
        password= intent.getStringExtra("pw_value");
        app_device_id = intent.getStringExtra("android_id_value");



        if(!TextUtils.isEmpty(phone_no) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(app_device_id)){
            //값이 정상으로 넘어온 상태
            body = new jsonRequest(phone_no, password, app_device_id);
            sendPost(body);
        }else {
            Toast.makeText(this, R.string.parameter_warning, Toast.LENGTH_SHORT).show();
        }


    }


    public void sendPost(jsonRequest body) {
        System.out.println("호출");

        myDataServie = ApiUtils.getAPIService();
        myDataServie.saveData(body).enqueue(new Callback<RetroResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<RetroResponse> call,@NonNull Response<RetroResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null) {
                        //진짜 여기 완전 잘 동작한 변환이다.
                        String result = response.body().getResult();
                        tvSession.setText(R.string.text_session+ response.body().getSession_key());
                        tvUserId.setText(R.string.text_user_id+response.body().getUser_id().toString());
                        tvUserType.setText(R.string.text_user_type+response.body().getUser_type());
                        tvStartMode.setText(R.string.text_start_mode+response.body().getStart_mode());
                        //devices에 대한 부분은 아직 남겨두었다.
                        first.setVisibility(View.VISIBLE);
                    }else {
                        tvFailure.setText(R.string.responseBody_warning);
                        second.setVisibility(View.VISIBLE);
                    }

                }else {
                    //잘 안나온 부분에 대해서 말을 해야하는데
                    tvFailure.setText(R.string.response_warning);
                    second.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<RetroResponse> call, Throwable t) {
                //여긴 아마 통신이 완전 망했을 때 뭘 쓰는 곳, 아래의 2줄은 오류를 트랙킹하기 위해서 사용한다
                System.out.println("onFailure" + call);
                t.printStackTrace();

                tvFailure.setText(R.string.total_failure);
                second.setVisibility(View.VISIBLE);



            }
        });
    }
}
