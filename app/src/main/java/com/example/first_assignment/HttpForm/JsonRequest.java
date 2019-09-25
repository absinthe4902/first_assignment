package com.example.first_assignment.HttpForm;

/**
 * first_assignment
 * Class: JsonRequest
 * Created by absinthe4902 on 2019-09-25.
 * <p>
 * Description: 리퀘스트 body에 객채 하나만을 깔끔하게 넣기 위해 만든 class. 실 사용은 GetDataService와 LoginActivity에서 한다.
 */

import java.io.Serializable;


public class JsonRequest  {
    private String phone_no;
    private String password;
    private String app_device_id;

    private String country_no;
    private String app_os_type;
    private String app_lang;
    private String app_version;
    private String os_version;
    private String model_name;



    /**
     *
     * @param country_no 국가 번호 *선제공
     * @param app_device_id 디바이스 하드웨어 아이디 *선제공
     * @param app_os_type 앱 운영체제 타입 *선제공
     * @param app_lang 앱 언어 설정 * 선제공
     * @param app_version 앱 버전 *선제공
     * @param os_version 앱 운영체제 버전 *선제공
     * @param model_name 디바이스 모델 이름 * 선제공
     *
     *                   아이디인 phone_no와 비밀번호인 password는 틀리면 수정을 해야하는데 이것들은 틀릴 이유가 없다.
     *                   근데 아이디/비번 틀릴때마다 다시 구해오기 번거로우니까 그냥 id와 pwd는 setter로 설정하고 얘들은 고정
     */
    public JsonRequest(String country_no, String app_device_id, String app_os_type, String app_lang, String app_version, String os_version, String model_name) {
        this.country_no = country_no;
        this.app_device_id = app_device_id;
        this.app_os_type = app_os_type;
        this.app_lang = app_lang;
        this.app_version = app_version;
        this.os_version = os_version;
        this.model_name = model_name;
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


    /**
     *
     * @param phone_no 아이디
     * @param password 비밀번호
     * @param app_device_id 디바이스 하드웨어
     * @return request의 필수 구성 요소가 다 들어가면 true, 아니면 false. LoginActivity에서 우선적으로 판별 후, server에 request 보냄
     *
     * object 자체를 null 검출해도 쓸모 없다.
     * string==null은 string이 null인지 검출, isEmpty()는 string에 ""로 null은 아닌데 비어있는지 검출, String.equals(null) 쓰지마세요.
     */
    public boolean checkValid(String phone_no, String password, String app_device_id){
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
