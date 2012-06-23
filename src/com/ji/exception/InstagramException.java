package com.ji.exception;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class InstagramException extends Exception
{
    private static final long serialVersionUID = 1198896972172803595L;

    public InstagramException(String message)
    {
        super(message);
    }

    public InstagramException(Throwable cause)
    {
        super(cause);
    }

    public InstagramException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
