package com.usern.fb.controller;

import com.usern.fb.controller.mapper.UserControllerMapper;
import com.usern.fb.endpoints.GraphAPIEndpoint;
import com.usern.fb.entity.FBUserEntity;
import com.usern.fb.entity.FBUserPhotoEntity;
import com.usern.fb.messages.request.UserDetailsRequest;
import com.usern.fb.messages.response.UserDetailsResponse;
import com.usern.fb.messages.response.UserPhotoResponse;
import com.usern.fb.repository.FBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private GraphAPIEndpoint graphAPIE;

    @Autowired
    private FBUserRepository fbUserRepository;

    @Autowired
    private UserControllerMapper userControllerMapper;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public String upsertUser(@RequestBody UserDetailsRequest userRequest) {
        FBUserEntity user = graphAPIE.getFacebookUserWithDetails(userRequest.getFbId(), userRequest.getAccessToken());
        fbUserRepository.save(user);
        return String.valueOf(HttpStatus.OK);
    }

    @DeleteMapping("/users/{userFbId}")
    public String deleteUser(@PathVariable Long userFbId) {
        fbUserRepository.removeByFacebookId(userFbId)
                .orElseThrow(() -> new RuntimeException("User with facebookId " + userFbId + " was not found!"));
        return String.valueOf(HttpStatus.OK);
    }

    @GetMapping("/users/{userFbId}")
    public UserDetailsResponse getUserDetails(@PathVariable Long userFbId) {
        FBUserEntity userDetails = fbUserRepository.findByFacebookId(userFbId)
                .orElseThrow(() -> new RuntimeException("User with facebookId " + userFbId + " was not found!"));
        return userControllerMapper.mapFBUserEntityToUserDetailsResponse(userDetails);
    }

    @GetMapping("/users/{userFbId}/photos")
    public List<UserPhotoResponse> upsertUserPhotos(@PathVariable Long userFbId) {
        FBUserEntity userDetails = fbUserRepository.findByFacebookId(userFbId)
                .orElseThrow(() -> new RuntimeException("User with facebookId " + userFbId + " was not found!"));
        List<FBUserPhotoEntity> photos = userDetails.getPhotos();
        return userControllerMapper.mapFBPhotoEntityToUserDetailsResponses(photos);
    }


}


