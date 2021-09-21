package com.lazarus.twitter.projectlazarus.constant;

/**
 * class to store constant for the Twitter API endpoints
 */
public class URLConstants {

    /**
     * BASEURL FOR API  V2 VERSION
     */
    public static final String BASE_URL = "https://api.twitter.com";

    public static final String VERSION_ONE = "/1.1";
    public static final String VERSION_TWO = "/2";

    /**
     * url endpoint for getting tweets based on unique id of the tweet
     */
    public static final String TWEETS_ID = BASE_URL + VERSION_TWO + "/tweets/";

    public static final String STATUS_UPDATE = BASE_URL + VERSION_ONE + "/statuses/update.json";

    private URLConstants() {
        throw new AssertionError("cant execute this class");
    }
}
