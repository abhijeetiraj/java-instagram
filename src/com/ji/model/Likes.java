package com.ji.model;



import java.util.List;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Likes
{
    private int count;
    private List<User> likedUserList;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public List<User> getLikedUserList()
    {
        return likedUserList;
    }

    public void setLikedUserList(List<User> likedUserList)
    {
        this.likedUserList = likedUserList;
    }

    @Override
    public String toString()
    {
        return "Likes [count=" + count + ", likedUserList=" + likedUserList + "]";
    }

}
