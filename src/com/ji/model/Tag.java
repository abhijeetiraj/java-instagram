package com.ji.model;

import org.json.JSONObject;

import com.ji.exception.InstagramException;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Tag
{
    private long count;
    private String name;

    public long getCount()
    {
        return count;
    }

    public void setCount(long count)
    {
        this.count = count;
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
        return "Tag [count=" + count + ", name=" + name + "]";
    }

    public Tag deserialize(JSONObject data) throws InstagramException
    {

        return this;
    }


}
