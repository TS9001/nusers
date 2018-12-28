package com.usern.fb.endpoints.mapper;

import com.restfb.types.*;
import com.usern.fb.entity.FBImageSource;
import com.usern.fb.entity.FBUserEntity;
import com.usern.fb.entity.FBUserPhotoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestFBToEntityMapperImpl implements RestFBToEntityMapper {

    @Override
    public FBUserEntity mapUserToFBUser(User user, List<Photo> photos) {
        FBUserEntity fbUserEntity = new FBUserEntity();
        fbUserEntity.setFacebookId(Long.valueOf(user.getId()));
        fbUserEntity.setName(user.getName());
        fbUserEntity.setGender(user.getGender());
        fbUserEntity.setProfilePictureUrl(Optional.ofNullable(user.getPicture()).orElse(new ProfilePictureSource()).getUrl());
        fbUserEntity.setPhotos(mapPhotoToFBUserPhoto(fbUserEntity,photos));
        return fbUserEntity;
    }

    private List<FBUserPhotoEntity> mapPhotoToFBUserPhoto(FBUserEntity fbUserEntity, List<Photo> photos) {
        return photos.stream().map(
                photo ->
                {
                    FBUserPhotoEntity entity = new FBUserPhotoEntity();
                    entity.setFbUser(fbUserEntity);
                    entity.setFacebookId(Long.valueOf(photo.getId()));
                    entity.setFacebookUrl(photo.getLink());
                    entity.setAlbum(Optional.ofNullable(photo.getAlbum()).orElse(new Album()).getName());
                    entity.setFileUrls(photo.getImages().stream().map(image -> new FBImageSource(image.getSource(), entity)).collect(Collectors.toList()));

                    if (photo.getReactions() != null && photo.getReactions().getData() != null && !photo.getReactions().getData().isEmpty()) {
                        entity.setReaction(photo.getReactions().getData().get(0).getType());
                    }

                    return entity;
                }
        ).collect(Collectors.toList());
    }
}
