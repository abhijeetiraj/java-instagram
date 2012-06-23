package com.ji.model;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Caption
{
    private String createdTime;
    private From from;
    private long id;
    private String text;

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
        return "Caption [createdTime=" + createdTime + ", from=" + from + ", id=" + id + ", text=" + text + "]";
    }

}
