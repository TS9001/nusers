package com.usern.fb.entity;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fb_photo")
public class FBUserPhotoEntity {
    @Id
    @Column(name ="facebook_id")
    Long facebookId;

    @Column(name ="facebook_url")
    String facebookUrl;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    List<FBImageSource> fileUrls;

    @Column(name ="album")
    String album;

    @Column(name ="reaction")
    String reaction;

    public FBUserPhotoEntity(){

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

    public List<FBImageSource>getFileUrls() {
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
}
