package com.usern.fb.messages.request;

import javax.validation.constraints.NotEmpty;

public class UserDetailsRequest {
    @NotEmpty(message = "Valid Facebook user ID must be provided!")
    private String fbId;

    @NotEmpty(message = "Valid Facebook user access token must be provided!")
    private String accessToken;

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
