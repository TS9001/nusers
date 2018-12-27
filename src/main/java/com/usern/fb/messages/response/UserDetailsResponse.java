package com.usern.fb.messages.response;

import com.usern.fb.entity.FBUserEntity;

public class UserDetailsResponse {

    private final long id=0;
    private final String content=" ";



    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UserDetailsResponse(FBUserEntity userDetails) {

    }
}
