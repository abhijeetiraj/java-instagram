package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.Comment;

/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for Comment Endpoints http://instagr.am/developer/endpoints/comments/
 */
public interface CommentAPI
{
    public String commentsOnMediaPath = "/media/%s/comments/";
    public String deleteCommentOnMediaPath = "/media/%s/comments/%s/";

    public List<Comment> getCommentsOnMedia(String mediaId) throws InstagramException;

    public void addCommentOnMedia(String mediaId, String comment) throws InstagramException;

    public void deleteCommentOnMedia(String mediaId, String commentId) throws InstagramException;
}
