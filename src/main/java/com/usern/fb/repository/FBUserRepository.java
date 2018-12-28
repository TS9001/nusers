package com.usern.fb.repository;

import com.usern.fb.entity.FBUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FBUserRepository extends JpaRepository<FBUserEntity, Long> {

    Optional<FBUserEntity> findByFacebookId(Long facebookId);

    Optional <FBUserEntity> removeByFacebookId(Long facebookId);

}
