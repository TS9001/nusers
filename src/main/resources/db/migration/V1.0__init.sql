CREATE TABLE  FB_USER (
    facebook_id bigint NOT NULL,
    name varchar(255) NOT NULL,
    gender varchar(255) NOT NULL,
    profile_picture_url varchar(255) NOT NULL,
    PRIMARY KEY (facebook_id)
);

CREATE TABLE FB_PHOTO (
    facebook_id bigint NOT NULL,
    facebook_url varchar(255) NOT NULL,
    facebook_user_id bigint,
    album varchar(255),
    reaction varchar(255),
    PRIMARY KEY (facebook_id),
    FOREIGN KEY (facebook_user_id) REFERENCES FB_USER(facebook_id)
);

CREATE TABLE FB_IMAGE_SOURCE (
    id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    facebook_photo_id bigint,
    PRIMARY KEY (id),
	FOREIGN KEY (facebook_photo_id) REFERENCES FB_PHOTO(facebook_id)
);
