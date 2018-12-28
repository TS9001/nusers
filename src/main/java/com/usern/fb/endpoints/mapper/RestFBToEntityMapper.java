package com.usern.fb.endpoints.mapper;

import com.restfb.types.Photo;
import com.restfb.types.User;
import com.usern.fb.entity.FBUserEntity;

import java.util.List;

public interface RestFBToEntityMapper{
    FBUserEntity mapUserToFBUser(User user, List<Photo> photos);
}
