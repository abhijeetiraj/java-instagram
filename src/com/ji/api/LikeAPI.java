package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.User;

/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for Like Endpoints http://instagr.am/developer/endpoints/likes/
 */
public interface LikeAPI
{
    public String likeOnMediaPath = "/media/%s/likes/";

    public List<User> getUsersWhoLikedMedia(String mediaId) throws InstagramException;

    public void addLikeOnMedia(String mediaId) throws InstagramException;

    public void deleteLikeOnMedia(String mediaId) throws InstagramException;

}
