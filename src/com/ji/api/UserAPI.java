package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.Media;
import com.ji.model.User;

/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for User Endpoints http://instagr.am/developer/endpoints/users/
 */
public interface UserAPI
{
    public String userBasicInfoPath = "/users/%s/";
    public String usersFeedPath = "/users/self/feed";
    public String userMostRecentMediaPath = "/users/%s/media/recent";
    public String userLikedMediaPath = "/users/self/media/liked";
    public String userSearchPath = "/users/search/";

    public User getUserBasicInfo(String userId) throws InstagramException;

    public List<Media> getSelfFeed() throws InstagramException;

    public List<Media> getSelfLikedMedia() throws InstagramException;

    public Media getMostRecentMedia(String userId) throws InstagramException;

    public List<User> searchUsersByName(String name) throws InstagramException;

}
