package com.ji.model;

import org.json.JSONObject;

import com.ji.exception.InstagramException;


/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Comment
{
    private String createdTime;
    private String text;
    private From from;
    private long id;

    public String getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(String createdTime)
    {
        this.createdTime = createdTime;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public From getFrom()
    {
        return from;
    }

    public void setFrom(From from)
    {
        this.from = from;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Comment [createdTime=" + createdTime + ", text=" + text + ", from=" + from + ", id=" + id + "]";
    }

    public Comment deserialize(JSONObject data) throws InstagramException
    {

        return this;
    }

}
