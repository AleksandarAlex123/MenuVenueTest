package com.aleksandar.menutest.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginAPiResponse {
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @SerializedName("access_token")
    private String accessToken;
}
