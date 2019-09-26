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




/*
<싱글톤>
 내가 처음에 짠 코드의 형태가 싱글톤이었다. getClient를 선언한 이유는 싱글톤 클래스는 app이 시작될 때 딱 한 번 만 메모리를 할당하고 (싱글톤도 new 사용하는, 객체라서 heap 영역에 만들어짐)
 인스턴스를 새로 만들때 마다 사실은 이 최초의 객체를 돌려막는다.
 1. static을 써서 정적변수가 되었기 때문에 다른 클래스들과 공유가 쉬움
 2. 딱 한 번 만들어지기 때문에 메모리 낭비가 적다.
 3. "공통된 객체를 여러번 만드는 상황에서 많이 사용"--> 이 말 자체가 여러개의 rest api를 사용한다고 해도 여러번의 RetroClientInstance 를 쓰지 않아도 된다는 말을 하고 있네.
     -대신 이런 형태로 쓰고 싶다면 메소드 형태를 getClient(String url)로 해서 다른 url를 넣을 수 있게 만들어야 한다. 여기다 그냥 하나의 url 박아두면 재사용 못 하니까.

 단점: 그냥 막 쓰면 인스턴스가 너무 많은 일을 하고 서로 결합-의존이 생겨서 테스트가 막 꼬여버려서 적절하게 사용 못하겠으면 차라리 싱글톤 안 쓰는게 좋음, global한 사용이라서 전역변수가 예상 못한 문제 가져오는 것과 동일
  synchronized: 멀티쓰레드 환경에서는 제어권 문제 때문에 singleton이면서도 객체가 두 개, 세 개 생기는 일이 발생한다. 이걸 막으려고 쓰는게 synchronized 라는 개념임
                한 마디로 lock를 걸어버려서 객체를 생성하고 있는 경우 제어권이 넘어가지를 못하게 하는 방식인데 이게 무지막지하게 그냥 lock을 걸어서 성능 문제를 야기하기도 함. LazyHolder가 딱인데 차마 이해가 진짜 안 가서 차마 못 쓰겠음
 https://jeong-pro.tistory.com/86
 */
public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://tpi.dnx.kr/";


   /* 해결방법중 그나마 문제가 적게 있으면서 내가 이해도 할 수 있는 코드를 refactoring 하였다.
    static 초기화를 사용해서 class가 올라오는 시점에 인스턴스를 선언하기 때문에 singleton의 단일 객체를 어기지 않고 getClient에서는 synchronized를 사용하기 때문에 제어권이 넘어갈 문제도 없다.
    근데 객채를 쓸지 안 쓸지 모르는데 그냥 바로 만들어버려서 소소한 메모리 낭비 문제가 있다.*/


    static {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient subClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(subClient) // 1. 이 코드 무엇인지 2. OkHttpClient는 무엇인지 HttpLoggingInterceptor은 무엇인지
                .build();
    }

    public static synchronized Retrofit getClient() {
        return retrofit;
    }



}

