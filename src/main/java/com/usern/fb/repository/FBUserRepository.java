package com.usern.fb.repository;

import com.usern.fb.entity.FBUserEntity;
import com.usern.fb.entity.FBUserPhotoEntity;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FBUserRepository extends Repository<FBUserEntity, Long> {

    Optional<FBUserEntity> save(FBUserEntity s);

    Optional<FBUserEntity> findByFacebookId(String facebookId);

    @Transactional
    Optional<List<FBUserPhotoEntity>> findPhotosByFacebookId(String facebookId);

    @Transactional
    Optional <FBUserEntity> removeByFacebookId(String facebookId);

}
