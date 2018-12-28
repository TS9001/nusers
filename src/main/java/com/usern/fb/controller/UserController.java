package com.usern.fb.controller;

import com.restfb.exception.FacebookGraphException;
import com.restfb.exception.FacebookOAuthException;
import com.usern.fb.controller.mapper.UserControllerMapper;
import com.usern.fb.endpoints.GraphAPIEndpoint;
import com.usern.fb.entity.FBUserEntity;
import com.usern.fb.entity.FBUserPhotoEntity;
import com.usern.fb.messages.request.UserDetailsRequest;
import com.usern.fb.messages.response.UserDetailsResponse;
import com.usern.fb.messages.response.UserErrorResponse;
import com.usern.fb.messages.response.UserPhotoResponse;
import com.usern.fb.repository.FBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public String upsertUser(@Valid @RequestBody UserDetailsRequest userRequest) {
            FBUserEntity user = graphAPIE.getFacebookUserWithDetails(userRequest.getFbId(), userRequest.getAccessToken());
            fbUserRepository.save(user);
            return String.valueOf(HttpStatus.OK);
    }

    @DeleteMapping("/users/{userFbId}")
    public String deleteUser(@PathVariable Long userFbId) {
        FBUserEntity userDetails = fbUserRepository.findByFacebookId(userFbId)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User with facebookId " + userFbId + " was not found!"));
        fbUserRepository.delete(userDetails);
        return String.valueOf(HttpStatus.OK);
    }

    @GetMapping("/users/{userFbId}")
    public UserDetailsResponse getUserDetails(@PathVariable Long userFbId) {
        FBUserEntity userDetails = fbUserRepository.findByFacebookId(userFbId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with facebookId " + userFbId + " was not found!"));
        return userControllerMapper.mapFBUserEntityToUserDetailsResponse(userDetails);
    }

    @GetMapping("/users/{userFbId}/photos")
    public List<UserPhotoResponse> getUserPhotos(@PathVariable Long userFbId) {
        FBUserEntity userDetails = fbUserRepository.findByFacebookId(userFbId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with facebookId " + userFbId + " was not found!"));
        List<FBUserPhotoEntity> photos = userDetails.getPhotos();
        return userControllerMapper.mapFBPhotoEntityToUserDetailsResponses(photos);
    }

    @ExceptionHandler(FacebookGraphException.class)
    ResponseEntity<UserErrorResponse> handleFacebookException(FacebookGraphException e) {
        HttpStatus reason = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof FacebookOAuthException) {
            reason = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(new UserErrorResponse(new Date(), reason, reason.value(), e.getErrorMessage()), reason);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<UserErrorResponse> handleConstraintViolationException(MethodArgumentNotValidException e) {
        List<String> validationErrors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        final String message = "Wrong request:" + validationErrors.toString();
        return new ResponseEntity<>(new UserErrorResponse(new Date(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }


}


