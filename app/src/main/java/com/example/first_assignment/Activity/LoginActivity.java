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
        JsonRequest body = (JsonRequest) intent.getSerializableExtra("request_body"); //cast 연산자 없으면 오류난다, object를 넘겼어서 특별한 getExtra 사용


        if (body.checkValid(body.getPhone_no(), body.getPassword(), body.getApp_device_id())) { //다 private으로 선언을 해서 getter로 받아왔다. 필수 3개 파라메터가 유효한지 아닌지 확인하기 위해 따로 메소드 만듬
            sendPost(body);
        } else {
            Toast.makeText(this, R.string.parameter_warning, Toast.LENGTH_SHORT).show();
        }


    }


    public void sendPost(JsonRequest body) {

        /*ApiUtils.getAPIService() 이런식으로 객체를 선언하지 않고도 메소드를 호출하여 쓰는게 static 방식이다.
        그런데 ApiUtils에서 getAPIService()메소드에서 static을 빼니까 문제가 생기는것, 그럼 getAPIService는 일반 메소드라서
        객체를 만들어서 호출을 해야하는 메소드가 됨 **APiUtils.java 참고!**
        */
        GetDataService myDataServie = ApiUtils.getAPIService();

        /*
        retrofit은 비동기, 동기로 작동하는게 다 가능하다.
        그런데 api에서 정보를 받아오는걸 비동기로 background에서 작업하는게 좋을 것 같아서 enqueue()를 사용하였다.
        동기로 사용하고 싶으면 execute()를 사용하면 된다.
         */
        myDataServie.saveData(body).enqueue(new Callback<RetroResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<RetroResponse> call, @NonNull Response<RetroResponse> response) {
                    if (response.isSuccessful()) {
                        //진짜 여기 완전 잘 동작한 변환이다. response의 status code가 200번대 일때 참이된다.
                        //onResponse가 http response를 받아오다보니 통신이 다 잘 되었나보다 라고 생각핳 수 있는데
                        //404나 500이 와도 onResponse가 되어버려서 isSuccessful 메소드로 한 번 걸러준다.


                        assert response.body() != null; //assert는 에러검출하는 디버깅용 코드라는데 null이 아닐때만 참으로 동작한다고 써놔서 뒤에 getSession_key()아런 애들이 null을 반환할 오류를 피할 수 있었다 (널값 자체를 거짓이라 보고 허용을 안 해서)
                                                        //디버깅 용이라 뭔 효력이 있는 것은 아니고 오류나면 이제 어떤 수를 내야한다는 뜻
                        tvSession.setText(String.format("%s %s", getResources().getString(R.string.text_session), response.body().getSession_key()));
                        tvUserId.setText(String.format("%s %s", getResources().getString(R.string.text_user_id), String.valueOf(response.body().getUser_id()))); //이런 소괄호 난리 정말 피하고 싶었는데 String.format에 %s %d를 나란히 쓰면 오류나서 숫자를 문자열로 바꾸고 사용.
                        tvUserType.setText(String.format("%s %s", getResources().getString(R.string.text_user_type), response.body().getUser_type()));
                        tvStartMode.setText(String.format("%s %s", getResources().getString(R.string.text_start_mode), response.body().getStart_mode()));
                        tvDevices.setText(String.format(("%s %s"), getResources().getString(R.string.text_devices), response.body().getDevices()));

                        first.setVisibility(View.VISIBLE);
                    } else {
                    //잘 안나온 부분에 대해서 말을 해야하는데
                    tvFailure.setText(R.string.response_warning);
                    second.setVisibility(View.VISIBLE);
                }
            }

            @Override
            /*
            @NonNull이 덕지덕지 붙어있는 이유 : 이것은 android support annotation이라는 라이브러리의 기능인데
            나중에 java 문법으로만 탐지하기 어려운 결함을탐지하는 걸 도와준다고 한다.
            NonNull은 절대 null값을 반환하지 못한다고 명시를 해 두는 건데 나중에 여기가 null이 반환되면 결함 바로 찾아 낼 것이다.
             */
            public void onFailure(@NonNull Call<RetroResponse> call, @NonNull Throwable t) {
                //여긴 아마 통신이 완전 망했을 때 뭘 쓰는 곳, 아래의 2줄은 오류를 트랙킹하기 위해서 사용한다
                System.out.println("onFailure" + call);
                t.printStackTrace();

                tvFailure.setText(R.string.total_failure);
                second.setVisibility(View.VISIBLE);


            }
        });
    }
}
