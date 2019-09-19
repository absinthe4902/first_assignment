package com.example.first_assignment;

import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.HttpForm.RetroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
retrofit는 http 클라이언트.
retrofit을 이용하기 위해서는3가지 클래스가 필수로 필요하다.
1. api에서 보내는 데이터 담을 객체 model class -> RetroResponse
2. http의 작동을 명시할 interface. 여기서는 post. -> GetDataService
3. retrofit을 만들 class ->REtrofitClientInstance
 */

public interface GetDataService {

    //@Headers({"Content-Type: application/json"})
    @POST("/xouchcare/user/login")
    Call<RetroResponse> saveData(@Body JsonRequest body);
    /*
    여기 Call이 바로 웹서버로 HTTP 리퀘스르르 보내고 답장을 받는 일을 한다.
    https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html : 여기가 정리가 참 잘 되어있다.
     */

    /*
    @body는 저기 뒤에 나오는 애가 리퀘스트 body라는 뜻이다.
    @Field 는 form-encoded data (ex html)을 파라미터로 보낼때 쓰고,(@FormUrlEncoded annotation 달아야한다)
    @Part는 multipart 데이터를 파라미터로 보낼때 쓴다 (@Multipart annotation 추가)

     */
}

