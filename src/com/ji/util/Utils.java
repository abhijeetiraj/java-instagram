package com.ji.util;

import com.ji.exception.InstagramException;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Utils
{

    public static void checkForNull(Object obj, String message) throws InstagramException
    {
        if (obj == null)
        {
            throw new InstagramException(message);
        }
    }

}
