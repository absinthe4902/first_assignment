package com.example.first_assignment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetDataService {

    //@Headers({"Content-Type: application/json"})
    @POST("/xouchcare/user/login")
    Call<RetroResponse> saveData(@Body jsonRequest body);


}


class jsonRequest{
    String phone_no;
    String password;
    String app_device_id;

    jsonRequest() {}

    jsonRequest(String phone_no, String password, String app_device_id) {
        this.phone_no = phone_no;
        this.password = password;
        this.app_device_id = app_device_id;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setApp_device_id(String app_device_id) {
        this.app_device_id = app_device_id;
    }

    public String getApp_device_id() {
        return app_device_id;
    }
}
