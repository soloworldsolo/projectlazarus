package com.lazarus.twitter.projectlazarus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetRequest {
    @JsonProperty("tweet_id")
    private String tweetId;

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public TweetRequest(String tweetId) {
        this.tweetId = tweetId;
    }
}
