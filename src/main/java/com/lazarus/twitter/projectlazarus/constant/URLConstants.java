package com.lazarus.twitter.projectlazarus.constant;

/**
 * class to store constant for the Twitter API endpoints
 */
public class URLConstants {

    /**
     * BASEURL FOR API  V2 VERSION
     */
    public static final String BASE_URL = "https://api.twitter.com/2";

    /**
     * url endpoint for getting tweets based on unique id of the tweet
     */
    public static final String Tweets_ID = BASE_URL + "/tweets/";

    private URLConstants() {
        throw new AssertionError("cant execute this class");
    }
}
