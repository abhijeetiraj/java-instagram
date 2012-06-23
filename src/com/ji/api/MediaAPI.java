package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.Media;

/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for Media Endpoints http://instagr.am/developer/endpoints/media/
 */
public interface MediaAPI
{
    public String mediaInfoPath = "/media/%s/";
    public String mediaSearchPath = "/media/search/";
    public String mediaPopularPath = "/media/popular/";

    public Media getMediaInfo(String mediaId) throws InstagramException;

    public List<Media> searchMediaInArea(double latitude, double longitude) throws InstagramException;

    public List<Media> getCurrentPopularMedia() throws InstagramException;

}
