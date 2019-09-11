package com.example.first_assignment.HttpForm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class RetroResponse {


    @SerializedName("user_type")
    @Expose
    private  String user_type;
    @SerializedName("user_id")
    @Expose
    private  Integer user_id;
    @SerializedName("devices")
    @Expose
    private List devices;
    @SerializedName("session_key")
    @Expose
    private  String session_key;
    @SerializedName("start_mode")
    @Expose
    private  String start_mode;    // 자바 객체 직렬화를 위한 annotation @SerializedName


    public RetroResponse( String user_type, Integer user_id, List devices, String session_key, String start_mode) {

        this.user_type = user_type;
        this.user_id = user_id;
        this.devices = devices;
        this.session_key = session_key;
        this.start_mode = start_mode;
    }


    public void setStart_mode(String start_mode) {
        this.start_mode = start_mode;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public void setDevices(List devices) {
        this.devices = devices;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getStart_mode() {
        return start_mode;
    }

    public String getSession_key() {
        return session_key;
    }

    public List getDevices() {
        return devices;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUser_type() {
        return user_type;
    }


}
