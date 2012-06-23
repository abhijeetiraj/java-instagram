package com.ji.model;


/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class From
{
    private String username;
    private String profilePicture;
    private long id;
    private String fullName;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    @Override
    public String toString()
    {
        return "From [username=" + username + ", profilePicture=" + profilePicture + ", id=" + id + ", fullName=" + fullName + "]";
    }

}
