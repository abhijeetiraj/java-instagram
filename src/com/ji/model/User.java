package com.ji.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.ji.exception.InstagramException;

/**
 * 
 * @author Abhijeet Iraj
 *
 */
public class User
{
    private String id;
    private String userName;
    private String bio;
    private String website;
    private String pictureUrl;
    private String fullName;
    private long mediaCount;
    private long followedByCount;
    private long followsCount;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getPictureUrl()
    {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public long getMediaCount()
    {
        return mediaCount;
    }

    public void setMediaCount(long mediaCount)
    {
        this.mediaCount = mediaCount;
    }

    public long getFollowedByCount()
    {
        return followedByCount;
    }

    public void setFollowedByCount(long followedByCount)
    {
        this.followedByCount = followedByCount;
    }

    public long getFollowsCount()
    {
        return followsCount;
    }

    public void setFollowsCount(long followsCount)
    {
        this.followsCount = followsCount;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", userName=" + userName + ", bio=" + bio + ", website=" + website + ", pictureUrl=" + pictureUrl + ", fullName="
                + fullName + ", mediaCount=" + mediaCount + ", followedByCount=" + followedByCount + ", followsCount=" + followsCount + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        User other = (User)obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
        {
            return false;
        }
        return true;
    }

    public User deserialize(JSONObject user) throws InstagramException
    {
        try
        {
            setId(user.getString("id"));
            setUserName(user.getString("username"));
            setBio(user.getString("bio"));
            setWebsite(user.getString("website"));
            setPictureUrl(user.getString("profile_picture"));
            setFullName(user.getString("full_name"));
            if (user.has("counts"))
            {
                JSONObject counts = user.getJSONObject("counts");
                setMediaCount(counts.getLong("media"));
                setFollowedByCount(counts.getLong("followed_by"));
                setFollowsCount(counts.getLong("follows"));
            }
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
        return this;
    }

}
