package com.example.first_assignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://tpi.dnx.kr/xouchcare/user/login";

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }



    // 참고했던 사이트 https://tpi.dnx.kr/xouchcare/user/login
    // URL 주소 https://tpi.dnx.kr/xouchcare/user/login
}
