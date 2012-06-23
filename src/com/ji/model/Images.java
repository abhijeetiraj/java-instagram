package com.ji.model;


/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Images
{
    private Image lowResolution;
    private Image thumbnail;
    private Image standardResolution;

    public Image getLowResolution()
    {
        return lowResolution;
    }

    public void setLowResolution(Image lowResolution)
    {
        this.lowResolution = lowResolution;
    }

    public Image getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public Image getStandardResolution()
    {
        return standardResolution;
    }

    public void setStandardResolution(Image standardResolution)
    {
        this.standardResolution = standardResolution;
    }

    @Override
    public String toString()
    {
        return "Images [lowResolution=" + lowResolution + ", thumbnail=" + thumbnail + ", standardResolution=" + standardResolution + "]";
    }

}
