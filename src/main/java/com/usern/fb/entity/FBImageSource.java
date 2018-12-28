package com.usern.fb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "FB_IMAGE_SOURCE")
@Table(name = "FB_IMAGE_SOURCE")
public class FBImageSource {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facebook_photo_id")
    private FBUserPhotoEntity fbUserPhoto;

    public FBImageSource() {
    }

    public FBImageSource(String url, FBUserPhotoEntity fbUserPhoto) {
        this.url = url;
        this.fbUserPhoto = fbUserPhoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FBUserPhotoEntity getFbUserPhoto() {
        return fbUserPhoto;
    }

    public void setFbUserPhoto(FBUserPhotoEntity fbUserPhoto) {
        this.fbUserPhoto = fbUserPhoto;
    }

}
