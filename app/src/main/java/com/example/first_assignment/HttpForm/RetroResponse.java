package com.example.first_assignment.HttpForm;

/**
 * first_assignment
 * Class: RetroResponse
 * Created by absinthe4902 on 2019-09-24.
 * <p>
 * Description: retrofit client에서 받아온 response를 담는 model class
 */

import android.util.Log;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.util.List;

public class RetroResponse implements Serializable {


    private String result;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName("user_type")
    private  String userType;
    @SerializedName("user_id")
    private  Integer userId;
    @SerializedName("devices")
    private List devicesList;
    @SerializedName("session_key")
    private  String sessionKey;
    @SerializedName("start_mode")
    private  String startMode;


    @SuppressWarnings("unused")
    public  RetroResponse() {}


    /**
     *
     * @param result 응답 결과, 추후에 isSuccessful 대신에 사용한다 "OK", "FAIL"
     * @param errorCode 오류에대한 상세한 코드, "NO USER", "SWER001" 등
     * @param errorMessage 단순 오류 메세지 + profiler로 봤을 때는 글자가 깨졌는데 log로 찍어보면 또 아무 이상 없다.
     * @param userType 사용자 타입. O:착용자, G: 보호자
     * @param userId 유저 아이디
     * @param devicesList 페어링한 디바이스 목록
     * @param sessionKey 세션 키
     * @param startMode 보호자 메인 모드, U: 사용자 관리 메인, C: 캘린더 메인 --> 착용자 모드일때 나오지 않는다.
     */
    @SuppressWarnings("unused")
    public RetroResponse( String result, String errorCode, String errorMessage, String userType, Integer userId, List devicesList, String sessionKey, String startMode) {

        this.result = result;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userType = userType;
        this.userId = userId;
        this.devicesList = devicesList;
        this.sessionKey = sessionKey;
        this.startMode = startMode;
    }



    public String getResult() {
        return result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUserType() {
        return userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public List getDevicesList() { return devicesList; }

    public String getSessionKey() { return sessionKey; }

    public String getStartMode() {
        return startMode;
    }

    /**
     * toString 대신에 디버그용으로 짠 메소드. okhttp 라이브러리로 응답을 다 보기는 하는데 log 더 써보려고 만들었다.
     * this.getResult 와 그냥 getResult
     * log 제목, 메세지 순서이다
     */
    public void responseDebug() {
       Log.d("Tag결과", getResult());
       Log.d("Tag에러코드", getErrorCode());
       Log.d("Tag에러 메세지", getErrorMessage());
       Log.d("Tag유저 타입", getUserType());
       Log.d("Tag유저 아이디", String.format("%d", getUserId()));
       Log.d("Tag세션키", getSessionKey());
       Log.d("Tag시작모드: ", getStartMode());
    }
}
