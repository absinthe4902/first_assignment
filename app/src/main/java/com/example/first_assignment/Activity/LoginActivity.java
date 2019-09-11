package com.example.first_assignment.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.first_assignment.ApiUtils;
import com.example.first_assignment.GetDataService;
import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.R;
import com.example.first_assignment.HttpForm.RetroResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private String phone_no, password, app_device_id;
    private GetDataService myDataServie;
    private JsonRequest body;


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
        body = (JsonRequest) intent.getSerializableExtra("request_body"); //cast 연산자 없으면 오류난다, object를 넘겼어서 특별한 getExtra 사용


        if (body.checkValid(body.getPhone_no(), body.getPassword(), body.getApp_device_id())) { //다 private으로 선언을 해서 getter로 받아왔다. 필수 3개 파라메터가 유효한지 아닌지 확인하기 위해 따로 메소드 만듬
            sendPost(body);
        } else {
            Toast.makeText(this, R.string.parameter_warning, Toast.LENGTH_SHORT).show();
        }


    }


    public void sendPost(JsonRequest body) {

        myDataServie = ApiUtils.getAPIService();
        myDataServie.saveData(body).enqueue(new Callback<RetroResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<RetroResponse> call, @NonNull Response<RetroResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        //진짜 여기 완전 잘 동작한 변환이다.
                        tvSession.setText(String.format("%s %s", getResources().getString(R.string.text_session), response.body().getSession_key()));
                        tvUserId.setText(String.format("%s %d", getResources().getString(R.string.text_user_id), response.body().getUser_id()));
                        tvUserType.setText(String.format("%s %s", getResources().getString(R.string.text_user_type), response.body().getUser_type()));
                        tvStartMode.setText(String.format("%s %s", getResources().getString(R.string.text_start_mode), response.body().getStart_mode()));
                        tvDevices.setText(String.format(("%s %s"), getResources().getString(R.string.text_devices), response.body().getDevices()));

                        first.setVisibility(View.VISIBLE);
                    } else {
                        tvFailure.setText(R.string.responseBody_warning);
                        second.setVisibility(View.VISIBLE);
                    }

                } else {
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
