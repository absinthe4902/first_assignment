package com.example.first_assignment.HttpForm;

/**
 * first_assignment
 * Class: RetroResponse
 * Created by absinthe4902 on 2019-09-24.
 * <p>
 * Description: retrofit client에서 받아온 response를 담는 model class
 * 원래는 Serializable interface를 사용했는데 이 interface는 개발자가 쓰기 편한 만큼 app이 하는 일이 많아져서
 * 성능적인 면을 고려하여 Parcelable interface를 사용했다.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Parcelable: Parcel 객체를 사용하기 위해서 꼭 필요한 interface. 얘가 있어서 액티비티에서 다른 액티비티로 넘겨주고 싶은 객체를 Parcel 객체에 옮기고 전달을 할 수 있는 거다.
 * RetroResponse 객체를 그냥 넘겨주는 게 아니라 Parcel(소포) 객체에 싸서 넘겨준다는 개념이 중요!
 *
 * <해결한 의문점>
 *  1.Parcelable interface를 사용한 이상 @SerializedName을 쓰던 말던 writeParcel 구현해야 한다. 구현을 안 하면 값이 안 넘어감. @SerializedName은 그냥 Java에서 Json으로 바꿀 때 이름 맞춰주는 용도일 뿐인다.
 *  2. readList()할 때 classloder 자리에 null 쓰면 그냥 default classLoader을 사용한다. classloder은 해당 객체를 JVM에 올리는 역할을 한다. 때때로 객체가 메모리에 없고 네트워크를 통해서 오거나 그러는 때가 있는데 이때 classLoader을 사용한다고 함
 *
 */
public class RetroResponse implements Parcelable {


    private String result;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName("user_type")
    private  String userType;
    @SerializedName("user_id")
    private  Integer userId;
    /*
    여기에 새로운 list 객체를 생성하지 않으면 뒤에 직렬화를 해제하는 readList에서 null 오류가 발생한다.
    Parcel을 이요하고 싶다면 이 부분만 바꿔주면 되고, 생성자, getter 등의 부분은 따로 수정할 필요 없다.
     */
    @SerializedName("devices")
    private List devicesList = new ArrayList<>();
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
     *                  retrofit client에서 받은 값들 새로 객체 만들어서 넘기지 않아도 되기 때문에 생성자 사용할 일이 없음.
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


    /**
     *
     * @return result 값. RetroResponse의 responseDedug에서만 사용해서 public이 아닌 private으로 선언
     */
    private String getResult() {
        return result;
    }

    /**
     *
     * @return error message 값. getResult와 같은 이유로 private으로 선언
     */
    private String getErrorCode() {
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
       Log.d("TagRetroResponse결과", getResult());
       Log.d("TagRetroResponse에러코드", getErrorCode());
       Log.d("TagRetroResponse에러 메세지", getErrorMessage());
       Log.d("TagRetroResponse유저 타입", getUserType());
       Log.d("TagRetroResponse유저 아이디", String.format("%d", getUserId()));
       Log.d("TagRetroResponse세션키", getSessionKey());
       Log.d("TagRetroResponse시작모드: ", getStartMode());
    }



    /**
     * RetroResponse 객체의 데이터를 Parcel에 옮겨 적어야 하는데 그때 사용하는 메소드다. 보내는 쪽 액티비티가 사용하겠네.
     * @param dest .
     * @param i .
     */
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(result);
        dest.writeString(errorCode);
        dest.writeString(errorMessage);
        dest.writeString(userType);
        dest.writeInt(userId);
        dest.writeList(devicesList);
        dest.writeString(sessionKey);
        dest.writeString(startMode);
    }

    /**
     * Parcel에 쌓인 데이터를 풀어서 원래의 객체 RetroResponse에 옮겨담는 과정. 여기는 받는 쪽 액티비티가 사용할 것이다.
     * 소포를 푸는 과정이라서 꼭 writeParcel에 쓴 순서대로 read를 해줘야 한다.
     * @param src . 객체를 받았을 때 직렬화를 푸는 메소드
     */
    private  RetroResponse(Parcel src){


        result = src.readString();
        errorCode = src.readString();
        errorMessage = src.readString();
        userType = src.readString();
        userId = src.readInt();
        /*
        deviceList = src.readList(deviceList, null) 을 하면 deviceList에 null이 반환되어버린다.
        그래서 내가 계속 봤던 Type mismatch: cannot convert from void to List이 떠버리는 것이다.
        공식 문서를 보니까 readList가 void를 반환하는 거였다. 그래서 List인 deviceList에 void를 가져다 놓으니까~~
        그래서 src.readList(deviceList, null) 형태를 사용해야 한다.
         */
        src.readList(devicesList, null);
        sessionKey = src.readString();
        startMode = src.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * CREATOR가 필수라는데 무슨 애인가 봤더니 잘 포장된 Parcel 객체를 RetroResponse에게 던져주는 것 같다. 던져줬으니 나중에 read로 정보 빼오는 모양
     */
    public static final Creator<RetroResponse> CREATOR = new Creator<RetroResponse>() {
        @Override
        public RetroResponse createFromParcel(Parcel parcel) {
            return new RetroResponse(parcel);
        }

        @Override
        public RetroResponse[] newArray(int i) {
            return new RetroResponse[i];
        }
    };
}


