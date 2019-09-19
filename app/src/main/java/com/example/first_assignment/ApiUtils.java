package com.example.first_assignment;

/*
여기서 RetroClientInstance에서 retrofit 만드는 메소드를 이용해서 retrofit 객체를 만들고 retrofit 객체의 create 메소드를 사용해서
GetDataService를 구현.
create() 메소드 설명으로 pass your interface to create(java.lang.Class<T>) to generate an implementation라고 되어있다.
https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html

원래 이 과정은 그냥 api 호출하기 전에 interface 객체 만들어서 retrofit.create(interface) 이렇게 쓰는데
나는 처음 배우니까 세분화 하는 것이 좋겠다고 생각해서 또 굳이 클래스를 뺌. --> 보통의 형식은 아니니 다음부터는 이렇게 하지 말자.
 */
public class ApiUtils {

    private ApiUtils() {}
    private  static final String BASE_URL = "https://tpi.dnx.kr/";
    //역시나 private 안 쓰고 그냥 선언했더니 warning에 private을 쓰라고 떠버렸다.
    /*
    BASE_URL에 static을 달아야했던 로지컬한 이유
    1. LoginActivity에서 getAPIService()를 static 형식의 메소드 방식으로 호출함.
    그래서 getAPiService는 static 메소드가 되어야했고, 그 안에 들어가는 BASE_URL도 static 변수가 되었어야 했다.
     */

    public static GetDataService getAPIService() {
        return RetrofitClientInstance.getClient(BASE_URL).create(GetDataService.class);
    }
}
