package com.usern.fb.entity;


import javax.persistence.*;
import java.util.List;

@Entity(name = "FB_USER")
@Table(name = "FB_USER")
public class FBUserEntity {

    @Id
    @Column(name ="facebook_id")
    private Long facebookId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @OneToMany(mappedBy = "fbUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
