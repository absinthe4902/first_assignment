package com.example.first_assignment;

import com.example.first_assignment.HttpForm.JsonRequest;
import com.example.first_assignment.HttpForm.RetroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {

    //@Headers({"Content-Type: application/json"})
    @POST("/xouchcare/user/login")
    Call<RetroResponse> saveData(@Body JsonRequest body);


}

