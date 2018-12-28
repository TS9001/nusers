package com.usern.fb.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "FB_PHOTO")
@Table(name = "FB_PHOTO")
public class FBUserPhotoEntity {
    @Id
    @Column(name = "facebook_id")
    private Long facebookId;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "album")
    private String album;

    @Column(name = "reaction")
    private String reaction;

    @OneToMany(mappedBy = "fbUserPhoto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FBImageSource> fileUrls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facebook_user_id")
    private FBUserEntity fbUser;

    public FBUserPhotoEntity() {

    }

    public Long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public List<FBImageSource> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<FBImageSource> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public FBUserEntity getFbUser() {
        return fbUser;
    }

    public void setFbUser(FBUserEntity fbUser) {
        this.fbUser = fbUser;
    }
}
