package com.lazarus.twitter.projectlazarus.service;

import com.lazarus.twitter.projectlazarus.exception.TwitterException;
import com.lazarus.twitter.projectlazarus.model.Status;
import com.lazarus.twitter.projectlazarus.model.Tweet;

public interface ITweetService {
    public Tweet getTweetsById(String id) throws TwitterException;

    public String updateStatus(Status status) throws TwitterException;


}
