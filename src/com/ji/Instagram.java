package com.ji;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ji.api.CommentAPI;
import com.ji.api.GeographyAPI;
import com.ji.api.LikeAPI;
import com.ji.api.LocationAPI;
import com.ji.api.MediaAPI;
import com.ji.api.RelationshipAPI;
import com.ji.api.TagAPI;
import com.ji.api.UserAPI;
import com.ji.conf.Configuration;
import com.ji.exception.InstagramException;
import com.ji.model.Comment;
import com.ji.model.Location;
import com.ji.model.Media;
import com.ji.model.Relationship;
import com.ji.model.RelationshipAction;
import com.ji.model.Tag;
import com.ji.model.User;
import com.ji.util.Utils;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class Instagram implements UserAPI, RelationshipAPI, MediaAPI, CommentAPI, LikeAPI, TagAPI, LocationAPI, GeographyAPI
{
    private String clientID;
    @SuppressWarnings("unused")
    private String clientSecret;
    private String accessToken;

    public Instagram(String clientID, String clientSecret)
    {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    public Instagram(String accessToken)
    {
        this.accessToken = accessToken;
    }

    /************************** User API **************************/

    /**
     * Get basic information about a user.
     * 
     * @param userId
     */
    public User getUserBasicInfo(String userId) throws InstagramException
    {
        String apiURL = String.format(UserAPI.userBasicInfoPath, userId);
        JSONObject response = executeGetMethod(apiURL);
        try
        {
            JSONObject user = response.getJSONObject("data");
            return new User().deserialize(user);
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
    }

    /**
     * See the authenticated user's feed.
     */
    public List<Media> getSelfFeed() throws InstagramException
    {
        Utils.checkForNull(accessToken, "This OAuth request requires an accessToken.");

        String apiURL = UserAPI.usersFeedPath;
        List<Media> mediaList = new ArrayList<Media>();
        try
        {
            JSONArray mediaArray = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < mediaArray.length(); i++)
            {
                JSONObject media = mediaArray.getJSONObject(i);
                mediaList.add(new Media().deserialize(media));
            }
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
        return mediaList;
    }

    /**
     * Get the most recent media published by a user.
     * 
     * @param userId
     */
    public Media getMostRecentMedia(String userId) throws InstagramException
    {
        Utils.checkForNull(accessToken, "This OAuth request requires an accessToken.");

        String apiURL = String.format(UserAPI.userMostRecentMediaPath, userId);
        try
        {
            JSONArray mediaArray = executeGetMethod(apiURL).getJSONArray("data");
            if (mediaArray.length() > 0)
            {
                return new Media().deserialize(mediaArray.getJSONObject(0));
            }
            else
            {
                return null;
            }
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
    }

    /**
     * See the authenticated user's list of media they've liked. Note that this list is ordered by the order in which
     * the user liked the media. Private media is returned as long as the authenticated user has permission to view that
     * media. Liked media lists are only available for the currently authenticated user.
     */
    public List<Media> getSelfLikedMedia() throws InstagramException
    {
        Utils.checkForNull(accessToken, "This OAuth request requires an accessToken.");

        List<Media> mediaList = new ArrayList<Media>();
        String apiURL = UserAPI.userLikedMediaPath;
        try
        {
            JSONArray mediaArray = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < mediaArray.length(); i++)
            {
                JSONObject media = mediaArray.getJSONObject(i);
                mediaList.add(new Media().deserialize(media));
            }
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
        return mediaList;
    }

    /**
     * Search for a user by name.
     * 
     * @param name
     *            user name
     * @param count
     *            Number of users to return.
     */
    public List<User> searchUsersByName(String name) throws InstagramException
    {
        List<User> userList = new ArrayList<User>();
        String apiURL = UserAPI.userSearchPath;
        try
        {
            NameValuePair[] params = new NameValuePair[]
                { new NameValuePair("q", name) };
            JSONArray users = executeGetMethod(apiURL, params).getJSONArray("data");
            for (int i = 0; i < users.length(); i++)
            {
                JSONObject user = users.getJSONObject(i);
                userList.add(new User().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            throw new InstagramException(e);
        }
        return userList;
    }


    /************************** Relationship API **************************/

    /**
     * Get the list of users this user follows.
     * 
     * @param userId
     */
    public List<User> getUserFollowsList(String userId) throws InstagramException
    {
        List<User> userList = new ArrayList<User>();
        String apiURL = String.format(RelationshipAPI.userFollowsPath, userId);
        try
        {
            JSONArray users = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < users.length(); i++)
            {
                JSONObject user = users.getJSONObject(i);
                userList.add(new User().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return userList;
    }

    /**
     * Get the list of users this user is followed by.
     * 
     * @param userId
     */
    public List<User> getUserFollowedByList(String userId) throws InstagramException
    {
        List<User> userList = new ArrayList<User>();
        String apiURL = String.format(RelationshipAPI.userFollowedByPath, userId);
        try
        {
            JSONArray users = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < users.length(); i++)
            {
                JSONObject user = users.getJSONObject(i);
                userList.add(new User().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return userList;
    }

    /**
     * List the users who have requested authenticated user's permission to follow.
     * 
     */
    public List<User> getUserRequestedByList() throws InstagramException
    {
        Utils.checkForNull(accessToken, "This OAuth request requires an accessToken.");

        List<User> userList = new ArrayList<User>();
        String apiURL = RelationshipAPI.userRequestedByPath;
        try
        {
            JSONArray users = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < users.length(); i++)
            {
                JSONObject user = users.getJSONObject(i);
                userList.add(new User().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return userList;
    }

    /**
     * Get information about authenticated user's relationship to another user.
     * 
     * @param userId
     */
    public Relationship getUserRelationship(String userId) throws InstagramException
    {
        Utils.checkForNull(accessToken, "This OAuth request requires an accessToken.");

        String apiURL = String.format(RelationshipAPI.userRelationshipPath, userId);
        JSONObject response = null;
        try
        {
            response = executeGetMethod(apiURL).getJSONObject("data");
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return new Relationship().deserialize(response);
    }

    /**
     * Modify the relationship between the current user and the target user.
     * 
     * @param userId
     * @param action
     */
    public void setUserRelationship(String userId, RelationshipAction action) throws InstagramException
    {
        // TODO Auto-generated method stub
    }


    /************************** Media API **************************/

    /**
     * Get information about a media object. If you authenticate with an OAuth Token, you will receive the
     * user_has_liked key which quickly tells you whether the current user has liked this media item.
     * 
     * @param mediaId
     */
    public Media getMediaInfo(String mediaId) throws InstagramException
    {
        String apiURL = String.format(MediaAPI.mediaInfoPath, mediaId);
        JSONObject response = executeGetMethod(apiURL);
        return new Media().deserialize(response);
    }

    /**
     * Search for media in a given area.
     * 
     * @param latitude
     *            Latitude of the center search coordinate. If used, lng is required.
     * @param longitude
     *            Longitude of the center search coordinate. If used, lat is required.
     */
    public List<Media> searchMediaInArea(double latitude, double longitude) throws InstagramException
    {
        List<Media> mediaList = new ArrayList<Media>();
        String apiURL = MediaAPI.mediaSearchPath;
        try
        {
            NameValuePair[] params = new NameValuePair[]
                { new NameValuePair("lat", latitude + ""), new NameValuePair("lng", longitude + "") };
            JSONArray medias = executeGetMethod(apiURL, params).getJSONArray("data");
            for (int i = 0; i < medias.length(); i++)
            {
                JSONObject media = medias.getJSONObject(i);
                mediaList.add(new Media().deserialize(media));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return mediaList;
    }

    /**
     * Get a list of what media is most popular at the moment.
     */
    public List<Media> getCurrentPopularMedia() throws InstagramException
    {
        List<Media> mediaList = new ArrayList<Media>();
        String apiURL = MediaAPI.mediaPopularPath;
        try
        {
            JSONArray medias = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < medias.length(); i++)
            {
                JSONObject media = medias.getJSONObject(i);
                mediaList.add(new Media().deserialize(media));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return mediaList;
    }


    /************************** Comment API **************************/

    /**
     * Get a full list of comments on a media.
     * 
     * @param mediaId
     */
    public List<Comment> getCommentsOnMedia(String mediaId) throws InstagramException
    {
        List<Comment> mediaList = new ArrayList<Comment>();
        String apiURL = String.format(CommentAPI.commentsOnMediaPath, mediaId);
        try
        {
            JSONArray comments = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < comments.length(); i++)
            {
                JSONObject comment = comments.getJSONObject(i);
                mediaList.add(new Comment().deserialize(comment));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return mediaList;
    }

    /**
     * Create a comment on a media.
     * 
     * @param mediaId
     * @param comment
     *            Text to post as a comment on the media as specified in mediaId.
     */
    public void addCommentOnMedia(String mediaId, String comment) throws InstagramException
    {
        // TODO Auto-generated method stub

    }

    /**
     * Remove a comment either on the authenticated user's media or authored by the authenticated user.
     * 
     * @param mediaId
     * @param commentId
     */
    public void deleteCommentOnMedia(String mediaId, String commentId) throws InstagramException
    {
        // TODO Auto-generated method stub

    }


    /************************** Like API **************************/

    /**
     * Get a list of users who have liked this media.
     * 
     * @param mediaId
     */
    public List<User> getUsersWhoLikedMedia(String mediaId) throws InstagramException
    {
        List<User> userList = new ArrayList<User>();
        String apiURL = String.format(LikeAPI.likeOnMediaPath, mediaId);
        try
        {
            JSONArray users = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < users.length(); i++)
            {
                JSONObject user = users.getJSONObject(i);
                userList.add(new User().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return userList;
    }

    /**
     * Set a like on this media by the currently authenticated user.
     * 
     * @param mediaId
     */
    public void addLikeOnMedia(String mediaId) throws InstagramException
    {
        // TODO Auto-generated method stub

    }

    /**
     * Remove a like on this media by the currently authenticated user.
     * 
     * @param mediaId
     */
    public void deleteLikeOnMedia(String mediaId) throws InstagramException
    {
        // TODO Auto-generated method stub

    }


    /************************** Tag API **************************/

    /**
     * Get information about a tag object.
     * 
     * @param name
     *            tag name
     */
    public Tag getTagInfo(String name) throws InstagramException
    {
        String apiURL = String.format(TagAPI.tagInfoPath, name);
        JSONObject response = executeGetMethod(apiURL);
        return new Tag().deserialize(response);
    }

    /**
     * Get a list of recently tagged media. Note that this media is ordered by when the media was tagged with this tag,
     * rather than the order it was posted. Use the max_tag_id and min_tag_id parameters in the pagination response to
     * paginate through these objects.
     * 
     * @param name
     *            tag name
     */
    public List<Media> getRecentlyTaggedMedia(String name) throws InstagramException
    {
        List<Media> mediaList = new ArrayList<Media>();
        String apiURL = String.format(TagAPI.recentlyTaggedMediaPath, name);
        try
        {
            JSONArray users = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < users.length(); i++)
            {
                JSONObject user = users.getJSONObject(i);
                mediaList.add(new Media().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return mediaList;
    }

    /**
     * Search for tags by name. Results are ordered first as an exact match, then by popularity.
     * 
     * @param name
     *            tag name
     */
    public List<Tag> searchTags(String name) throws InstagramException
    {
        List<Tag> tagList = new ArrayList<Tag>();
        String apiURL = TagAPI.searchTagsPath;
        try
        {
            NameValuePair[] params = new NameValuePair[]
                { new NameValuePair("q", name) };
            JSONArray tags = executeGetMethod(apiURL, params).getJSONArray("data");
            for (int i = 0; i < tags.length(); i++)
            {
                JSONObject user = tags.getJSONObject(i);
                tagList.add(new Tag().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return tagList;
    }


    /************************** Location API **************************/

    /**
     * Get information about a location.
     * 
     * @param locationId
     *            Location Id
     */
    public Location getLocationInfo(String locationId) throws InstagramException
    {
        String apiURL = String.format(LocationAPI.locationInfoPath, locationId);
        JSONObject response = executeGetMethod(apiURL);
        return new Location().deserialize(response);
    }

    /**
     * Get a list of recent media objects from a given location.
     * 
     * @param locationId
     *            Location Id
     */
    public List<Media> getRecentMedia(String locationId) throws InstagramException
    {
        List<Media> mediaList = new ArrayList<Media>();
        String apiURL = String.format(LocationAPI.recentMediaPath, locationId);
        try
        {
            JSONArray medias = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < medias.length(); i++)
            {
                JSONObject media = medias.getJSONObject(i);
                mediaList.add(new Media().deserialize(media));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return mediaList;
    }

    /**
     * Search for a location by geographic coordinate.
     * 
     * @param latitude
     *            Latitude of the center search coordinate. If used, lng is required.
     * @param longitude
     *            Longitude of the center search coordinate. If used, lat is required.
     */
    public List<Location> searchLocation(double latitude, double longitude) throws InstagramException
    {
        List<Location> locationList = new ArrayList<Location>();
        String apiURL = LocationAPI.locationSearchPath;
        try
        {
            NameValuePair[] params = new NameValuePair[]
                { new NameValuePair("lat", latitude + ""), new NameValuePair("lng", longitude + "") };
            JSONArray locations = executeGetMethod(apiURL, params).getJSONArray("data");
            for (int i = 0; i < locations.length(); i++)
            {
                JSONObject media = locations.getJSONObject(i);
                locationList.add(new Location().deserialize(media));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return locationList;
    }


    /************************** Geography API **************************/

    /**
     * Get very recent media from a geography subscription that you created. Note: you can only access Geographies that
     * were explicitly created by your OAuth client. To backfill photos from the location covered by this geography, use
     * the media search endpoint.
     * 
     * @param geoId
     *            geography subscription Id
     */
    public List<Media> getRecentMediaFromGeography(String geoId) throws InstagramException
    {
        List<Media> mediaList = new ArrayList<Media>();
        String apiURL = String.format(GeographyAPI.recentMediaPath, geoId);
        try
        {
            JSONArray users = executeGetMethod(apiURL).getJSONArray("data");
            for (int i = 0; i < users.length(); i++)
            {
                JSONObject user = users.getJSONObject(i);
                mediaList.add(new Media().deserialize(user));
            }
        }
        catch (JSONException e)
        {
            new InstagramException(e);
        }
        return mediaList;
    }


    private JSONObject executeGetMethod(String apiURL) throws InstagramException
    {
        return executeGetMethod(apiURL, null);
    }

    private JSONObject executeGetMethod(String apiURL, NameValuePair[] params) throws InstagramException
    {
        StringBuffer url = new StringBuffer(Configuration.getBaseUrl());
        url.append(apiURL);

        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();

        // Create a method instance.
        GetMethod method = new GetMethod(url.toString());

        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        NameValuePair authParam = null;
        if (accessToken != null)
        {
            authParam = new NameValuePair("access_token", accessToken);
        }
        else
        {
            authParam = new NameValuePair("client_id", clientID);
        }

        if (params != null)
        {
            method.setQueryString((NameValuePair[])ArrayUtils.addAll(params, new NameValuePair[]
                { authParam }));
        }
        else
        {
            method.setQueryString(new NameValuePair[]
                { authParam });
        }

        JSONObject jsonResponse = null;
        try
        {

            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK)
            {
                String message = "Method failed: " + method.getStatusLine() + " : " + new String(method.getResponseBody());
                System.err.println(message);
                throw new InstagramException(message);
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            String response = new String(responseBody);
            System.out.println(response);

            jsonResponse = new JSONObject(response);
        }
        catch (HttpException e)
        {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            throw new InstagramException(e);
        }
        catch (IOException e)
        {
            System.err.println("Fatal transport error: " + e.getMessage());
            throw new InstagramException(e);
        }
        catch (JSONException e)
        {
            System.err.println("Fatal json error: " + e.getMessage());
            throw new InstagramException(e);
        }
        finally
        {
            // Release the connection.
            method.releaseConnection();
        }
        return jsonResponse;
    }

}
