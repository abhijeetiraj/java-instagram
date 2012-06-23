package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.Location;
import com.ji.model.Media;


/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for Location Endpoints http://instagr.am/developer/endpoints/locations/
 */
public interface LocationAPI
{
    public String locationInfoPath = "/locations/%s/";
    public String recentMediaPath = "/locations/%s/media/recent/";
    public String locationSearchPath = "/locations/search/";

    public Location getLocationInfo(String locationId) throws InstagramException;

    public List<Media> getRecentMedia(String locationId) throws InstagramException;

    public List<Location> searchLocation(double latitude, double longitude) throws InstagramException;
}
