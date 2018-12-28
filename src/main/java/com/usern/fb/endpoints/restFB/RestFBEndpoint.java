package com.usern.fb.endpoints.restFB;

import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.Photo;
import com.restfb.types.User;
import com.usern.fb.endpoints.GraphAPIEndpoint;
import com.usern.fb.endpoints.mapper.RestFBToEntityMapper;
import com.usern.fb.entity.FBUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:fcb.properties")
public class RestFBEndpoint implements GraphAPIEndpoint {


    private static final String PHOTO_FIELDS_TO_MAP = "album,id,link,images{source},reactions";
    private static final String USER_FIELDS_TO_MAP = "id,name,gender,picture{url}";

    @Autowired
    private Environment env;

    @Autowired
    private RestFBToEntityMapper mapper;

    public FBUserEntity getFacebookUserWithDetails(String fbId, String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, env.getProperty("fb.MY_APP_SECRET"), Version.LATEST);
        User fbUser = facebookClient.fetchObject("/" + fbId, User.class, Parameter.with("fields", USER_FIELDS_TO_MAP));

        Connection<Photo> fbUserPhotos = facebookClient.fetchConnection("/" + fbId + "/photos", Photo.class, Parameter.with("fields", PHOTO_FIELDS_TO_MAP));
        List fbPhotos = fetchFBPhotos(fbUserPhotos);

        return mapper.mapUserToFBUser(fbUser,fbPhotos);
    }

    private List fetchFBPhotos(Connection<Photo> fbUserPhotos) {

        // If not everything was fetched
        final ConnectionIterator<Photo> iterator = fbUserPhotos.iterator();
        List fbPhotos = new ArrayList<Photo>();
        while (iterator.hasNext()) {
            fbPhotos.addAll(iterator.next());
        }
        return fbPhotos;
    }
}
