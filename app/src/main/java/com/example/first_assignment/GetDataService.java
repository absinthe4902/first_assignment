package com.example.first_assignment;

/**
 * first_assignment
 * Class: GetDataService
 * Created by absinthe4902 on 2019-09-24.
 * <p>
 * Description: retofit http 클라이언트의 작동을 명시할 interface
 */

import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.HttpForm.RetroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface GetDataService {


    @POST("/xouchcare/user/login")
    Call<RetroResponse> getRetroResponse(@Body JsonRequest body);

}

