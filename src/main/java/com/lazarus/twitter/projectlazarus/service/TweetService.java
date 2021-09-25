package com.lazarus.twitter.projectlazarus.service;

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
public class TweetService {
    @Autowired
    private RestUtil restUtil;

    public Tweet getTweetsById(java.lang.String id) {
        String url = TWEETS_ID + id;
        return restUtil.getWithoutParameter(url);

    }

    public String updateStatus(Status status) {
        Authorization authorization = new Authorization.AuthorizationBuilder(status.getConsumerKey(), status.getConsumerSecret(),
                status.getTokenKey(),
                status.getTokenSecret()).withStatus(status.getText()).withEntities("true").Build();
        Signature signature = new Signature(authorization, HttpMethod.POST.toString(), STATUS_UPDATE);
        String header = SecurityUtils.getAuthorizationHeader(signature);
        return restUtil.postStatus(header, signature);
    }

    public String deleteStatus(String id) {
        Signature signature = new Signature(new Authorization.AuthorizationBuilder("uh1yn1l86iteZsI9Ha3itXccf",
                "SkDUvZF5ZeWpRYccaO6c7ael5HF5q7pwCgzGXG4fxImLw5mLuq",
                "85558968-SuHkQsja7iTmzU4PBtxSUuDVlknvHi6ORvQT6ks1r",
                "0HF0Q9nEJ1ixt5JBE7Of5MWcoMNaIx22ZI2ZTwCI2K66N"
        ).ofId(id).Build(), HttpMethod.POST.toString(), DELETE_TWEET + id + ".json");
        String header = SecurityUtils.getAuthorizationHeader(signature);

        return restUtil.destroyStatus(header, signature);
    }

    public String reTweet(String id, String tweetId) {
        Signature signature = new Signature(new Authorization.AuthorizationBuilder("uh1yn1l86iteZsI9Ha3itXccf",
                "SkDUvZF5ZeWpRYccaO6c7ael5HF5q7pwCgzGXG4fxImLw5mLuq",
                "85558968-SuHkQsja7iTmzU4PBtxSUuDVlknvHi6ORvQT6ks1r",
                "0HF0Q9nEJ1ixt5JBE7Of5MWcoMNaIx22ZI2ZTwCI2K66N"
        ).ofId(id).Build(), HttpMethod.POST.toString(), RETWEET + id + "/retweets");

        String header = SecurityUtils.getAuthorizationHeader(signature);
        String body = new MapperUtils().convertObjectToString(new TweetRequest(tweetId));
        return restUtil.retweet(header, signature, body);
    }

    public String hideReplies(String id, Status status) {
        Signature signature = new Signature(new Authorization.AuthorizationBuilder(status.getConsumerKey(),status.getConsumerSecret(),
                status.getTokenKey(),status.getTokenSecret()).ofId(id).Build(),HttpMethod.PUT.toString(),TWEETS_ID+id+"/hidden");
        String header = SecurityUtils.getAuthorizationHeader(signature);
        Map<String,Boolean> map = new HashMap<>();
        map.put("hidden",true);
        String body = new MapperUtils().convertMapToString(map);

        return restUtil.hideReplies(signature,header,body);
    }
}
