package com.example.first_assignment;

public class ApiUtils {

    private ApiUtils() {}
    public static final String BASE_URL = "https://tpi.dnx.kr/";

    public static GetDataService getAPIService() {
        return RetrofitClientInstance.getClient(BASE_URL).create(GetDataService.class);
    }
}
