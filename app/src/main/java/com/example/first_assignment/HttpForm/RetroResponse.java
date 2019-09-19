package com.example.first_assignment.HttpForm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class RetroResponse {

    /*
    @SerializedName : Gson이 json 키를 매핑하기 위해서 필요하다. 이런경우 원래는 변수 이름에 _ 를 넣지 않는게 좋다고 한다...
    @Expose : 이 필드가 json 직렬-비직렬화에 노출되어야 함. 얘는 옵션이다. 얘를 @Expose(serialize = false) 해두면 @SerializedName가 통하지 않는다.

    직렬화: 객체를 특정한 형태로 바꾼다 json이나 xml 등등 (데이터 전송을 위한 형태변화 정도인듯)
     */

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


    @SuppressWarnings("unused")
    public void setStart_mode(String start_mode) {
        this.start_mode = start_mode;
    }

    @SuppressWarnings("unused")
    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    @SuppressWarnings("unused")
    public void setDevices(List devices) {
        this.devices = devices;
    }

    @SuppressWarnings("unused")
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @SuppressWarnings("unused")
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
