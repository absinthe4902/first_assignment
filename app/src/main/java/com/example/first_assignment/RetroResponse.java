package com.example.first_assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroResponse {


    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("error_code")
    @Expose
    private  String error_code;
    @SerializedName("error_message")
    @Expose
    private  String error_message;
    @SerializedName("user_type")
    @Expose
    private  String user_type;
    @SerializedName("user_id")
    @Expose
    private  Integer user_id;
//    @SerializedName("devices")
//    @Expose
//    private  String devices;
    @SerializedName("session_key")
    @Expose
    private  String session_key;
    @SerializedName("start_mode")
    @Expose
    private  String start_mode;    // 자바 객체 직렬화를 위한 annotation @SerializedName


    public RetroResponse(String result, String error_code, String error_message, String user_type, Integer user_id,  String session_key, String start_mode) {

        this.result = result;
        this.error_code = error_code;
        this.error_message = error_message;
        this.user_type = user_type;
        this.user_id = user_id;
       // this.devices = devices;
        this.session_key = session_key;
        this.start_mode = start_mode;
    }

    public String getResult() { return result; }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError_code() { return error_code; }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() { return  error_message; }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getUser_type() { return user_type; }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_id() { return user_id; }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

//    public String getDevices() { return devices; }
//
//    public void setDevices(String devices) {
//        this.devices = devices;
//    }

    public String getSession_key() { return session_key; }

    public void setSession_key() {
        this.session_key = session_key;
    }

    public String getStart_mode() { return start_mode; }

    public void setStart_mode(String start_mode) {
        this.start_mode = start_mode;
    }
}
