package com.example.first_assignment;

/**
 * first_assignment
 * Class: RetrofitClientInstance
 * Created by absinthe4902 on 2019-09-25.
 * <p>
 * Description: retrofit의 객체를 build 하는 class. retrofit 객체를 http 행동 명시하는 interface에 써둠
 */

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


 class RetrofitClientInstance {

    //private static Retrofit retrofit;
    static Retrofit retrofit;



    @SuppressWarnings("SameParameterValue")
    static Retrofit getClient(String baseUrl) {

        /*
        okhttp를 쓰지 않으면 안되는 이유: RetroResponse에 responseDebug()를 만들어 냈다고 해서 log 다 찍어낸다고 했을 때 OK로 오면 모를까 fail이 오면
        몇몇 파라메터에 값이 들어오지 않은 response를 받게된다. 그럼 이제 null이 찍혀서 나오는거다. log 찍으려다가 다른 문제가 우수수 나옴. 많이 쓰는데는 이유가 있다.
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient subClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(subClient) // 1. 이 코드 무엇인지 2. OkHttpClient는 무엇인지 HttpLoggingInterceptor은 무엇인지
                    .build();
        }

        return retrofit;
    }



}
