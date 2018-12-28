package com.usern.fb.controller.mapper;

import com.usern.fb.entity.FBUserEntity;
import com.usern.fb.entity.FBUserPhotoEntity;
import com.usern.fb.messages.response.UserDetailsResponse;
import com.usern.fb.messages.response.UserPhotoResponse;

import java.util.List;

public interface UserControllerMapper {

    UserDetailsResponse mapFBUserEntityToUserDetailsResponse(FBUserEntity userEntity);

    List<UserPhotoResponse> mapFBPhotoEntityToUserDetailsResponses(List<FBUserPhotoEntity> photoEntity);

}
