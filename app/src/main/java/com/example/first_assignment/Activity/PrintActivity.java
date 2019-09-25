package com.example.first_assignment.Activity;

/**
 * first_assignment
 * Class: PrintActivity
 * Created by absinthe4902 on 2019-09-24.
 * <p>
 * Description: LoginActivity에서 retrofit을 이용해서 받아온 response를 화면에 뿌려주기만 하는 클래스
 */

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


public class PrintActivity extends AppCompatActivity {

    /*맨처음에는 습관대로 여기다 global 변수로
    private GetDataService myDataService;
    private JsonRequest body
    선언을 했는데 warning이 떠버렸다. 딱! 어떤 메소드에서만 쓰이는 변수들은 어지간하면 글로벌 변수로 만들지 않고 로컬 변수로 만드는게 좋기 때문. 아니면 private을 지우던가.(글로벌 변수 어떤 모양으로도 꼬여서 문제 생길 확률 높다)

    global vs local
    데이터 영역 저장 vs 스텍저장 --> 여기서 데이터 영역이란 결국 RAM 영역이라고 함
     */

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
        tvFailure = findViewById(R.id.failure);  //rest api를 통해서 정보를 받아오고, Text 하나에 통으로 뿌리지 않고 text를 하나씩 설정해서 각각의 text에 정보를 set 해준다

        Intent intent = getIntent();
        RetroResponse retroResponse = (RetroResponse) intent.getSerializableExtra("response_body"); //cast 연산자 없으면 오류난다, object를 넘겼어서 특별한 getExtra 사용


        tvSession.setText(String.format("%s %s", getResources().getString(R.string.text_session), retroResponse.getSessionKey()));
        tvUserId.setText(String.format("%s %s", getResources().getString(R.string.text_user_id), String.valueOf(retroResponse.getUserId()))); //이런 소괄호 난리 정말 피하고 싶었는데 String.format에 %s %d를 나란히 쓰면 오류나서 숫자를 문자열로 바꾸고 사용.
        tvUserType.setText(String.format("%s %s", getResources().getString(R.string.text_user_type), retroResponse.getUserType()));
        tvStartMode.setText(String.format("%s %s", getResources().getString(R.string.text_start_mode), retroResponse.getStartMode()));
        tvDevices.setText(String.format(("%s %s"), getResources().getString(R.string.text_devices), retroResponse.getDevicesList()));


        first.setVisibility(View.VISIBLE);


    }


}
