package com.lazarus.twitter.projectlazarus.service;

import com.lazarus.twitter.projectlazarus.model.Status;
import com.lazarus.twitter.projectlazarus.model.Tweet;
import com.lazarus.twitter.projectlazarus.security.Authorization;
import com.lazarus.twitter.projectlazarus.security.Signature;
import com.lazarus.twitter.projectlazarus.util.RestUtil;
import com.lazarus.twitter.projectlazarus.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

import static com.lazarus.twitter.projectlazarus.constant.URLConstants.TWEETS_ID;


@Service
public class TweetService {
    @Autowired
    private RestUtil restUtil;

    public Tweet getTweetsById(java.lang.String id) {
        String url = TWEETS_ID + id;
        return restUtil.getWithoutParameter(url);

    }

    public String updateStatus(Status status) {
        Signature signature = new Signature(new Authorization(status.getConsumerKey(), status.getConsumerSecret(),
                status.getTokenKey( ),
                status.getTokenSecret(),
                Objects.requireNonNull(status.getText())));
        String header = SecurityUtils.getAuthorizationHeader(signature);
        return restUtil.postStatus(header, signature);
    }
}
