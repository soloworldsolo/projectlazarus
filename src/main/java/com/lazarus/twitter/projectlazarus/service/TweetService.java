package com.lazarus.twitter.projectlazarus.service;

import com.lazarus.twitter.projectlazarus.model.Tweet;
import com.lazarus.twitter.projectlazarus.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lazarus.twitter.projectlazarus.constant.URLConstants.Tweets_ID;


@Service
public class TweetService {
    @Autowired
    private RestUtil restUtil;
    public Tweet getTweetsById(java.lang.String id) {
        String url = Tweets_ID+id;
        return restUtil.getWithoutParameter(url);

    }
}
