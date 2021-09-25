package com.lazarus.twitter.projectlazarus.service;

import com.lazarus.twitter.projectlazarus.exception.TwitterException;
import com.lazarus.twitter.projectlazarus.model.Status;
import com.lazarus.twitter.projectlazarus.model.Tweet;
import com.lazarus.twitter.projectlazarus.model.TweetRequest;
import com.lazarus.twitter.projectlazarus.security.Authorization;
import com.lazarus.twitter.projectlazarus.security.Signature;
import com.lazarus.twitter.projectlazarus.util.MapperUtils;
import com.lazarus.twitter.projectlazarus.util.RestUtil;
import com.lazarus.twitter.projectlazarus.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.lazarus.twitter.projectlazarus.constant.URLConstants.*;


@Service
public class TweetService implements ITweetService {
    @Autowired
    private RestUtil restUtil;

    @Override
    public Tweet getTweetsById(String id) throws TwitterException {
        String url = TWEETS_ID + id;
        return restUtil.getWithoutParameter(url);

    }

    @Override
    public String updateStatus(Status status) throws TwitterException {
        Authorization authorization = new Authorization.AuthorizationBuilder(status.getConsumerKey(), status.getConsumerSecret(),
                status.getTokenKey(),
                status.getTokenSecret()).withStatus(status.getText()).withEntities("true").build();
        Signature signature = new Signature(authorization, HttpMethod.POST.toString(), STATUS_UPDATE);
        String header = SecurityUtils.getAuthorizationHeader(signature);
        return restUtil.postStatus(header, signature);
    }

    public String deleteStatus(String id) throws TwitterException {
        Signature signature = new Signature(new Authorization.AuthorizationBuilder("x",
                "y",
                "z",
                "g"
        ).ofId(id).build(), HttpMethod.POST.toString(), DELETE_TWEET + id + ".json");
        String header = SecurityUtils.getAuthorizationHeader(signature);

        return restUtil.destroyStatus(header, signature);
    }

    @Override
    public String reTweet(String id, String tweetId) throws TwitterException {
        Signature signature = new Signature(new Authorization.AuthorizationBuilder("a",
                "b",
                "c",
                "d"
        ).ofId(id).build(), HttpMethod.POST.toString(), RETWEET + id + "/retweets");

        String header = SecurityUtils.getAuthorizationHeader(signature);
        String body = new MapperUtils().convertObjectToString(new TweetRequest(tweetId));
        return restUtil.retweet(header, signature, body);
    }

    @Override
    public String hideReplies(String id, Status status) throws TwitterException {
        Signature signature = new Signature(new Authorization.AuthorizationBuilder(status.getConsumerKey(), status.getConsumerSecret(),
                status.getTokenKey(), status.getTokenSecret()).ofId(id).build(), HttpMethod.PUT.toString(), TWEETS_ID + id + "/hidden");
        String header = SecurityUtils.getAuthorizationHeader(signature);
        Map<String, Boolean> map = new HashMap<>();
        map.put("hidden", true);
        String body = new MapperUtils().convertMapToString(map);

        return restUtil.hideReplies(signature, header, body);
    }
}
