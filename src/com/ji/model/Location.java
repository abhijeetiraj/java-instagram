package com.ji.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.ji.exception.InstagramException;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Location
{
    private String id;
    private String name;
    private double latitude;
    private double longitude;

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Location [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    public Location deserialize(JSONObject location) throws InstagramException
    {
        try
        {
            setId(location.getString("id"));
            setName(location.getString("name"));
            setLatitude(location.getDouble("latitude"));
            setLongitude(location.getDouble("longitude"));
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
        return this;
    }

}
