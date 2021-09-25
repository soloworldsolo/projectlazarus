package com.lazarus.twitter.projectlazarus.service;

import com.lazarus.twitter.projectlazarus.exception.TwitterException;
import com.lazarus.twitter.projectlazarus.model.Status;
import com.lazarus.twitter.projectlazarus.model.Tweet;

public interface ITweetService {
    Tweet getTweetsById(String id) throws TwitterException;

    String updateStatus(Status status) throws TwitterException;

    String reTweet(String id, String tweetId) throws TwitterException;

    String hideReplies(String id, Status status) throws TwitterException;

    String deleteStatus(String id) throws TwitterException;
}
