package com.example.first_assignment.HttpForm;

import android.util.Log;

/**
 * first_assignment
 * Class: JsonRequest
 * Created by absinthe4902 on 2019-09-25.
 * <p>
 * Description: 리퀘스트 body에 객채 하나만을 깔끔하게 넣기 위해 만든 class. 실 사용은 GetDataService와 LoginActivity에서 한다.
 */

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


    @SuppressWarnings("unused")
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

    private String getApp_device_id() {
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


    private String getModel_name() {
        return model_name;
    }

    @SuppressWarnings("unused")
    public String getOs_version() {
        return os_version;
    }


    private String getApp_version() {
        return app_version;
    }


    private String getApp_lang() {
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


    private String getCountry_no() {
        return country_no;
    }


    /**
     * RetroResponse와 마찬가지로 request 값을 찍어보기 위해 debug 코드 작성.
     * 값을 입력받는 phone_no와 password는 null이 일어날 확률이 있어서 값 있을 때만 찍어지는 조건의 log 걸어두었다.
     * 여기서만 사용하는 getter는 private으로 선언함
     */
    public void requestDebug() {
        Log.d("TagJsonRequest국가번호", getCountry_no());
        Log.d("TagJsonRequest디바이스 아이디", getApp_device_id());
        Log.d("Tag언어", getApp_lang());
        Log.d("Tag운영체제 버전", getApp_version());
        Log.d("Tag모델이름", getModel_name());
    }
}
