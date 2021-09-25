package com.lazarus.twitter.projectlazarus.controller;

import com.lazarus.twitter.projectlazarus.model.Status;
import com.lazarus.twitter.projectlazarus.model.Tweet;
import com.lazarus.twitter.projectlazarus.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweets/{id}")
    public Tweet getTweetsById(@PathVariable("id") String id) {
        return tweetService.getTweetsById(Objects.requireNonNull(id));
    }

    @PostMapping("/tweet/status")
    public String postStatusUpdate(@RequestBody Status status) {
        return tweetService.updateStatus(status);
    }

    @DeleteMapping("/tweet/destroy/{id}")
    public String destroyStatus(@PathVariable("id") String id) {
        return tweetService.deleteStatus(id);
    }

    @PostMapping("/tweet/retweet/{id}/{tweetId}")
    public String reTweet(@PathVariable("id") String id ,@PathVariable("tweetId") String tweetId){
        return tweetService.reTweet(id,tweetId);
    }

    @PutMapping("/tweet/hidereply/{id}")
    public String hideReplies(@PathVariable("id") String id ,@RequestBody Status status){
        return tweetService.hideReplies(id ,status);
    }
}
