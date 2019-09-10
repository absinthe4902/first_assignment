package com.example.first_assignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://tpi.dnx.kr"; // base url 틀렸다. 정말 베이스지 이렇게 절절문 쓰면 안된다. endpoint에 가서 나머지 절절문 쓰게 될 것

    public static Retrofit getClient(String baseUrl) {
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }



    // 참고했던 사이트 https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
    // URL 주소 https://tpi.dnx.kr/xouchcare/user/login
    //request를 json으로 변환하는 과정 https://saviorj.tistory.com/19
}
