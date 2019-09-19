package com.example.first_assignment.HttpForm;


import java.io.Serializable;

/*
서버에 리퀘스트를 보낼 때 쓰는 body 객체
직렬화를 가능하게 하는 Serializable interface 를 사용했으나, 그 interface의 메소드는 사용하지 않았고,
MainActivity에서 putExtra로 오브젝트 형태를 넣어서 보낼때 필요하다고 해서 implement를 해뒀다.
그런데 더 좋은 퍼포먼스를 신경쓴다면 Parcelable interface를 쓴다고 한다. Serializable 보다 약간 복잡함

Serializable :https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
Parcelable : https://developer88.tistory.com/64
 */
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

    /*
    안 쓰는 getter setter의 method never used warning이 너무 많이 떠서 @SuppressWarnings("unused") annotation으로 warning을 억제했다
     */

    //처음에 필수 파라메터 쓸 때 사용한 생성자
    @SuppressWarnings("unused")
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
        this.model_name = model_name;

    }

    /*
    MainActivity에서 LoginActivity로 넘기는데 그때 api 호출하기 위한 필수 파라메터 3개가 잘 들어있는지
    확인하기 위해 메소드 따로 만듬
     */
    public boolean checkValid(String phone_no, String password, String app_device_id){ //object==null은 별로 안 좋다고 해서 따로 만듬. 그런데 과연 string==null은 안전한지..  제일 최악 String.equals(nulL) 이런것보단 string.equals("") 이게 양반
        return ((phone_no != null && !phone_no.isEmpty()) && (password != null && !password.isEmpty()) && (app_device_id != null && !app_device_id.isEmpty()));
    }

    @SuppressWarnings("unused")
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    @SuppressWarnings("unused")
    public String getPhone_no() {
        return phone_no;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("unused")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setApp_device_id(String app_device_id) {
        this.app_device_id = app_device_id;
    }

    public String getApp_device_id() {
        return app_device_id;
    }

    @SuppressWarnings("unused")
    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    @SuppressWarnings("unused")
    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    @SuppressWarnings("unused")
    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    @SuppressWarnings("unused")
    public void setApp_lang(String app_lang) {
        this.app_lang = app_lang;
    }

    @SuppressWarnings("unused")
    public void setApp_os_type(String app_os_type) {
        this.app_os_type = app_os_type;
    }

    @SuppressWarnings("unused")
    public String getModel_name() {
        return model_name;
    }

    @SuppressWarnings("unused")
    public String getOs_version() {
        return os_version;
    }

    @SuppressWarnings("unused")
    public String getApp_version() {
        return app_version;
    }

    @SuppressWarnings("unused")
    public String getApp_lang() {
        return app_lang;
    }

    @SuppressWarnings("unused")
    public void setCountry_no(String country_no) {
        this.country_no = country_no;
    }

    @SuppressWarnings("unused")
    public String getApp_os_type() {
        return app_os_type;
    }

    @SuppressWarnings("unused")
    public String getCountry_no() {
        return country_no;
    }
}
