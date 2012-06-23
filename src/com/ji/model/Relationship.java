package com.ji.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.ji.exception.InstagramException;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Relationship
{
    private String outgoingStatus;
    private String incomingStatus;
    private boolean targetUserIsPrivate;

    public String getOutgoingStatus()
    {
        return outgoingStatus;
    }

    public void setOutgoingStatus(String outgoingStatus)
    {
        this.outgoingStatus = outgoingStatus;
    }

    public String getIncomingStatus()
    {
        return incomingStatus;
    }

    public void setIncomingStatus(String incomingStatus)
    {
        this.incomingStatus = incomingStatus;
    }

    public boolean isTargetUserIsPrivate()
    {
        return targetUserIsPrivate;
    }

    public void setTargetUserIsPrivate(boolean targetUserIsPrivate)
    {
        this.targetUserIsPrivate = targetUserIsPrivate;
    }

    @Override
    public String toString()
    {
        return "Relationship [outgoingStatus=" + outgoingStatus + ", incomingStatus=" + incomingStatus + ", targetUserIsPrivate="
                + targetUserIsPrivate + "]";
    }

    public Relationship deserialize(JSONObject data) throws InstagramException
    {
        try
        {
            setOutgoingStatus(data.getString("outgoing_status"));
            setIncomingStatus(data.getString("incoming_status"));
            setTargetUserIsPrivate(data.getBoolean("target_user_is_private"));
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
        return this;

    }
}
