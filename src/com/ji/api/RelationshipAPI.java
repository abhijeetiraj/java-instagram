package com.ji.api;

import java.util.List;

import com.ji.exception.InstagramException;
import com.ji.model.Relationship;
import com.ji.model.RelationshipAction;
import com.ji.model.User;

/**
 * 
 * @author Abhijeet Iraj
 * 
 *         API for Relationship Endpoints http://instagr.am/developer/endpoints/relationships/
 */
public interface RelationshipAPI
{
    public String userFollowsPath = "/users/%s/follows";
    public String userFollowedByPath = "/users/%s/followed-by";
    public String userRequestedByPath = "/users/self/requested-by";
    public String userRelationshipPath = "/users/%s/relationship";

    public List<User> getUserFollowsList(String userId) throws InstagramException;

    public List<User> getUserFollowedByList(String userId) throws InstagramException;

    public List<User> getUserRequestedByList() throws InstagramException;

    public Relationship getUserRelationship(String userId) throws InstagramException;

    public void setUserRelationship(String userId, RelationshipAction action) throws InstagramException;

}
