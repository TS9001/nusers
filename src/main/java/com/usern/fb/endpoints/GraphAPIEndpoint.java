package com.usern.fb.endpoints;

import com.usern.fb.entity.FBUserEntity;

public interface GraphAPIEndpoint {
    FBUserEntity getFacebookUserWithDetails(String fbId, String accessToken);
}
