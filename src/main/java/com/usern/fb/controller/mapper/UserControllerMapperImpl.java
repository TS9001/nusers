package com.usern.fb.controller.mapper;

import com.usern.fb.entity.FBUserEntity;
import com.usern.fb.entity.FBUserPhotoEntity;
import com.usern.fb.messages.response.UserDetailsResponse;
import com.usern.fb.messages.response.UserPhotoResponse;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserControllerMapperImpl implements UserControllerMapper{

    @Override
    public UserDetailsResponse mapFBUserEntityToUserDetailsResponse(FBUserEntity userEntity) {
        UserDetailsResponse userDetailsResponse= new UserDetailsResponse();
        userDetailsResponse.setFacebookId(userEntity.getFacebookId());
        userDetailsResponse.setGender(userEntity.getGender());
        userDetailsResponse.setName(userEntity.getName());
        userDetailsResponse.setProfilePictureUrl(userEntity.getProfilePictureUrl());
        return userDetailsResponse;
    }

    @Override
    public List<UserPhotoResponse> mapFBPhotoEntityToUserDetailsResponses(List<FBUserPhotoEntity> photoEntities) {
        return photoEntities.stream().map( photoEntity -> {
            UserPhotoResponse userPhotoResponse = new UserPhotoResponse();
            userPhotoResponse.setAlbum(photoEntity.getAlbum());
            userPhotoResponse.setFacebookId(photoEntity.getFacebookId());
            userPhotoResponse.setFacebookUrl(photoEntity.getFacebookUrl());
            userPhotoResponse.setFileUrls(photoEntity.getFileUrls().stream().map(p -> p.getUrl()).collect(Collectors.toList()));
            userPhotoResponse.setReaction(photoEntity.getReaction());
        return userPhotoResponse;
        }).collect(Collectors.toList());
    }
}
