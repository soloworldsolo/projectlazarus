package com.lazarus.twitter.projectlazarus.controller;

import com.lazarus.twitter.projectlazarus.model.Status;
import com.lazarus.twitter.projectlazarus.model.Tweet;
import com.lazarus.twitter.projectlazarus.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * rest controller contains endpoints for all API
 */
@RestController
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweets/{id}")
    public Tweet getTweetsById(@PathVariable("id") String id) {
        return tweetService.getTweetsById(Objects.requireNonNull(id));
    }

    @PostMapping("/tweet/status")
    public ResponseEntity postStatusUpdate(@RequestBody Status status) {
        return ResponseEntity.ok(tweetService.updateStatus(status));
    }

    @DeleteMapping("/tweet/destroy/{id}")
    public ResponseEntity destroyStatus(@PathVariable("id") String id) {
        return ResponseEntity.ok(tweetService.deleteStatus(id));
    }

    @PostMapping("/tweet/retweet/{id}/{tweetId}")
    public ResponseEntity reTweet(@PathVariable("id") String id, @PathVariable("tweetId") String tweetId) {
        return ResponseEntity.ok().body(tweetService.reTweet(id, tweetId));
    }

    @PutMapping("/tweet/hidereply/{id}")
    public ResponseEntity hideReplies(@PathVariable("id") String id, @RequestBody Status status) {
        return ResponseEntity.ok().body(tweetService.hideReplies(id, status));
    }
}
