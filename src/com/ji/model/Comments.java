package com.ji.model;


import java.util.List;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Comments
{
    private int count;
    private List<Comment> commentList;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public List<Comment> getCommentList()
    {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList)
    {
        this.commentList = commentList;
    }

    @Override
    public String toString()
    {
        return "Comments [count=" + count + ", commentList=" + commentList + "]";
    }

}
