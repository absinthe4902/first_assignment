package com.example.first_assignment.HttpForm;


import java.io.Serializable;

public class JsonRequest implements Serializable {
    private String phone_no;
    private String password;
    private String app_device_id;

    //새로 응답 body 만들기 시작
    private String country_no;
    private String app_os_type;
    private String app_lang;
    private String app_version;
    private String os_version;
    private String model_name;

    JsonRequest() {}

    //처음에 api 실험용으로
    public  JsonRequest(String phone_no, String password, String app_device_id) {
        this.phone_no = phone_no;
        this.password = password;
        this.app_device_id = app_device_id;
    }

    public JsonRequest(String country_no, String phone_no, String password, String app_device_id, String app_os_type, String app_lang, String app_version, String os_version, String model_name) {
        this.country_no = country_no;
        this.phone_no = phone_no;
        this.password = password;
        this.app_device_id = app_device_id;
        this.app_os_type = app_os_type;
        this.app_lang = app_lang;
        this.app_version = app_version;
        this.os_version = os_version;

    }

    public boolean checkValid(String phone_no, String password, String app_device_id){ //object==null은 별로 안 좋다고 해서 따로 만듬. 그런데 과연 string==null은 안전한지..  제일 최악 String.equals(nulL) 이런것보단 string.equals("") 이게 양반
        return ((phone_no != null && !phone_no.isEmpty()) && (password != null && !password.isEmpty()) && (app_device_id != null && !app_device_id.isEmpty()));
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

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public void setApp_lang(String app_lang) {
        this.app_lang = app_lang;
    }

    public void setApp_os_type(String app_os_type) {
        this.app_os_type = app_os_type;
    }

    public String getModel_name() {
        return model_name;
    }

    public String getOs_version() {
        return os_version;
    }

    public String getApp_version() {
        return app_version;
    }

    public String getApp_lang() {
        return app_lang;
    }

    public void setCountry_no(String country_no) {
        this.country_no = country_no;
    }

    public String getApp_os_type() {
        return app_os_type;
    }

    public String getCountry_no() {
        return country_no;
    }
}
