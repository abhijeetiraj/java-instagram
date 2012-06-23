package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.Media;
import com.ji.model.Tag;

/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for Tag Endpoints http://instagr.am/developer/endpoints/tags/
 */
public interface TagAPI
{
    public String tagInfoPath = "/tags/%s/";
    public String recentlyTaggedMediaPath = "/tags/%s/media/recent/";
    public String searchTagsPath = "/tags/search/";

    public Tag getTagInfo(String name) throws InstagramException;

    public List<Media> getRecentlyTaggedMedia(String name) throws InstagramException;

    public List<Tag> searchTags(String name) throws InstagramException;

}
