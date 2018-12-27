package com.usern.fb.entity;

import javax.persistence.*;

@Entity
@Table(name = "fbImageSource")
public class FBImageSource {
    @Column(name = "id")
    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "url")
    String url;

    public FBImageSource(String url) {
        this.url = url;
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
}
