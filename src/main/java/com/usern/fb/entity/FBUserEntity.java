package com.usern.fb.entity;


import com.usern.fb.endpoints.mapper.RestFBToEntityMapper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fbuser")
public class FBUserEntity {

    @Id
    @Column(name ="facebook_id")
    private Long facebookId;

    @Column(name = "name")
    String name;

    @Column(name = "gender")
    String gender;

    @Column(name = "profile_picture_url")
    String profilePictureUrl;

    @OneToMany(mappedBy = "facebookId", fetch = FetchType.LAZY)
    private List<FBUserPhotoEntity> photos;

    public Long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public List<FBUserPhotoEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(List<FBUserPhotoEntity> photos) {
        this.photos = photos;
    }


}
