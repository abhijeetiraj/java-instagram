package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.Media;

/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for Geography Endpoints http://instagr.am/developer/endpoints/geographies/
 */
public interface GeographyAPI
{
    public String recentMediaPath = "/geographies/%s/media/recent";

    public List<Media> getRecentMediaFromGeography(String geoId) throws InstagramException;
}
