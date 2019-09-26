package com.example.first_assignment;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * first_assignment
 * Class: LoginActivity
 * Created by absinthe4902 on 2019-09-26.
 * <p>
 * Description: 싱글톤 스타일이 아닌 retrofit instance 만드는 class
 */

public class NoSingletonRetroClient {

    private static final String BASE_URL = "https://tpi.dnx.kr/";

    public Retrofit noGetClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient subClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(subClient)
                .build();
        /*
        나는 이렇게 return을 하는 형식이 과하다고 생각했는데 의외로 사람들이 많이 쓰고 있었다.
        생각해보면 return 하는 거 하나 때문에 객체를 하나 더 만드는게 비효율적이긴 하다.
         */
    }
}
