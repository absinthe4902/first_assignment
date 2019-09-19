package com.example.first_assignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
이 java class가 retrofit을 만드는 retrofit builder가 있는 클래스인데,
대부분의 retrofit 문서에서는 작동을 명시할 interface에 retrofit 객체를 선언하고, builder로 그 객체를 빌드하였으나,
나는 일단 처음 써보는거니까 페이지 자세하게 분할하는게 좋을 것 같아서 class를 따로 뺌
 */
 class RetrofitClientInstance {
    //class 접근제한자는 public이랑 default 밖에 없는데 자꾸 변수 접근제안자랑 헷갈려서 보안 높이려고 class에 private 쓰다가 빨간경고 먹음

    private static Retrofit retrofit;
    /*
    여기서도 static의 retrofit과 getClient() 메소드가 쓰인 이유는 APIUtils에서 static 형식의 메소드 호출을 하고 있어서,
     */

    @SuppressWarnings("SameParameterValue") //원래 value가 오는 곳이랑 (APiUtils) 똑같이 해라~ 워닝떠서 그냥 똑같은 value라고 결함인지 annotation 으로 쇼부를 봤다.
    static Retrofit getClient(String baseUrl) {
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())  //서버에서 받아오는 json데이터를 java 객체 형태로 바꿔주는 gson 변환기. 반대의 일을 하는 것도 가능하다.
                    .build();
        }

        return retrofit;
    }



    // 참고했던 사이트 https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
    // URL 주소 https://tpi.dnx.kr/xouchcare/user/login
    //request를 json으로 변환하는 과정 https://saviorj.tistory.com/19
}
