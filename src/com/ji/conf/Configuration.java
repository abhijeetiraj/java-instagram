package com.ji.conf;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Configuration
{
    private static String protocol = "https";
    private static String host = "api.instagram.com";
    private static String basePath = "/v1";

    public static String getBaseUrl()
    {
        return protocol + "://" + host + basePath;
    }

}
